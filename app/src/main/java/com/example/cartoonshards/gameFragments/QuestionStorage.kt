package com.example.cartoonshards.gameFragments

import com.example.cartoonshards.R

data class Question(val image: Int, val answer: Int)

val allQuestionsList: List<Question> = listOf(
    Question(
        image = R.drawable.tom_jerry_img,
        answer = R.string.tom_and_jerry,
    ),
    Question(
        image = R.drawable.lilo_stitch_img,
        answer = R.string.lilo_and_stitch
    ),
    Question(
        image = R.drawable.finding_nemo_img,
        answer = R.string.finding_nemo
    )
)

val allAnswersList: List<Int> = listOf(
    R.string.lilo_and_stitch,
    R.string.tom_and_jerry,
    R.string.finding_nemo,
    R.string.frozen,
    R.string.lion_king,
    R.string.ladybug_and_cat_noir
)