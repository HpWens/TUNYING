package com.ccb.cdialog.`interface`

import android.view.View
import inno.tmsclient.widget.dialog.BaseDialog

interface OnDialogButtonClickListener{
    fun onClick(dialog: BaseDialog, v : View)

}