package com.example.viewmodelsantiagorojas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContadorViewModel : ViewModel() {
    var contador by mutableStateOf(0)
        private set

    fun sumar() { contador++ }
    fun restar() { if (contador > 0) contador-- }
    fun reiniciar() { contador = 0 }
}

