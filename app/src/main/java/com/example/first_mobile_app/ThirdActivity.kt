package com.example.first_mobile_app

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first_mobile_app.ui.theme.First_Mobile_AppTheme

class ThirdActivity : ComponentActivity() {
    private val picId = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            First_Mobile_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptureImageScreen(
                        modifier = Modifier.padding(innerPadding),
                        onCaptureImage = {
                            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(cameraIntent, picId)
                        },
                        capturedImage = null,
                        onBackToMainActivity = {
                            // Explicit intent to navigate back to MainActivity
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == picId && resultCode == RESULT_OK) {
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            setContent {
                First_Mobile_AppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CaptureImageScreen(
                            modifier = Modifier.padding(innerPadding),
                            onCaptureImage = {
                                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                                startActivityForResult(cameraIntent, picId)
                            },
                            capturedImage = photo,
                            onBackToMainActivity = {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CaptureImageScreen(
    modifier: Modifier = Modifier,
    onCaptureImage: () -> Unit,
    capturedImage: Bitmap?,
    onBackToMainActivity: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Capture and View Image")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onCaptureImage) {
                Text(text = "Capture Image")
            }

            Spacer(modifier = Modifier.height(32.dp))

            capturedImage?.let { image ->
                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "Captured Image",
                    modifier = Modifier.size(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Button to go back to MainActivity
            Button(onClick = onBackToMainActivity) {
                Text(text = "Back to Main Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptureImageScreenPreview() {
    First_Mobile_AppTheme {
        CaptureImageScreen(
            onCaptureImage = {},
            capturedImage = null,
            onBackToMainActivity = {}
        )
    }
}
