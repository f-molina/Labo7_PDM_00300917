package com.example.labo7.Viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.labo7.Repository.GithubRepoRepository
import com.example.labo7.database.GithubRepo
import com.example.labo7.database.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubRepoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: GithubRepoRepository
    init {
        val repoDao = RoomDB.getInstance(application).repoDao()
        repository = GithubRepoRepository(repoDao)
    }

    fun insert(repo: GithubRepo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(repo)
    }

    fun getAll(): LiveData<List<GithubRepo>> = repository.getAll()

    fun nukeAll() = repository.nuke()
}