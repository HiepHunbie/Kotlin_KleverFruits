package com.example.kleverfruits.ui.dialog.memberInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.memberInfo.MemberInfoBonusData
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_member_info.view.*

class MemberInfoAdapter (val context: MainActivity): RecyclerView.Adapter<MemberInfoAdapter.ViewHolder>(){
    private var items: List<MemberInfoBonusData> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePosts(items: List<MemberInfoBonusData>, isLoadMore:Boolean) {
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
                R.layout.item_member_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: MemberInfoBonusData = items[position]

        holder?.txt_value.setText(item.data.toString() ?: "")
        if(item.type == 0){
            holder?.img_avatar.setImageResource(R.drawable.ic_violet_member)
        }else if(item.type == 1){
            holder?.img_avatar.setImageResource(R.drawable.ic_sliver_member)
        }else if(item.type == 2){
            holder?.img_avatar.setImageResource(R.drawable.ic_gold_member)
        }else if(item.type == 3){
            holder?.img_avatar.setImageResource(R.drawable.ic_diamound_member)
        }

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val img_avatar = view.img_avatar!!
        val txt_value = view.txt_value!!
    }
}