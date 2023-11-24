package com.jesusaledo.pr306

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusaledo.pr306.ui.theme.PR306Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PR306Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiasMes()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiasMes() {
    val d30: List<Int> = listOf(4, 6, 9, 11)
    val d31: List<Int> = listOf(1, 3, 5, 7, 8, 10, 12)
    var dia by rememberSaveable {
        mutableStateOf("")
    }
    var mes by rememberSaveable {
        mutableStateOf("")
    }
    var year by rememberSaveable {
        mutableStateOf("")
    }
    var secure by rememberSaveable {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Días restantes hasta el final del mes")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = dia,
            onValueChange = { dia = it; secure = false },
            label = { Text("Introduce el día") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = mes,
            onValueChange = { mes = it; secure = false },
            label = { Text("Introduce el mes") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = year,
            onValueChange = { year = it; secure = false },
            label = { Text("Introduce el año") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                secure =
                    (dia.isNotBlank() && dia.toIntOrNull() != null && dia.toInt() >= 0 && dia.toInt() <= 31) &&
                            (mes.isNotBlank() && mes.toIntOrNull() != null && mes.toInt() >= 0 && mes.toInt() <= 12) &&
                            (year.isNotBlank() && year.toIntOrNull() != null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(bottom = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Calcular")
        }
        if (secure) {
            var bisiesto = (year.toInt() % 4 == 0) && ((year.toInt() % 100 != 0) || (year.toInt() % 400 == 0))
            if (mes.toInt() in d30 && dia.toInt() <= 30)
                Text("${30 - dia.toInt()} días restantes")
            else if (mes.toInt() in d31)
                Text("${31 - dia.toInt()} días restantes")
            else if (bisiesto)
                Text("${29 - dia.toInt()} días restantes")
            else
                Text("${28 - dia.toInt()} días restantes")
        } else
            Text("Introduce números enteros positivos que no sean mayores a los valores posibles")
    }
}
