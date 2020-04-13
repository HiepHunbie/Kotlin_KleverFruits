package com.example.kleverfruits.ui.dialog.memberInfo

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.kleverfruits.R
import com.example.kleverfruits.model.image.ImageModel

class SlidingImageMemberAdapter(context: Context, private val imageModelArrayList: ArrayList<ImageModel>) : PagerAdapter(),
    Parcelable {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    constructor(parcel: Parcel) : this(
        TODO("context"),
        TODO("imageModelArrayList")
    )


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimages_member_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image_member) as ImageView


        imageView.setImageResource(imageModelArrayList[position].getImageDrawables())

        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlidingImageMemberAdapter> {
        override fun createFromParcel(parcel: Parcel): SlidingImageMemberAdapter {
            return SlidingImageMemberAdapter(parcel)
        }

        override fun newArray(size: Int): Array<SlidingImageMemberAdapter?> {
            return arrayOfNulls(size)
        }
    }

}
