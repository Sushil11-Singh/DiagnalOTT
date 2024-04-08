package com.diagnal.diagnalott.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diagnal.diagnalott.modal.ContentListDTO
import com.diagnal.diagnalott.repositry.ContentListRepo
import kotlinx.coroutines.launch

class ContentListViewModal() : ViewModel() {
    private lateinit var repository: ContentListRepo
    private val _myData = MutableLiveData<ContentListDTO>()
    private val _isLoading = MutableLiveData<Boolean>()
    val myData: LiveData<ContentListDTO> get() = _myData
    fun init(repo: ContentListRepo) {
        repository = repo
    }
    fun loadData(pageNumber: Int) {
        _isLoading.value = true // Set loading state to true before fetching data
        viewModelScope.launch {
            val newData = repository.loadDataFromJson(pageNumber)
            newData.let {
                _myData.value = it
            }
        }
    }
}