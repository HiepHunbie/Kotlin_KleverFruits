package com.example.kleverfruits.ui.dialog.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.levelFruits.DataFilterLevelFruits
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.fragment.levelFruits.FilterLevelFruitsAdapter
import com.example.kleverfruits.ui.fragment.newFruits.NewFruitsAdapter
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter (val context: MainActivity, val clickItemListener: (DataOrder, Int) -> Unit): RecyclerView.Adapter<OrderAdapter.ViewHolder>(){
    private var items: List<DataOrder> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataOrder>, isLoadMore:Boolean) {
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
                R.layout.item_order,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataOrder = items[position]

        holder?.txt_order_code.setText(item.order_code)
        holder?.txt_date.setText(item.date)

        var totalCost = 0
        for(itemSmall in item.dataOrderDetail){
            totalCost = totalCost + (itemSmall.cost.toInt() * itemSmall.number)
        }
        holder?.txt_total_cost.setText(NumberUtil.convertNumberToPrice(totalCost.toDouble(), context))
        holder?.txt_count_product.setText(item.dataOrderDetail.size.toString() + " "+ context.getString(R.string.product_small))

        if(item.status == 0){
            holder?.txt_status.setText(context.getString(R.string.not_pay))
        }else if(item.status == 1){
            holder?.txt_status.setText(context.getString(R.string.payed))
        }

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

        var orderDetailAdapter : OrderDetailAdapter? = null
        orderDetailAdapter = OrderDetailAdapter(context!!, { itemDto: DataOrderDetail, position: Int ->

        })

        holder?.ryv_order_detail!!.adapter = orderDetailAdapter
        holder?.ryv_order_detail!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
        orderDetailAdapter!!.updatePosts(item.dataOrderDetail,false)

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val txt_order_code = view.txt_order_code!!
        val txt_date = view.txt_date!!
        val txt_status = view.txt_status
        val ryv_order_detail = view.ryv_order_detail
        val txt_count_product = view.txt_count_product
        val txt_total_cost = view.txt_total_cost
    }
}