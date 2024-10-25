package com.example.first_mobile_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first_mobile_app.ui.theme.First_Mobile_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            First_Mobile_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Austin Erickson",
                        studentId = "1259150",
                        modifier = Modifier.padding(innerPadding),
                        onStartExplicitActivity = {
                            // Explicit Intent to start SecondActivity
                            val explicitIntent = Intent(this, SecondActivity::class.java)
                            startActivity(explicitIntent)
                        },
                        onStartImplicitActivity = {
                            // Implicit Intent to start SecondActivity
                            val implicitIntent = Intent("com.example.ACTION_VIEW_CHALLENGES")
                            startActivity(implicitIntent)
                        },
                        onViewImageActivity = {
                            val intent = Intent(this, ThirdActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    name: String,
    studentId: String,
    modifier: Modifier = Modifier,
    onStartExplicitActivity: () -> Unit,
    onStartImplicitActivity: () -> Unit,
    onViewImageActivity: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth().padding(16.dp)
    ) {
        // Display the user's name
        Text(
            text = "Name: $name",
            fontSize = 32.sp, // Adjust the font size as needed
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Display the student's ID
        Text(
            text = "Student ID: $studentId",
            fontSize = 24.sp, // Adjust the font size as needed
            modifier = Modifier.padding(bottom = 32.dp)
        )
        // Button to start Explicit Activity
        Button(onClick = onStartExplicitActivity, modifier = Modifier.padding(8.dp)) {
            Text(text = "Start Activity Explicitly")
        }
        // Button to start the activity implicitly
        Button(onClick = onStartImplicitActivity, modifier = Modifier.padding(8.dp)) {
            Text(text = "Start Activity Implicitly")
        }
        Button(onClick = onViewImageActivity) {
            Text(text = "View Image Activity")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    First_Mobile_AppTheme {
        MainScreen(
            name = "Austin",
            studentId = "1259150",
            onStartExplicitActivity = {},
            onStartImplicitActivity = {},
            onViewImageActivity = {}
        )
    }
}
