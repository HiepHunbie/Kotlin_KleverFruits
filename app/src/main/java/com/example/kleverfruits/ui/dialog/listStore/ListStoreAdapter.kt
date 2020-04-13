package com.example.kleverfruits.ui.dialog.listStore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kleverfruits.R
import com.example.kleverfruits.model.listStore.DataListStore
import com.example.kleverfruits.ui.main.MainActivity
import kotlinx.android.synthetic.main.item_list_store.view.*
import kotlinx.android.synthetic.main.item_title_list_store.view.*

class ListStoreAdapter (val context: MainActivity,
                        val clickItemListener: (DataListStore, Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_TITLE = 0
        private const val TYPE_ITEM = 1
    }

    private var items: List<DataListStore> = listOf()

    override fun getItemCount(): Int {
        return items.size

    }
    override fun getItemViewType(position: Int): Int {
        val title = items[position].title
        var status = 0
        if(title.isNotEmpty()){
            status = 0
        }else {
            status = 1
        }
        return when (status) {
            0 -> TYPE_TITLE
            1 -> TYPE_ITEM
            else -> TYPE_ITEM
        }
    }
    fun updatePosts(items: List<DataListStore>,isLoadMore:Boolean) {
        if(isLoadMore){
            this.items+=items
        }else{
            this.items = items
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> {
                return ViewTitleHolder(LayoutInflater.from(context).inflate(R.layout.item_title_list_store, parent, false))
            }
            TYPE_ITEM -> {
                return ViewItemHolder(LayoutInflater.from(context).inflate(R.layout.item_list_store, parent, false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewTitleHolder -> {
                holder?.txt_title_list_store.setText(items[position].title)
            }
            is ViewItemHolder -> {
                holder?.txt_address.setText(items[position].address)
                holder?.txt_phone.setText(items[position].phone)
                holder?.layout_view_list_store.setOnClickListener(View.OnClickListener {
                    clickItemListener(items[position],position)
                })
            }
        }
    }
    class ViewTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val txt_title_list_store = view.txt_title_list_store!!
    }
    class ViewItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val txt_address = view.txt_address!!
        val txt_phone = view.txt_phone!!
        val layout_view_list_store = view.layout_view_list_store!!
    }
}