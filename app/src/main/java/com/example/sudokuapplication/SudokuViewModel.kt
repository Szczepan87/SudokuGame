package com.example.sudokuapplication

import android.util.Log
import androidx.lifecycle.*
import com.example.sudokuapplication.model.Board
import com.example.sudokuapplication.repository.SudokuRepository
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: SudokuRepository) : ViewModel() {

    private val _board = MutableLiveData<Board>()
    val board: LiveData<Board>
        get() = _board
    private val databaseObserver = Observer<Board> { _board.postValue(it) }

    init {
        if (isDatabaseEmpty()) {
            loadRemoteBoardData()
        }
        repository.databaseBoard().observeForever { databaseObserver }
    }

    private fun isDatabaseEmpty(): Boolean {
        return repository.databaseBoard().value == null ||
                repository.databaseBoard().value == Board(listOf<List<Int>>())
    }

    fun loadRemoteBoardData() {
        viewModelScope.launch {
            val remoteBoard = repository.loadRemoteBoard()
            Log.d("VIEW MODEL", "Board: ${remoteBoard.toString()}")
            repository.saveBoardToDatabase(remoteBoard) }
    }

    override fun onCleared() {
        repository.databaseBoard().removeObserver(databaseObserver)
        super.onCleared()
    }
}