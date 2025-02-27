package com.devlopershankar.pregnancyvitalstracker.uiv.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.devlopershankar.pregnancyvitalstracker.data.Vital

@Composable
fun AddVitalDialog(
    onDismiss: () -> Unit,
    onSave: (Vital) -> Unit
) {
    var sysBP by remember { mutableStateOf("") }
    var diaBP by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var babyKicks by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Vitals", fontWeight = FontWeight.Bold) },
        text = {
            Column {
                OutlinedTextField(
                    value = sysBP,
                    onValueChange = { sysBP = it },
                    label = { Text("Sys BP") }
                )
                OutlinedTextField(
                    value = diaBP,
                    onValueChange = { diaBP = it },
                    label = { Text("Dia BP") }
                )
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") }
                )
                OutlinedTextField(
                    value = babyKicks,
                    onValueChange = { babyKicks = it },
                    label = { Text("Baby Kicks") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (sysBP.isNotEmpty() && diaBP.isNotEmpty() && weight.isNotEmpty() && babyKicks.isNotEmpty()) {
                        val vital = Vital(
                            bloodPressureSys = sysBP.toIntOrNull() ?: 0,
                            bloodPressureDia = diaBP.toIntOrNull() ?: 0,
                            weight = weight.toFloatOrNull() ?: 0f,
                            babyKicks = babyKicks.toIntOrNull() ?: 0,
                            heartRate = (60..100).random(), // Random heart rate for now
                            timestamp = System.currentTimeMillis()
                        )
                        onSave(vital) // Call onSave with the new vital
                    }
                }
            ) {
                Text("Submit")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
