package com.tunyin.widget.dialog

import android.app.DialogFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import com.tunyin.R
import kotlinx.android.synthetic.main.layout_pay_suc.*



class LessMoneyDialog : DialogFragment() {

    var listener: LuckDrawDialogCallback? = null

    interface LuckDrawDialogCallback {
        fun onLuckDrawing(ll: LinearLayout, tv: TextView, tvTitle: TextView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val view = inflater.inflate(R.layout.layout_less_money, container)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            //            tv_complete_card_num.text = it.getString("cardNum", "")
        }

        rl_contain.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(): LessMoneyDialog {
            val instance = LessMoneyDialog()
            return instance
        }
    }

}