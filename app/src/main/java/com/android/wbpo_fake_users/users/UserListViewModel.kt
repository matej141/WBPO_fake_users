package com.android.wbpo_fake_users.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.wbpo_fake_users.ReqresApiInstance
import com.android.wbpo_fake_users.users.retrofit.models.User
import com.android.wbpo_fake_users.users.retrofit.models.UserListResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private var currentPage = 1
    private var totalPages = 1

    private val followStatusRepository = FollowStatusRepository(application)

    init {
        loadUsers(currentPage)
    }

    private fun loadUsers(page: Int) {
        if (page > totalPages) return

        _loading.value = true
        viewModelScope.launch {
            val response: Response<UserListResponse> = ReqresApiInstance.api.getUsers(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    currentPage = it.page
                    totalPages = it.totalPages
                    val updatedUsers = _users.value.orEmpty().toMutableList().apply {
                        addAll(it.data)
                    }
                    _users.value = updatedUsers
                }
                _error.value = null
            } else {
                _error.value = response.errorBody()?.string()
            }
            _loading.value = false
        }
    }

    fun loadNextPage() {
        if (!loading.value!! && currentPage < totalPages) {
            loadUsers(currentPage + 1)
        }
    }

    fun toggleFollowStatus(userId: Int) {
        followStatusRepository.toggleFollowStatus(userId)
        _users.value = _users.value
    }

    fun isUserFollowing(userId: Int): Boolean {
        return followStatusRepository.isUserFollowing(userId)
    }
}
