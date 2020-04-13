package com.example.kleverfruits.ui.dialog.bonus

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kleverfruits.R
import com.example.kleverfruits.base.BaseDialogFragment
import com.example.kleverfruits.model.bonus.DataBonus
import com.example.kleverfruits.model.order.DataOrder
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail
import com.example.kleverfruits.ui.dialog.order.OrderAdapter
import com.example.kleverfruits.ui.dialog.order.OrderDialogFragment
import com.example.kleverfruits.ui.dialog.order.OrderPresenter
import com.example.kleverfruits.ui.dialog.order.OrderView
import com.example.kleverfruits.ui.dialog.orderDetail.OrderDetailDialogFragment
import com.example.kleverfruits.ui.main.MainActivity
import com.example.kleverfruits.utils.`object`.BonusUtil
import com.example.kleverfruits.utils.`object`.LoginDialogs
import kotlinx.android.synthetic.main.fragment_klever_bonus.view.*

class BonusDialogFragment : BaseDialogFragment<BonusPresenter>(), BonusView {
    override fun instantiatePresenter(): BonusPresenter {
        return BonusPresenter(this)
    }

    override fun getContext(): Context {
        return activity!!.getContext()
    }

    var activity : MainActivity? = null
    var bonusKleverAdapter : BonusKleverAdapter? = null
    var bonusPartnerAdapter : BonusPartnerAdapter? = null
    var listDataBonus : ArrayList<DataBonus> = ArrayList()
    var txt_back_bonus : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity = arguments!!.getSerializable("activity") as MainActivity?
        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_FRAME
        val theme = R.style.DialogCustom
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_klever_bonus, container, false)

        rootView.layout_from_partner.setOnClickListener(View.OnClickListener {
            rootView.txt_from_partner.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.txt_from_klever.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.layout_from_partner.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView.layout_from_klever.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView?.ryv_klever_bonus!!.adapter = bonusPartnerAdapter
            bonusPartnerAdapter!!.updatePosts(listDataBonus,false)
        })
        rootView.layout_from_klever.setOnClickListener(View.OnClickListener {
            rootView.txt_from_partner.setTextColor(activity!!.resources.getColor(R.color.text_black_333))
            rootView.txt_from_klever.setTextColor(activity!!.resources.getColor(R.color.background_main))
            rootView.layout_from_partner.setBackgroundResource(R.drawable.custom_border_bottom_white)
            rootView.layout_from_klever.setBackgroundResource(R.drawable.custom_border_bottom_violet)
            rootView?.ryv_klever_bonus!!.adapter = bonusKleverAdapter
            bonusKleverAdapter!!.updatePosts(listDataBonus,false)
        })
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",1,"https://www.google.co.in/")
        )
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",2,"https://www.google.co.in/")
        )
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",3,"https://www.google.co.in/")
        )
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",1,"https://www.google.co.in/")
        )
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",1,"https://www.google.co.in/")
        )
        listDataBonus.add(
            DataBonus("","","Ưu đãi mã khuyến mãi 100.000đ","Momo","19/11/2019","50",2,"https://www.google.co.in/")
        )
        bonusKleverAdapter = BonusKleverAdapter(activity!!, { itemDto: DataBonus, position: Int ->
            BonusUtil.showDialogBonusDetail(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                BonusUtil.showDialogConvertPoint(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                    dialogSmall.dismiss()
                })
            })
        },{ itemDto: DataBonus, position: Int ->
            BonusUtil.showDialogConvertPoint(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                dialogSmall.dismiss()
            })
        })

        bonusPartnerAdapter = BonusPartnerAdapter(activity!!, { itemDto: DataBonus, position: Int ->
            BonusUtil.showDialogBonusDetail(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                BonusUtil.showDialogConvertPoint(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                    dialogSmall.dismiss()
                })
            })
        },{ itemDto: DataBonus, position: Int ->
            BonusUtil.showDialogConvertPoint(activity!!,itemDto,{ dialogSmall: Dialog, email : String ->
                dialogSmall.dismiss()
            })
        })


        rootView?.ryv_klever_bonus!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL!!, false)
//        orderAdapter!!.updatePosts(listDataOrder,false)

        rootView.layout_from_partner.callOnClick()

        rootView.img_back_klever_bonus.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })
        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_BACK){
//                dialog.dismiss()
            }
            false
        })
        return rootView
    }

    companion object {
        fun newInstance(activity: MainActivity): BonusDialogFragment {
            val f = BonusDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("activity", activity)
            f.arguments = args

            return f
        }
    }
}