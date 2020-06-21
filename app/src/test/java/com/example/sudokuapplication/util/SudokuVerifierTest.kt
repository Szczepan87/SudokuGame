package com.example.sudokuapplication.util

import com.example.sudokuapplication.model.Board
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SudokuVerifierTest {

    private val sudokuVerifier = SudokuVerifier()

    private val goodRowsWrongSudoku = Board(
        mutableListOf(
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )
    )

    private val emptyBoardSudoku = Board(
        mutableListOf(
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        )
    )

    private val partiallySolvedSudo = Board(
        mutableListOf(
            mutableListOf(4, 9, 8, 2, 6, 3, 1, 5, 7),
            mutableListOf(1, 3, 6, 0, 7, 8, 2, 9, 4),
            mutableListOf(5, 7, 2, 4, 9, 1, 6, 8, 3),
            mutableListOf(8, 1, 9, 3, 4, 2, 7, 6, 5),
            mutableListOf(6, 5, 3, 0, 1, 7, 0, 4, 2),
            mutableListOf(2, 4, 7, 6, 5, 9, 8, 3, 1),
            mutableListOf(7, 6, 1, 9, 3, 5, 4, 2, 8),
            mutableListOf(9, 8, 5, 1, 2, 0, 3, 7, 6),
            mutableListOf(3, 2, 4, 7, 8, 6, 5, 1, 9)
        )
    )

    private val goodColumnsWrongSudoku = Board(
        mutableListOf(
            mutableListOf(1, 1, 1, 1, 1, 1, 1, 1, 1),
            mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2),
            mutableListOf(3, 3, 3, 3, 3, 3, 3, 3, 3),
            mutableListOf(4, 4, 4, 4, 4, 4, 4, 4, 4),
            mutableListOf(5, 5, 5, 5, 5, 5, 5, 5, 5),
            mutableListOf(6, 6, 6, 6, 6, 6, 6, 6, 6),
            mutableListOf(7, 7, 7, 7, 7, 7, 7, 7, 7),
            mutableListOf(8, 8, 8, 8, 8, 8, 8, 8, 8),
            mutableListOf(9, 9, 9, 9, 9, 9, 9, 9, 9)
        )
    )

    private val goodSquaresWrongSudoku = Board(
        mutableListOf(
            mutableListOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            mutableListOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            mutableListOf(7, 8, 9, 7, 8, 9, 7, 8, 9),
            mutableListOf(1, 2, 3, 4, 4, 4, 1, 2, 3),
            mutableListOf(4, 5, 6, 5, 5, 5, 4, 5, 6),
            mutableListOf(7, 8, 9, 6, 6, 6, 7, 8, 9),
            mutableListOf(1, 2, 3, 1, 2, 3, 1, 2, 3),
            mutableListOf(4, 5, 6, 4, 5, 6, 4, 5, 6),
            mutableListOf(7, 8, 9, 7, 8, 9, 7, 8, 9)
        )
    )

    private val properlySolvedSudoku = Board(
        mutableListOf(
            mutableListOf(4, 9, 8, 2, 6, 3, 1, 5, 7),
            mutableListOf(1, 3, 6, 5, 7, 8, 2, 9, 4),
            mutableListOf(5, 7, 2, 4, 9, 1, 6, 8, 3),
            mutableListOf(8, 1, 9, 3, 4, 2, 7, 6, 5),
            mutableListOf(6, 5, 3, 8, 1, 7, 9, 4, 2),
            mutableListOf(2, 4, 7, 6, 5, 9, 8, 3, 1),
            mutableListOf(7, 6, 1, 9, 3, 5, 4, 2, 8),
            mutableListOf(9, 8, 5, 1, 2, 4, 3, 7, 6),
            mutableListOf(3, 2, 4, 7, 8, 6, 5, 1, 9)
        )
    )

    @Test
    fun `should return true with good input`() {
        assertTrue(sudokuVerifier.isSudokuSolved(properlySolvedSudoku.board.toMutableListOfCells()))
    }

    @Test
    fun `should return false with empty board`() {
        assertFalse(sudokuVerifier.isSudokuSolved(emptyBoardSudoku.board.toMutableListOfCells()))
    }

    @Test
    fun `should return false with partially solved board`() {
        assertFalse(sudokuVerifier.isSudokuSolved(partiallySolvedSudo.board.toMutableListOfCells()))
    }

    @Test
    fun `should return false with good rows`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodRowsWrongSudoku.board.toMutableListOfCells()))
    }

    @Test
    fun `should return false with good columns`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodColumnsWrongSudoku.board.toMutableListOfCells()))
    }

    @Test
    fun `should return false with good squares`() {
        assertFalse(sudokuVerifier.isSudokuSolved(goodSquaresWrongSudoku.board.toMutableListOfCells()))
    }
}