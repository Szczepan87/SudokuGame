package com.example.sudokuapplication.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.model.Cell
import com.example.sudokuapplication.repository.SudokuRepository
import com.example.sudokuapplication.util.getCell
import com.example.sudokuapplication.util.toMutableListOfCells
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: SudokuRepository) : ViewModel() {

    private val _board = MutableLiveData<MutableList<Cell>>()
    val board: LiveData<MutableList<Cell>>
        get() = _board

    private val _selectedFieldLiveData = MutableLiveData<Pair<Int, Int>>()
    val selectedFieldLiveData: LiveData<Pair<Int, Int>>
        get() = _selectedFieldLiveData

    init {
        _selectedFieldLiveData.postValue(Pair(-1, -1))
        _board.postValue(mutableListOf())
        loadRemoteBoardData()
//        if (isDatabaseEmpty()) {
//            loadRemoteBoardData()
//            // TODO overwrites constantly
//        } else _board.postValue(repository.databaseBoard())
    }

    private fun isDatabaseEmpty(): Boolean {
        return repository.databaseBoard().isNullOrEmpty()
    }

    fun loadRemoteBoardData() {
        viewModelScope.launch {
            val remoteBoard: Board = repository.loadRemoteBoard()
            Log.d("VIEW MODEL", "Loaded $remoteBoard")
            repository.saveBoardToDatabase(remoteBoard.board.toMutableListOfCells())
            Log.d("VIEW MODEL", "Saved ${remoteBoard.board}")
            _board.postValue(remoteBoard.board.toMutableListOfCells())
            Log.d("VIEW MODEL", "Posted value ${remoteBoard.board}")
        }
    }

    fun updateSelectedField(row: Int, column: Int) {
        if (board.value?.getCell(row, column)?.isEditable?.not() == true) return
        _selectedFieldLiveData.postValue(Pair(row, column))
    }

    fun updateFieldValue(value: Int) {
        val mutableBoard: MutableList<Cell> = _board.value ?: mutableListOf()
        val selectedFieldCoord = selectedFieldLiveData.value ?: Pair(0, 0)
        mutableBoard.getCell(selectedFieldCoord.first, selectedFieldCoord.second).value = value
        _board.postValue(mutableBoard)
    }
}