package com.example.kleverfruits.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//  THIS SOURCE CODE IS THE INTELLECTUAL PROPERTY OF TELESENSE, INC. This source
//  code cannot be copied, modified, printed, reproduced or used in any way,
//  shape or form without prior permission from Telesense, Inc. ANY ATTEMPTS TO
//  COPY, MODIFY, PRINT, REPRODUCE OR USE THIS SOURCE CODE WITHOUT PERMISSION
//  FROM TELESENSE, INC ARE STRICTLY PROHIBITED.
//
//  Anyone creating, updating, or viewing this source code in any way, shape
//  or form is bound by this copyright message, including Telesense, Inc
//  employees, contractors, partners, or any other associated or non-associated
//  person, entity or a system.
//
//  Copyright 2019 Telesense, Inc., All rights reserved.

abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (recyclerView != null) {
            super.onScrolled(recyclerView, dx, dy)
        }

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }//                    && totalItemCount >= ClothesFragment.itemsCount
        }
    }
    abstract fun loadMoreItems()
}