package com.example.kleverfruits.ui.dialog.cart

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.cart.cartDetail.DataCartDetail
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter (val context: MainActivity, val clickItemListener: (DataCartDetail, Int) -> Unit
                   , val clickCancekListener: (DataCartDetail, Int) -> Unit): RecyclerView.Adapter<CartAdapter.ViewHolder>(){
    private var items: List<DataCartDetail> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataCartDetail>, isLoadMore:Boolean) {
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
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataCartDetail = items[position]

        holder?.txt_number.setText(item.number.toString() ?: "")
        holder?.txt_name.setText(item.name?: "")
        holder?.txt_sale_cost.setText(NumberUtil.convertNumberToPrice(item.sale_cost.toDouble(), context)?: "")
        holder?.txt_real_cost.setText(NumberUtil.convertNumberToPrice(item.real_cost.toDouble(), context)?: "")
        holder?.txt_real_cost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

        holder?.layout_cancel.setOnClickListener(View.OnClickListener {
            clickCancekListener(item,position)
        })
        holder?.img_minus!!.setOnClickListener(View.OnClickListener {
            if(holder?.txt_number!!.text.toString().toInt()>0){
                holder?.txt_number!!.setText((holder?.txt_number!!.text.toString().toInt()-1).toString())
            }
        })
        holder?.img_add!!.setOnClickListener(View.OnClickListener {
            if(holder?.txt_number!!.text.toString().toInt()<10){
                holder?.txt_number!!.setText((holder?.txt_number!!.text.toString().toInt()+1).toString())
            }
        })
        if(item.image.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(context,item.image!!,holder?.img_product!!)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val txt_number = view.txt_number!!
        val img_product = view.img_product!!
        val txt_name = view.txt_name
        val txt_sale_cost = view.txt_sale_cost
        val txt_real_cost = view.txt_real_cost
        val img_minus = view.img_minus
        val img_add = view.img_add
        val layout_cancel = view.layout_cancel
    }
}