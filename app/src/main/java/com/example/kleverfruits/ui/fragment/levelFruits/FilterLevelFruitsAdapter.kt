package com.example.kleverfruits.ui.fragment.levelFruits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.levelFruits.DataFilterLevelFruits
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_filter_fruits.view.*

class FilterLevelFruitsAdapter (val context: MainActivity, val clickItemListener: (DataFilterLevelFruits, Int) -> Unit): RecyclerView.Adapter<FilterLevelFruitsAdapter.ViewHolder>(){
    private var items: List<DataFilterLevelFruits> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    var posChecked = ""
    fun updatePosts(items: List<DataFilterLevelFruits>,posChecked : String, isLoadMore:Boolean) {
        if(isLoadMore){
            this.items+=items
        }else{
            this.items = items
        }
        this.posChecked = posChecked
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_filter_fruits,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataFilterLevelFruits = items[position]

        if(item.id.equals(posChecked)){
            holder?.txt_name.setTextColor(context.resources.getColor(R.color.background_main))
            holder?.img_filter.setColorFilter(context.resources.getColor(R.color.background_main))
        }
        holder?.txt_name.setText(item.name)

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val img_filter = view.img_filter!!
        val layout_view = view.layout_view!!
        val txt_name = view.txt_name
    }
}