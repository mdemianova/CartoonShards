package com.ignation.cartoonshards.data

import com.ignation.cartoonshards.data.QuestionStorage.allQuestionsList
import com.ignation.cartoonshards.data.QuestionStorage.setAnswersList
import org.junit.Assert.assertEquals
import org.junit.Test

class QuestionStorageTest {
    @Test
    fun setAnswersList_instantiate_answerNumberEqualsQuestion() {
        assertEquals(allQuestionsList.size, setAnswersList().size)
    }
}