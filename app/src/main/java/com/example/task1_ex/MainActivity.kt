package com.example.task1_ex

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.res.painterResource
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task1_ex.ui.theme.Task1_EXTheme
import org.w3c.dom.Text


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task1_EXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quiz()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Quiz() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta)
            )
        },
        content = { padding -> ScreenContent(Modifier.padding(padding)) },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier) {
    var state by remember { mutableStateOf((1..3).random()) }
    var texto by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(modifier = Modifier.padding(top = 0.dp, start = 0.dp)) {
            if (state == 1){
                Image(
                    painter = painterResource(id = R.drawable.cat),
                    contentDescription = "", // decorative element
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .width(250.dp)
                        .height(250.dp)
                )
            }
            else if (state == 2){
                Image(
                    painter = painterResource(id = R.drawable.cow),
                    contentDescription = "", // decorative element
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .width(250.dp)
                        .height(250.dp)
                )
            }
            else if (state == 3) {
                Image(
                    painter = painterResource(id = R.drawable.sheep),
                    contentDescription = "", // decorative element
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .width(250.dp)
                        .height(250.dp)
                )
            }

        }
        Row (){
            Text(text = "What animal is this?")
        }
        Row (
            modifier = Modifier.padding(10.dp)
        ){
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Write your answer") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if(texto == "cat" && state == 1 ||
                            texto == "cow" && state == 2 ||
                            texto == "sheep" && state == 3){
                            Toast.makeText(context,"Right answer", Toast.LENGTH_SHORT)
                                .show()
                            state = (1..3).random()
                            texto = ""
                        }
                        else{
                            Toast.makeText(context,"Wrong answer :c", Toast.LENGTH_SHORT)
                                .show()
                            texto = ""
                        }
                    }
                )
            )
        }
    }
}