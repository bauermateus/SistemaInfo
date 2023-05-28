package com.mbs.sistemainfo.utils.models

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class RegisterScreenState(
    val nameField: Boolean,
    @ColorRes val nameFieldColor: Int,
    @DrawableRes val nameDrawableRes: Int,
    val cpfField: Boolean,
    @ColorRes val cpfFieldColor: Int,
    @DrawableRes val cpfDrawableRes: Int,
    val phoneField: Boolean,
    @ColorRes val phoneFieldColor: Int,
    @DrawableRes val phoneDrawableRes: Int,
    val addressField: Boolean,
    @ColorRes val registerButtonColor: Int
)