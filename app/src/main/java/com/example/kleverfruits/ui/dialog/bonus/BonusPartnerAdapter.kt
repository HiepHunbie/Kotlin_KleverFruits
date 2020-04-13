package com.example.kleverfruits.ui.dialog.bonus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.bonus.DataBonus
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import kotlinx.android.synthetic.main.item_bonus_from_partner.view.*

class BonusPartnerAdapter (val context: MainActivity, val clickItemListener: (DataBonus, Int) -> Unit
                           , val clickItemConvertPointListener: (DataBonus, Int) -> Unit): RecyclerView.Adapter<BonusPartnerAdapter.ViewHolder>(){
    private var items: List<DataBonus> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<DataBonus>, isLoadMore:Boolean) {
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
                R.layout.item_bonus_from_partner,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataBonus = items[position]

        holder?.txt_point.setText(item.number_point.toString() ?: "")
        holder?.txt_name_partner.setText(item.name_partner?: "")
        holder?.txt_sale_title.setText(item.title.toString() ?: "")

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })
        holder?.txt_convert_point.setOnClickListener(View.OnClickListener {
            clickItemConvertPointListener(item,position)
        })

        if(item.url.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(context,item.url!!,holder?.img_small_back!!)
        }
        if(item.url_back.isNotEmpty()){
            ImageUtil.loadImageGlideNotProgressBar(context,item.url_back!!,holder?.img_back!!)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val txt_point = view.txt_point!!
        val img_back = view.img_back!!
        val img_small_back = view.img_small_back
        val txt_name_partner = view.txt_name_partner
        val txt_sale_title = view.txt_sale_title
        val txt_convert_point = view.txt_convert_point
    }
}