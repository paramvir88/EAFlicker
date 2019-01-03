package com.eaflicker.paramvir.eaflicker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.eaflicker.paramvir.eaflicker.network.FlickrResponse
import com.eaflicker.paramvir.eaflicker.network.FlickrService
import com.eaflicker.paramvir.eaflicker.network.GenericApiListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v7.widget.DefaultItemAnimator


class MainActivity : AppCompatActivity() {


    var photos: List<Photo> = ArrayList()

    var glide: RequestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemAnimator = DefaultItemAnimator()
        itemAnimator.addDuration = 1000
        itemAnimator.removeDuration = 1000

        glide = Glide.with(this)
        var layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager

        rv.setItemAnimator(itemAnimator)


        val listener = object : GenericApiListener<List<Photo>, String> {

            override fun onSucsess(ressponse: List<Photo>) {

                rv.adapter = RvAdapter(ressponse)
            }

            override fun onFailure(failure: String) {

            }
        }
        FlickrService().searchPhotos("android", listener)

    }

    inner class RvAdapter(var photosData: List<Photo>) : RecyclerView.Adapter<RvViewHolder>() {


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


