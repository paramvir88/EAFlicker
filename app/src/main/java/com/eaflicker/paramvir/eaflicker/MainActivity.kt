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
import com.eaflicker.paramvir.eaflicker.network.FlickrService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var photos: List<Photo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layoutManager = GridLayoutManager(this, 2)
        rv.layoutManager = layoutManager
        rv.adapter = RvAdapter(photos)


        FlickrService().searchPhotos("android")

    }

    class RvAdapter(var photosData: List<Photo>) : RecyclerView.Adapter<RvViewHolder>() {


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

    class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView : ImageView? = null
        var titleTv: TextView? = null
        fun bind(){
            imageView = itemView.findViewById(R.id.iv_photo)
            titleTv = itemView.findViewById(R.id.tv_title)
        }

        fun setData(photo: Photo){
            titleTv?.text = photo.title
        }


    }


}


