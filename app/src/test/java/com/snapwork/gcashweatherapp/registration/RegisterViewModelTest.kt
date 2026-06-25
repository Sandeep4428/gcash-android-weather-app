package com.snapwork.gcashweatherapp.registration

import com.snapwork.gcashweatherapp.presentation.registration.RegisterViewModel
import io.mockk.mockk
import org.junit.Assert.assertNotNull
import org.junit.Test

class RegisterViewModelTest {

    @Test
    fun registerViewModel_isCreated() {

        val viewModel = RegisterViewModel(
            repository = TODO()
        )

        assertNotNull(viewModel)

    }

}