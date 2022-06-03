package com.giffinder.app.presentation.common.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.giffinder.app.R
import com.giffinder.app.core.presentation.AndroidResourceReader

fun AlertDialog.setButtonColor(buttonType: Int, @ColorRes color: Int) {
    this.setOnShowListener {
        this.getButton(buttonType)
            ?.setTextColor(AndroidResourceReader(this.context).getColor(color))
    }
}

fun Context.createAndShowInfoAlertDialog(
    title: String? = null,
    message: String,
    onClickListener: DialogInterface.OnClickListener? = null,
    buttonTextRes: Int = android.R.string.ok,
): AlertDialog {
    val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle)

    builder.apply {
        title?.let {
            setTitle(it)
        }

        setMessage(message)

        setPositiveButton(buttonTextRes, onClickListener)
        setCancelable(false)
    }

    val dialog: AlertDialog = builder.create()

    dialog.setButtonColor(
        buttonType = AlertDialog.BUTTON_POSITIVE,
        color = R.color.teal_200
    )

    dialog.show()

    return dialog
}

fun Fragment.createAndShowInfoAlertDialog(
    title: String? = null,
    message: String,
    onClickListener: DialogInterface.OnClickListener? = null,
    buttonTextRes: Int = android.R.string.ok,
): AlertDialog {
    return this.requireContext().createAndShowInfoAlertDialog(
        title = title,
        message = message,
        onClickListener = onClickListener,
        buttonTextRes = buttonTextRes
    )
}
