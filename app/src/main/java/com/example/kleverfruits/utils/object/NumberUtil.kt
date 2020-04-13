package com.example.kleverfruits.utils.`object`

import android.content.Context
import com.example.kleverfruits.R
import java.math.RoundingMode
import java.text.DecimalFormat

object NumberUtil {
    fun convertNumberToPrice(cost : Double, context: Context): String{
        val df = DecimalFormat("#,###")
        df.roundingMode = RoundingMode.CEILING
        return df.format(cost).toString() + " " + context.getString(R.string.money_unit)
    }
}