package com.example.kleverfruits.ui.fragment.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.sale.DataSale
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_sale.view.*

class SaleAdapter (val context: MainActivity, val clickItemListener: (DataSale, Int) -> Unit): RecyclerView.Adapter<SaleAdapter.ViewHolder>(){
    private var items: List<DataSale> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataSale>, isLoadMore:Boolean) {
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
                R.layout.item_sale,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataSale = items[position]

        holder?.txt_name.setText(item.title.toString() ?: "")
        holder?.txt_date.setText(item.date.toString() ?: "")

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
        val txt_date = view.txt_date
    }
}