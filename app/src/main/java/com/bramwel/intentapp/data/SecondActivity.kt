package com.bramwel.intentapp.data

// SecondActivity.kt


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*

class SecondActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedData = intent.getStringExtra("data")

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("Second Activity") })
                }
            ) { padding ->
                Surface(modifier = androidx.compose.ui.Modifier.padding(padding)) {
                    Column {
                        Text(text = "Data received: ${receivedData ?: "None"}")
                        Button(onClick = {
                            val resultIntent = Intent().apply {
                                putExtra("result", "Hello from SecondActivity!")
                            }
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        }) {
                            Text("Return Result")
                        }
                    }
                }
            }
        }
    }
}
