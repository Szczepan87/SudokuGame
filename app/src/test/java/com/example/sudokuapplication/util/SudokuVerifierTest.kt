package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Board
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SudokuVerifierTest {

    private val sudokuVerifier = SudokuVerifier()

    private val goodRowsWrongSudoku = Board(
        listOf<List<Int>>(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )
    )

    private val goodColumnsWrongSudoku = Board(
        listOf<List<Int>>(
            listOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
            listOf(2, 2, 2, 2, 2, 2, 2, 2, 2),
            listOf(3, 3, 3, 3, 3, 3, 3, 3, 3),
            listOf(4, 4, 4, 4, 4, 4, 4, 4, 4),
            listOf(5, 5, 5, 5, 5, 5, 5, 5, 5),
            listOf(6, 6, 6, 6, 6, 6, 6, 6, 6),
            listOf(7, 7, 7, 7, 7, 7, 7, 7, 7),
            listOf(8, 8, 8, 8, 8, 8, 8, 8, 8),
            listOf(9, 9, 9, 9, 9, 9, 9, 9, 9)
        )
    )

    private val goodSquaresWrongSudoku = Board(
        listOf<List<Int>>(
            listOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            listOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            listOf(7, 8, 9, 7, 8, 9, 7, 8, 9),
            listOf(1, 2, 3, 4, 4, 4, 1, 2, 3),
            listOf(4, 5, 6, 5, 5, 5, 4, 5, 6),
            listOf(7, 8, 9, 6, 6, 6, 7, 8, 9),
            listOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            listOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            listOf(7, 8, 9, 7, 8, 9, 7, 8, 9)
        )
    )

    private val properlySolvedSudoku = Board(
        listOf<List<Int>>(
            listOf(4, 9, 8, 2, 6, 3, 1, 5, 7),
            listOf(1, 3, 6, 5, 7, 8, 2, 9, 4),
            listOf(5, 7, 2, 4, 9, 1, 6, 8, 3),
            listOf(8, 1, 9, 3, 4, 2, 7, 6, 5),
            listOf(6, 5, 3, 8, 1, 7, 9, 4, 2),
            listOf(2, 4, 7, 6, 5, 9, 8, 3, 1),
            listOf(7, 6, 1, 9, 3, 5, 4, 2, 8),
            listOf(9, 8, 5, 1, 2, 4, 3, 7, 6),
            listOf(3, 2, 4, 7, 8, 6, 5, 1, 9)
        )
    )

    @Test
    fun `should return true with good input`() {
        assertTrue(sudokuVerifier.isSudokuSolved(properlySolvedSudoku))
    }

    @Test
    fun `should return false with good rows`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodRowsWrongSudoku))
    }

    @Test
    fun `should return false with good columns`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodColumnsWrongSudoku))
    }

    @Test
    fun `should return false with good squares`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodSquaresWrongSudoku))
    }
}