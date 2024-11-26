package com.example.first_mobile_app

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.first_mobile_app.ui.theme.First_Mobile_AppTheme

class MainActivity : ComponentActivity() {

    private val customPermission = "com.example.first_mobile_app.MSE412"
    private val customPermissionKey = "custom_permission_granted"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            First_Mobile_AppTheme {
                var showPermissionDialog by remember { mutableStateOf(false) }
                var permissionGranted by remember { mutableStateOf(isPermissionGranted()) }

                if (showPermissionDialog) {
                    CustomPermissionDialog(
                        onGrant = {
                            grantPermission()
                            permissionGranted = true
                            showPermissionDialog = false
                        },
                        onDeny = {
                            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                            showPermissionDialog = false
                        }
                    )
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Austin Erickson",
                        studentId = "1259150",
                        modifier = Modifier.padding(innerPadding),
                        onStartExplicitActivity = {
                            if (permissionGranted) {
                                val explicitIntent = Intent(this, SecondActivity::class.java)
                                startActivity(explicitIntent)
                            } else {
                                showPermissionDialog = true
                            }
                        },
                        onStartImplicitActivity = {
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

    private fun isPermissionGranted(): Boolean {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean(customPermissionKey, false)
    }

    private fun grantPermission() {
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(customPermissionKey, true).apply()
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
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
        Text(
            text = "Name: $name",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Student ID: $studentId",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Button(onClick = onStartExplicitActivity, modifier = Modifier.padding(8.dp)) {
            Text(text = "Start Activity Explicitly")
        }
        Button(onClick = onStartImplicitActivity, modifier = Modifier.padding(8.dp)) {
            Text(text = "Start Activity Implicitly")
        }
        Button(onClick = onViewImageActivity) {
            Text(text = "View Image Activity")
        }
    }
}

@Composable
fun CustomPermissionDialog(onGrant: () -> Unit, onDeny: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDeny,
        title = { Text("Permission Request") },
        text = { Text("The app requires a custom permission to access this feature.") },
        confirmButton = {
            Button(onClick = onGrant) {
                Text("Allow")
            }
        },
        dismissButton = {
            Button(onClick = onDeny) {
                Text("Deny")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    First_Mobile_AppTheme {
        MainScreen(
            name = "Austin Erickson",
            studentId = "1259150",
            onStartExplicitActivity = {},
            onStartImplicitActivity = {},
            onViewImageActivity = {}
        )
    }
}
