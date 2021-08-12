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
    ),
    Question(
        image = R.drawable.ainbo,
        answer = R.string.ainbo
    ),
    Question(
        image = R.drawable.luca,
        answer = R.string.luca
    ),
    Question(
        image = R.drawable.puss_in_boots,
        answer = R.string.puss_in_boots
    ),
    Question(
        image = R.drawable.tangled,
        answer = R.string.tangled
    ),
    Question(
        image = R.drawable.vivo,
        answer = R.string.vivo
    ),
    Question(
        image = R.drawable.wall_e,
        answer = R.string.wall_e
    ),
    Question(
        image = R.drawable.wish_dragon,
        answer = R.string.wish_dragon
    ),
    Question(
        image = R.drawable.despicable_me,
        answer = R.string.despicable_me
    ),
    Question(
        image = R.drawable.cars,
        answer = R.string.cars
    ),
    Question(
        image = R.drawable.shrek,
        answer = R.string.shrek
    ),
    Question(
        image = R.drawable.hotel_transylvania,
        answer = R.string.hotel_transylvania
    ),
    Question(
        image = R.drawable.monsters_inc,
        answer = R.string.monsers_inc
    ),
    Question(
        image = R.drawable.how_to_train_your_dragon,
        answer = R.string.how_to_train_your_dragon
    ),
    Question(
        image = R.drawable.kung_fu_panda,
        answer = R.string.kung_fu_panda
    )
)

val allAnswersList: List<Int> = listOf(
    R.string.lilo_and_stitch,
    R.string.tom_and_jerry,
    R.string.finding_nemo,
    R.string.frozen,
    R.string.lion_king,
    R.string.ladybug_and_cat_noir,
    R.string.ainbo,
    R.string.luca,
    R.string.puss_in_boots,
    R.string.tangled,
    R.string.vivo,
    R.string.wall_e,
    R.string.wish_dragon,
    R.string.despicable_me,
    R.string.cars,
    R.string.shrek,
    R.string.hotel_transylvania,
    R.string.monsers_inc,
    R.string.how_to_train_your_dragon,
    R.string.kung_fu_panda
)