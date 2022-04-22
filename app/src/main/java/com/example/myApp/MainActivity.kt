package com.example.myApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.myApp.screens.home.HomeScreen
import com.example.myApp.ui.theme.AppEntwicklungTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    AppEntwicklungTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    AppEntwicklungTheme {
        HomeScreen()
    }
}




