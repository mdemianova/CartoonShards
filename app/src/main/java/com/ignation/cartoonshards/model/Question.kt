package com.ignation.cartoonshards.model

import androidx.annotation.DrawableRes

data class Question(
    @DrawableRes val image: Int,
    val answer: Int
)
