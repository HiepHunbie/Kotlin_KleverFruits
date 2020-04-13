package com.example.kleverfruits.ui.fragment.notification

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.notification.DataNotification
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter (val context: MainActivity, val clickItemListener: (DataNotification, Int) -> Unit): RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){
    private var items: List<DataNotification> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataNotification>, isLoadMore:Boolean) {
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
                R.layout.item_notification,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataNotification = items[position]

        holder?.txt_title.setText(item.title)
        holder?.txt_date.setText(item.date)
        holder?.txt_detail.setText(item.detail)

        if(item.status == 0){
            holder?.layout_view.setBackgroundColor(context.resources.getColor(R.color.white))
        }else if(item.status == 1){
            holder?.layout_view.setBackgroundColor(context.resources.getColor(R.color.item_blue_notification))
        }

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })

        if(item.image.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(context,item.image!!,holder?.img_product!!)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val img_product = view.img_product!!
        val txt_title = view.txt_title!!
        val txt_detail = view.txt_detail
        val txt_date = view.txt_date
    }
}