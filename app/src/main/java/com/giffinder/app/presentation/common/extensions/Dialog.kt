package com.giffinder.app.presentation.common.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.giffinder.app.R
import com.giffinder.app.core.presentation.AndroidResourceReader
import com.giffinder.app.presentation.common.dialog.AlertDialogConfig

fun AlertDialog.setButtonColor(buttonType: Int, @ColorRes color: Int) {
    this.setOnShowListener {
        this.getButton(buttonType)
            ?.setTextColor(AndroidResourceReader(this.context).getColor(color))
    }
}

fun DialogInterface.setButtonsColor(
    dialog: AlertDialog,
    @ColorRes positiveColor: Int,
    @ColorRes negativeColor: Int,
    @ColorRes neutralColor: Int
) {
    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        ?.setTextColor(
            ContextCompat.getColor(
                dialog.context,
                positiveColor
            )
        )

    dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
        ?.setTextColor(
            ContextCompat.getColor(
                dialog.context,
                neutralColor
            )
        )

    dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        ?.setTextColor(
            ContextCompat.getColor(
                dialog.context,
                negativeColor
            )
        )
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

fun Fragment.createAndShowQuestionDialog(
    config: AlertDialogConfig
): AlertDialog {
    val builder = AlertDialog.Builder(requireActivity(), R.style.AlertDialogStyle)

    builder.apply {
        config.title?.let {
            this.setTitle(config.title)
        }

        setMessage(config.message)

        setPositiveButton(config.positiveButtonTextRes, config.onClickPositiveButtonListener)
        setNegativeButton(config.negativeButtonTextRes, config.onClickNegativeButtonListener)

        if (config.neutralButtonTextRes != null && config.onClickNeutralButtonListener != null) {
            setNeutralButton(config.neutralButtonTextRes, config.onClickNeutralButtonListener)
        }
    }

    val alertDialog = builder.create()

    alertDialog.setOnShowListener { dialog ->
        dialog.setButtonsColor(
            dialog = alertDialog,
            positiveColor = config.positiveColor,
            neutralColor = config.neutralColor,
            negativeColor = config.negativeColor
        )
    }

    alertDialog.show()

    return alertDialog
}
