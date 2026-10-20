package com.example.viewmodelsantiagorojas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                val vm: ContadorViewModel = viewModel()
                NavHost(nav, startDestination = "contador") {
                    composable("contador") { PantallaContador(vm) { nav.navigate("info") } }
                    composable("info") { PantallaInfo(vm) { nav.popBackStack() } }
                }
            }
        }
    }
}

@Composable
fun PantallaContador(vm: ContadorViewModel, irInfo: () -> Unit) {
    var contadorSinVm by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Contador SIN ViewModel: $contadorSinVm")
        Button(onClick = { contadorSinVm++ }) { Text("Sumar") }

        Spacer(Modifier.height(32.dp))

        Text("Contador CON ViewModel: ${vm.contador}")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { vm.sumar() }) { Text("+") }
            Button(onClick = { vm.restar() }) { Text("-") }
            Button(onClick = { vm.reiniciar() }) { Text("Reset") }
        }

        Spacer(Modifier.height(32.dp))

        Text("Rota el celular y mira cuál contador se conserva")
        Button(onClick = irInfo) { Text("¿Qué es un ViewModel?") }
    }
}

@Composable
fun PantallaInfo(vm: ContadorViewModel, volver: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Un ViewModel guarda y maneja los datos de la pantalla.")
        Spacer(Modifier.height(8.dp))
        Text("Sobrevive a cambios como rotar el celular.")
        Spacer(Modifier.height(8.dp))
        Text("Es el mismo ViewModel de la otra pantalla, valor: ${vm.contador}")
        Spacer(Modifier.height(16.dp))
        Button(onClick = volver) { Text("Volver") }
    }
}