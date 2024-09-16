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
import com.example.first_mobile_app.ui.theme.First_Mobile_AppTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            First_Mobile_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChallengesScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBackToMainActivity = {
                            // Explicit intent to go back to MainActivity
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChallengesScreen(modifier: Modifier = Modifier, onBackToMainActivity: () -> Unit) {
    // Centering the content using a Box
    Box(
        modifier = Modifier
            .fillMaxSize() // Make the Box fill the screen
            .padding(16.dp), // Add some padding to avoid edge cutoff
        contentAlignment = Alignment.Center // Align content in the center
    ) {
        // Column to arrange text and button vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center elements horizontally
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Mobile Software Engineering Challenges")

            Spacer(modifier = Modifier.height(16.dp)) // Add some space between text items

            Text(text = "1. Device Fragmentation")
            Text(text = "2. OS Fragmentation")
            Text(text = "3. Rapid Changes")
            Text(text = "4. User Interface Consistency")
            Text(text = "5. Unstable Environment")

            Spacer(modifier = Modifier.height(32.dp)) // Space between challenges and the button

            // Button to go back to Main Activity
            Button(onClick = onBackToMainActivity) {
                Text(text = "Back to Main Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengesScreenPreview() {
    First_Mobile_AppTheme {
        ChallengesScreen(onBackToMainActivity = {})
    }
}
