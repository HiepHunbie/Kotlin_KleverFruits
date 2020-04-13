package com.example.kleverfruits.ui.dialog.bonus

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.bonus.DataBonus
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.ImageUtil
import com.example.kleverfruits.utils.`object`.NumberUtil
import kotlinx.android.synthetic.main.item_bonus_from_klever.view.*

class BonusKleverAdapter (val context: MainActivity, val clickItemListener: (DataBonus, Int) -> Unit
                          , val clickItemConvertPointListener: (DataBonus, Int) -> Unit): RecyclerView.Adapter<BonusKleverAdapter.ViewHolder>(){
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
                R.layout.item_bonus_from_klever,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: DataBonus = items[position]

        holder?.txt_point.setText(item.number_point.toString() ?: "")
        holder?.txt_title.setText(item.title?: "")
        holder?.txt_date.setText(item.date.toString() ?: "")

        holder?.layout_view.setOnClickListener(View.OnClickListener {
            clickItemListener(item,position)
        })
        holder?.txt_convert_point.setOnClickListener(View.OnClickListener {
            clickItemConvertPointListener(item,position)
        })

        if(item.count >1){
            holder?.txt_number_bonus_card.setText("x"+item.count.toString() ?: "")
            holder?.txt_number_bonus_card.visibility = View.VISIBLE
        }else{
            holder?.txt_number_bonus_card.visibility = View.GONE
        }

//        if(item.image.isNotEmpty()){
//            ImageUtil.loadImageGlideNotProgressBar(context,item.image!!,holder?.img_product!!)
//        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val layout_view = view.layout_view!!
        val txt_point = view.txt_point!!
        val txt_title = view.txt_title!!
        val txt_date = view.txt_date
        val txt_convert_point = view.txt_convert_point
        val img_title = view.img_title
        val txt_number_bonus_card = view.txt_number_bonus_card
    }
}