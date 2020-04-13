package com.example.kleverfruits.ui.fragment.beautyOfLife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.beautyOfLife.DataBeautyOfLife
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_beauty_of_life.view.*

class BeautyOfLifeAdapter (val context: MainActivity, val clickItemListener: (DataBeautyOfLife, Int) -> Unit): RecyclerView.Adapter<BeautyOfLifeAdapter.ViewHolder>(){
    private var items: List<DataBeautyOfLife> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataBeautyOfLife>, isLoadMore:Boolean) {
        if(isLoadMore){
            this.items+=items
        }else{
            this.items = items
        }
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_beauty_of_life,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataBeautyOfLife = items[position]

        holder?.txt_name.setText(item.title.toString() ?: "")

        holder?.layout_item.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

//        if(item.image.isNotEmpty()){
//            ImageUtil.loadImageGlideNotProgressBar(context,item.image!!,holder?.img_product!!)
//        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_item = view.layout_item!!
        val txt_name = view.txt_name!!
    }
}