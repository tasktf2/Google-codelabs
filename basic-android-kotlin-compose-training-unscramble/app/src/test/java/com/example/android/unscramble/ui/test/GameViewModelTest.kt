/*
 * Copyright (c)2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.getUnscrambledWord
import com.example.android.unscramble.ui.GameViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private var viewModel: GameViewModel = GameViewModel()

    /*
    thingUnderTest = gameViewModel
    TriggerOfTest = CorrectWordGuessed
    ResultOfTest = ScoreUpdatedAndErrorFlagUnset
     */
    @Test
    fun `gameViewModel, correct word guessed, score updated and error flag unset`() {
        var currentGameState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameState = viewModel.uiState.value

        // Assert that checkUserGuess() method updates isGuessedWordWrong is updated correctly.
        assertFalse(currentGameState.isGuessedWordWrong)

        // Assert that score is updated correctly.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameState.score)
    }

    @Test
    fun `gameViewModel, incorrect word guess, error flag set`() {
        val incorrectPlayerWord = "and"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameState = viewModel.uiState.value

        assertEquals(0, currentGameState.score)
        assertTrue(currentGameState.isGuessedWordWrong)
    }

    @Test
    fun `gameViewModel, initialization, first word loaded`() {

        val currentGameState = viewModel.uiState.value
        val unscrambledWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        //inc() calls only if word accepted or skipped, dunno why author said here should be 1 as default state
        assertEquals(currentGameState.currentWordCount, 0)
        assertEquals(currentGameState.score, 0)
        assertFalse(currentGameState.isGuessedWordWrong)
        assertFalse(currentGameState.isGameOver)
        assertNotEquals(unscrambledWord, currentGameState.currentScrambledWord)
    }

    @Test
    fun `gameViewModel, all words guessed, uiState updated correctly`() {
        //author rly haven`t checked state initialization
        var expectedScore = 0
        var currentGameState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)
            assertEquals(expectedScore, currentGameState.score)
        }
        //here shouldn`t be incrementation
        assertEquals(MAX_NO_OF_WORDS, currentGameState.currentWordCount + 1)
        assertTrue(currentGameState.isGameOver)
    }

    @Test
    fun `gameViewModel, word skipped, score unchanged and word count increased`() {
        var currentGameState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameState = viewModel.uiState.value

        val lastWordCount = currentGameState.currentWordCount
        viewModel.skipWord()
        currentGameState = viewModel.uiState.value
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameState.score)
        assertEquals(lastWordCount + 1, currentGameState.currentWordCount)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}
