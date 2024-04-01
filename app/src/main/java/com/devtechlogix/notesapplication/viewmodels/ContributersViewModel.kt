package com.devtechlogix.notesapplication.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devtechlogix.notesapplication.models.Contributers
import com.devtechlogix.notesapplication.repository.GithubRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @Author: Abdul Rehman
 * @Date: 31/03/2024.
 */

@HiltViewModel
class ContributersViewModel @Inject constructor(private val githubRepo: GithubRepo) : ViewModel() {
    val contributers: StateFlow<List<Contributers>>
        get() = githubRepo.categories

    init {
        viewModelScope.launch {
            githubRepo.getContributors()
        }
    }

}