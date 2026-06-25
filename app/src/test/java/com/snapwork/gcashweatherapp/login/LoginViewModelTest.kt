package com.snapwork.gcashweatherapp.login

import com.snapwork.gcashweatherapp.presentation.login.LoginViewModel
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun loginViewModel_isCreated() {

        val viewModel = LoginViewModel(
            sessionManager = mockk(relaxed = true),
            repository = TODO()
        )

        assertNotNull(viewModel)

    }

}