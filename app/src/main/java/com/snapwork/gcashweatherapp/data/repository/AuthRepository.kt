package com.snapwork.gcashweatherapp.data.repository

import com.snapwork.gcashweatherapp.data.local.dao.UserDao
import com.snapwork.gcashweatherapp.data.local.entity.UserEntity
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun register(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun getUser(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }
}