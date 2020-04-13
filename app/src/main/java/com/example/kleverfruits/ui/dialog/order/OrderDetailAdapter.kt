package com.example.kleverfruits.ui.dialog.order

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_oder_detail.view.*

class OrderDetailAdapter (val context: MainActivity, val clickItemListener: (DataOrderDetail, Int) -> Unit): RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>(){
    private var items: List<DataOrderDetail> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataOrderDetail>, isLoadMore:Boolean) {
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
                R.layout.item_oder_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataOrderDetail = items[position]

        holder?.txt_number.setText(item.number.toString())
        holder?.txt_name.setText(item.name)
        holder?.txt_cost.setText(NumberUtil.convertNumberToPrice(item.cost.toDouble(), context))

        if(item.status == 0){
            holder?.txt_status.setText(context.getString(R.string.waiting_confirm))
            holder?.txt_status.setTextColor(context.resources.getColor(R.color.background_main))
            holder?.txt_status.setBackgroundResource(R.drawable.custom_button_violet_white)
        }else if(item.status == 1){
            holder?.txt_status.setText(context.getString(R.string.shipping))
            holder?.txt_status.setTextColor(context.resources.getColor(R.color.background_main))
            holder?.txt_status.setBackgroundResource(R.drawable.custom_button_violet_white)
        }else if(item.status == 2){
            holder?.txt_status.setText(context.getString(R.string.shipped))
            holder?.txt_status.setTextColor(context.resources.getColor(R.color.white))
            holder?.txt_status.setBackgroundResource(R.drawable.custom_button_violet)
        }else if(item.status == 3){
            holder?.txt_status.setText(context.getString(R.string.canceled))
            holder?.txt_status.setTextColor(context.resources.getColor(R.color.white))
            holder?.txt_status.setBackgroundResource(R.drawable.custom_button_gray)
        }

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

        if(item.url.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(context,item.url!!,holder?.img_product!!)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val txt_status = view.txt_status!!
        val txt_number = view.txt_number!!
        val txt_cost = view.txt_cost
        val txt_name = view.txt_name
        val img_product = view.img_product
    }
}