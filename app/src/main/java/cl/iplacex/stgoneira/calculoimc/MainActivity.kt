package cl.iplacex.stgoneira.calculoimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMC()
        }
    }
}


// Fórmula IMC = pesoKg / alturaEnMetrosAlCuadrado
// Peso inferior al normal	| Menos de 18.5
// Normal	                | 18.5 – 24.9
// Peso superior al normal	| 25.0 – 29.9
// Obesidad	                | Más de 30.0
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CalculadoraIMC() {
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column() {
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso") },
            supportingText = { Text("Peso en kilogramos (por ej. 70)") })
        OutlinedTextField(value = altura,
            onValueChange = { altura = it },
            label = { Text("Altura") },
            supportingText = { Text("Altura en metros (por ej. 1.65)") }
        )
        Button(onClick = {
            val pesoD:Double    = peso.toDouble()
            val alturaD:Double  = altura.toDouble()
            val imc             = calcularIMC(pesoD, alturaD)
            resultado           = "El IMC es: "+imc.toString()
        }) {
            Text("Calcular IMC")
        }
        Text(text = resultado)
    }
}

fun calcularIMC(peso:Double, altura:Double):Double {
    return peso / Math.pow(altura, 2.0)
}

