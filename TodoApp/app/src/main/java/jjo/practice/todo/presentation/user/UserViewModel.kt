package jjo.practice.todo.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jjo.practice.todo.data.model.UserEntity

class UserViewModel : ViewModel() {
    private val liveData : MutableLiveData<UserEntity> by lazy {
        MutableLiveData<UserEntity>(UserEntity("gudwh14"))
    }

    fun getLiveData() : LiveData<UserEntity> {
        return liveData
    }

    fun getValue() : UserEntity? {
        return liveData.value
    }

    fun setValue(data : UserEntity) {
        liveData.value = data
    }
}
