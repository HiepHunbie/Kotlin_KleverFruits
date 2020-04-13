package com.example.kleverfruits.ui.fragment.newFruits

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.newFruits.DataNewFruits
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_new_fruits.view.*

class NewFruitsAdapter (val context: MainActivity, val clickItemListener: (DataNewFruits, Int) -> Unit): RecyclerView.Adapter<NewFruitsAdapter.ViewHolder>(){
    private var items: List<DataNewFruits> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataNewFruits>, isLoadMore:Boolean) {
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
                R.layout.item_new_fruits,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataNewFruits = items[position]

        holder?.txt_time.setText(item.time)
        holder?.txt_name.setText(item.name)
        holder?.txt_sale_cost.setText(NumberUtil.convertNumberToPrice(item.sale_cost.toDouble(), context))
        holder?.txt_real_cost.setText(NumberUtil.convertNumberToPrice(item.real_cost.toDouble(), context))
        holder?.txt_real_cost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        holder?.layout_item.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

        if(item.url.isNotEmpty()){
            ImageUtil.loadImageGlide(context,item.url!!,holder?.img_product!!,holder?.prg_new_fruits!!)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_item = view.layout_item!!
        val txt_time = view.txt_time!!
        val img_product = view.img_product!!
        val txt_name = view.txt_name
        val txt_sale_cost = view.txt_sale_cost
        val txt_real_cost = view.txt_real_cost
        val prg_new_fruits = view.prg_new_fruits
    }
}