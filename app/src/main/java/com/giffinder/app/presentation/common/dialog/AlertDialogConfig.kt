package com.giffinder.app.presentation.common.dialog

import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.giffinder.app.R

data class AlertDialogConfig(
    val title: String? = null,
    val message: String,
    @StringRes val positiveButtonTextRes: Int,
    @StringRes val negativeButtonTextRes: Int = R.string.action_cancel,
    @StringRes val neutralButtonTextRes: Int? = null,
    val onClickPositiveButtonListener: DialogInterface.OnClickListener,
    val onClickNegativeButtonListener: DialogInterface.OnClickListener? = null,
    val onClickNeutralButtonListener: DialogInterface.OnClickListener? = null,
    @ColorRes val positiveColor: Int = R.color.teal_700,
    @ColorRes val negativeColor: Int = R.color.black,
    @ColorRes val neutralColor: Int = R.color.teal_200
)
