package com.example.calculador_imc

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculador_imc.ui.theme.CalculadorIMCTheme
import com.example.calculador_imc.ui.theme.CalculadorIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadorIMCTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {

    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var resultado by remember {
        mutableStateOf(0.0)
        }

    var classificacao by remember{
        mutableStateOf("Insira o peso")
    }





        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(color = colorResource(id = R.color.cor_app)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.bmi),
                    contentDescription = "Logo App",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(vertical = 16.dp)
                )

                Text(
                    text = "Calculadora IMC",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .offset(y = (-30).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                )
                {
                    Box(
                        modifier = modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center

                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Seus Dados",
                                color = colorResource(R.color.cor_app),
                                fontSize = 22.sp,
                            )

                            OutlinedTextField(
                                value = altura,
                                onValueChange = { altura = it },
                                singleLine = true,
                                label = {
                                    Text(text = "Altura")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            OutlinedTextField(
                                value = peso,
                                onValueChange = { peso = it },
                                singleLine = true,
                                label = {
                                    Text(text = "Peso")
                                },

                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                )
                            )

                            Button(
                                onClick = {
                                    val pesoConvertido = peso.toDouble()
                                    val alturaConvertida = altura.toDouble() / 100




                                    resultado = pesoConvertido / (alturaConvertida * alturaConvertida)

                                    fun decisaoImc(imc: Double): String {
                                        return when {
                                            imc < 18.5 -> "Abaixo do peso"
                                            imc < 25.0 -> "Peso ideal"
                                            imc < 30.0 -> "Levemente acima do peso"
                                            imc < 35.0 -> "Obesidade grau 1"
                                            imc< 40.0 -> "Obesidade grau 2"
                                            else -> "Obesidade grau 3"

                                        }
                                    }


                                    classificacao = decisaoImc(resultado)

                                },

                                modifier = Modifier.padding(21.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(R.color.cor_app)
                                )
                            ) {
                                Text(
                                    text = "Calcular"
                                )
                            }
                        }

                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF329F6B)
                    ),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "21.3",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Start


                            )

                        Spacer(modifier = Modifier.width(16.dp))


                        Text(
                            text = classificacao,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp,
                        )
                    }
                }


            }

        }
    }