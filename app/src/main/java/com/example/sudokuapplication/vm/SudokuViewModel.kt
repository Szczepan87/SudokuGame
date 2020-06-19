package com.example.sudokuapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.repository.SudokuRepository
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: SudokuRepository) : ViewModel() {

    private val _board = repository.databaseBoard()
    val board: LiveData<Board>
        get() = _board

    private val _selectedFieldLiveData = MutableLiveData<Pair<Int, Int>>()
    val selectedFieldLiveData: LiveData<Pair<Int, Int>>
        get() = _selectedFieldLiveData

    init {
        _selectedFieldLiveData.postValue(Pair(-1, -1))
        if (isDatabaseEmpty()) {
            loadRemoteBoardData()
            // TODO overwrites constantly
        }
    }

    private fun isDatabaseEmpty(): Boolean {
        return _board.value == null ||
                _board.value == Board(listOf<List<Int>>())
    }

    fun loadRemoteBoardData() {
        viewModelScope.launch {
            val remoteBoard = repository.loadRemoteBoard()
            repository.saveBoardToDatabase(remoteBoard)
        }
    }

    fun updateSelectedField(row: Int, column: Int) {
        _selectedFieldLiveData.postValue(Pair(row, column))
    }
}