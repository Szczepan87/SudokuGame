package com.example.sudokuapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.model.Cell
import com.example.sudokuapplication.repository.SudokuRepositoryImpl
import com.example.sudokuapplication.util.OUTSIDE_BOARD
import com.example.sudokuapplication.util.SudokuVerifier
import com.example.sudokuapplication.util.getCell
import com.example.sudokuapplication.util.toMutableListOfCells
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: SudokuRepositoryImpl) : ViewModel() {

    private val _board = MutableLiveData<MutableList<Cell>>()
    val board: LiveData<MutableList<Cell>>
        get() = _board

    private val _selectedFieldLiveData = MutableLiveData<Pair<Int, Int>>()
    val selectedFieldLiveData: LiveData<Pair<Int, Int>>
        get() = _selectedFieldLiveData

    init {
        _selectedFieldLiveData.postValue(Pair(OUTSIDE_BOARD, OUTSIDE_BOARD))
        _board.postValue(mutableListOf())
        if (isDatabaseEmpty()) {
            loadRemoteBoardData()
        } else _board.postValue(repository.databaseBoard())
    }

    private fun isDatabaseEmpty(): Boolean {
        return repository.databaseBoard().isNullOrEmpty()
    }

    fun loadRemoteBoardData() {
        viewModelScope.launch {
            val remoteBoard: Board = try {
                repository.loadRemoteBoard()
            } catch (e: Exception) {
                Board(mutableListOf())
            }
            repository.saveBoardToDatabase(remoteBoard.board.toMutableListOfCells())
            _board.postValue(remoteBoard.board.toMutableListOfCells())
        }
    }

    fun updateSelectedField(row: Int, column: Int) {
        if (board.value?.getCell(row, column)?.isEditable?.not() == true) return
        _selectedFieldLiveData.postValue(Pair(row, column))
    }

    fun updateFieldValue(value: Int) {
        val mutableBoard: MutableList<Cell> = _board.value ?: mutableListOf()
        val selectedFieldCoord = selectedFieldLiveData.value ?: Pair(OUTSIDE_BOARD, OUTSIDE_BOARD)
        mutableBoard.getCell(selectedFieldCoord.first, selectedFieldCoord.second).value = value
        _board.postValue(mutableBoard)
        viewModelScope.launch { repository.saveBoardToDatabase(_board.value ?: mutableListOf()) }
    }

    fun isSudokuSolved(): Boolean {
        return SudokuVerifier().isSudokuSolved(board.value ?: mutableListOf())
    }
}