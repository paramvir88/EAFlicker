package com.eaflicker.paramvir.eaflicker

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.eaflicker.paramvir.eaflicker.database.FlickrDatabase
import com.eaflicker.paramvir.eaflicker.network.FlickrService
import com.eaflicker.paramvir.eaflicker.network.GenericApiListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var flckrService: FlickrService? = null
    var database: FlickrDatabase? = null


    override fun onQueryTextSubmit(text: String?): Boolean {
        searchPhotos(text)
        return true
    }

    private fun searchPhotos(text: String?) {
        if (!TextUtils.isEmpty(text?.trim())) {
            var term = SearchTerm()
            term.key = text!!
            Thread {
                database?.searchTermDao()?.insert(term)
                /*var terms = database?.searchTermDao()?.allSearchTerms
                Log.i("SEARCHES ", "values = $terms")*/

            }.start()


            fetchData(text!!)

        }
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    var glide: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(applicationContext, FlickrDatabase::class.java, "fl_databse").build()
        flckrService = FlickrService(database)

        val itemAnimator = DefaultItemAnimator()
        itemAnimator.addDuration = 1000
        itemAnimator.removeDuration = 1000

        search?.setOnQueryTextListener(this)
        glide = Glide.with(this)
        var layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager
        rv.itemAnimator = itemAnimator

        search.setQuery("flowers", true)
        search.setIconifiedByDefault(false)
        //searchPhotos("flowers")

    }

    private fun fetchData(text: String) {
        val listener = object : GenericApiListener<List<Photo>, String> {

            override fun onSucsess(ressponse: List<Photo>) {

                rv.post {
                    rv.adapter = RvAdapter(ressponse)
                    progress_bar.visibility = View.GONE
                }

            }

            override fun onFailure(failure: String) {
                progress_bar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Some network issue", Toast.LENGTH_LONG).show()
            }
        }
        progress_bar.visibility = View.VISIBLE
        flckrService?.searchPhotos(text, listener)
    }

    inner class RvAdapter(var photosData: List<Photo>) : RecyclerView.Adapter<RvViewHolder>() {

        fun updateData(data: List<Photo>) {
            photosData = data
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {


            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            var vh = RvViewHolder(view)
            vh.bind()

            return vh
        }

        override fun getItemCount(): Int {
            return photosData.size
        }

        override fun onBindViewHolder(viewHolder: RvViewHolder, p1: Int) {
            var photo = photosData[p1]
            viewHolder.setData(photo)

        }

    }

    inner class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView? = null
        var titleTv: TextView? = null
        fun bind() {
            imageView = itemView.findViewById(R.id.iv_photo)
            titleTv = itemView.findViewById(R.id.tv_title)
        }

        fun setData(photo: Photo) {
            titleTv?.text = photo.title
            glide?.load(photo.url_o)
                ?.centerCrop()
                ?.into(imageView)
        }

    }


}


