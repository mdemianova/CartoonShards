package com.example.cartoonshards.gameFragments

import com.example.cartoonshards.R

data class Question(val image: Int, val answer: Int)

val allQuestionsList: List<Question> = listOf(
    Question(
        image = R.drawable.tom_jerry,
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
        answer = R.string.monsters_inc
    ),
    Question(
        image = R.drawable.how_to_train_your_dragon,
        answer = R.string.how_to_train_your_dragon
    ),
    Question(
        image = R.drawable.kung_fu_panda,
        answer = R.string.kung_fu_panda
    ),
    Question(
        image = R.drawable.frozen,
        answer = R.string.frozen
    ),
    Question(
        image = R.drawable.lion_king,
        answer = R.string.lion_king
    ),
    Question(
        image = R.drawable.monsters_university,
        answer = R.string.monsters_university
    ),
    Question(
        image = R.drawable.ratatouille,
        answer = R.string.ratatouille
    ),
    Question(
        image = R.drawable.rio,
        answer = R.string.rio
    ),
    Question(
        image = R.drawable.rise_guardians,
        answer = R.string.rise_guardians
    ),
    Question(
        image = R.drawable.brave,
        answer = R.string.brave
    ),
    Question(
        image = R.drawable.wreck_it_ralph,
        answer = R.string.wreck_it_ralph
    ),
    Question(
        image = R.drawable.ice_age,
        answer = R.string.ice_age
    ),
    Question(
        image = R.drawable.up,
        answer = R.string.up
    ),
    Question(
        image = R.drawable.the_incredibles,
        answer = R.string.the_incredibles
    ),
    Question(
        image = R.drawable.the_smurfs,
        answer = R.string.the_smurfs
    ),
    Question(
        image = R.drawable.zootopia,
        answer = R.string.zootopia
    ),
    Question(
        image = R.drawable.shark_tale,
        answer = R.string.shark_tale
    ),
    Question(
        image = R.drawable.bolt,
        answer = R.string.bolt
    ),
    Question(
        image = R.drawable.madagascar,
        answer = R.string.madagascar
    ),
    Question(
        image = R.drawable.hercules,
        answer = R.drawable.hercules
    ),
    Question(
        image = R.drawable.moana,
        answer = R.string.moana
    ),
    Question(
        image = R.drawable.minions,
        answer = R.string.minions
    ),
    Question(
        image = R.drawable.beauty_beast,
        answer = R.string.beauty_beast
    ),
    Question(
        image = R.drawable.boss_baby,
        answer = R.string.boss_baby
    ),
    Question(
        image = R.drawable.jungle_book,
        answer = R.string.jungle_book
    ),
    Question(
        image = R.drawable.toy_story,
        answer = R.string.toy_story
    ),
    Question(
        image = R.drawable.alvin_chipmunks,
        answer = R.string.alvin_chipmunks
    ),
    Question(
        image = R.drawable.planes,
        answer = R.string.planes
    ),
    Question(
        image = R.drawable.aladdin,
        answer = R.string.aladdin
    ),
    Question(
        image = R.drawable.balto,
        answer = R.string.balto
    ),
    Question(
        image = R.drawable.little_mermaid,
        answer = R.string.little_mermaid
    ),
    Question(
        image = R.drawable.over_the_hedge,
        answer = R.string.over_the_hedge
    ),
    Question(
        image = R.drawable.cinderella,
        answer = R.string.cinderella
    ),
    Question(
        image = R.drawable.angry_birds,
        answer = R.string.angry_birds
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
    R.string.monsters_inc,
    R.string.how_to_train_your_dragon,
    R.string.kung_fu_panda,
    R.string.monsters_university,
    R.string.ratatouille,
    R.string.rio,
    R.string.rise_guardians,
    R.string.brave,
    R.string.wreck_it_ralph,
    R.string.ice_age,
    R.string.up,
    R.string.the_incredibles,
    R.string.the_smurfs,
    R.string.zootopia,
    R.string.shark_tale,
    R.string.bolt,
    R.string.madagascar,
    R.string.hercules,
    R.string.moana,
    R.string.minions,
    R.string.beauty_beast,
    R.string.boss_baby,
    R.string.jungle_book,
    R.string.toy_story,
    R.string.alvin_chipmunks,
    R.string.planes,
    R.string.aladdin,
    R.string.balto,
    R.string.little_mermaid,
    R.string.over_the_hedge,
    R.string.cinderella,
    R.string.angry_birds
)