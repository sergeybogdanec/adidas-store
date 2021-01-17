package by.tix.draft.artemapp.ui.fragment.auth

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.tix.draft.artemapp.R
import by.tix.draft.artemapp.getString
import by.tix.draft.artemapp.repo.AuthRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

    private val _progress = MutableStateFlow(false)
    val progress = _progress.asSharedFlow()

    private val _completion = MutableSharedFlow<Boolean>()
    val completion = _completion.asSharedFlow()

    fun auth() {
        viewModelScope.launch {
            _progress.value = true
            email.value?.takeIf {
                it.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(it).matches()
            }?.let { email ->
                password.value?.let { pass ->
                    AuthRepo.auth(
                        email, pass, onComplete = {
                            viewModelScope.launch {
                                _completion.emit(true)
                            }
                        }, onFailure = {
                            viewModelScope.launch {
                                _errorEvent.emit(it.message ?: getString(R.string.unknown_error))
                            }
                        }
                    )
                }
            } ?: kotlin.run {
                _errorEvent.emit(getString(R.string.validation_error))
            }
        }.invokeOnCompletion {
            _progress.value = false
        }
    }
}