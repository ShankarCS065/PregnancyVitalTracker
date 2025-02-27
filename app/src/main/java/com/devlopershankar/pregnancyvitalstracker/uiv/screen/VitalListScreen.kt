package com.devlopershankar.pregnancyvitalstracker.uiv.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import com.devlopershankar.pregnancyvitalstracker.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devlopershankar.pregnancyvitalstracker.data.Vital
import com.devlopershankar.pregnancyvitalstracker.repository.VitalRepository
import com.devlopershankar.pregnancyvitalstracker.viewmodel.VitalViewModel
import com.devlopershankar.pregnancyvitalstracker.viewmodel.VitalViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalListScreen(repository: VitalRepository) {
    val viewModel: VitalViewModel = viewModel(factory = VitalViewModelFactory(repository))
    val vitals by viewModel.allVitals.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Track My Pregnancy", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF9B59B6)))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }, containerColor = Color(0xFF8E44AD)) {
                Text("+", color = Color.White, fontSize = 24.sp)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.padding(8.dp)) {
                items(vitals) { vital ->
                    VitalCard(vital)
                }
            }
        }
    }

    if (showDialog) {
        AddVitalDialog(onDismiss = { showDialog = false }, onSave = { vital ->
            viewModel.insert(vital)
            showDialog = false
        })
    }
}

@Composable
fun VitalCard(vital: Vital) {
    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(vital.timestamp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8C1FF))
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconWithText(R.drawable.heartbeat, "${vital.heartRate} bpm")
                    IconWithText(R.drawable.bp, "${vital.bloodPressureSys}/${vital.bloodPressureDia} mmHg")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconWithText(R.drawable.weight, "${vital.weight} kg")
                    IconWithText(R.drawable.kicks, "${vital.babyKicks} kicks           ")
                }

                Spacer(modifier = Modifier.height(40.dp))
            }

            // Timestamp bar aligned at the bottom of the card
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0xFF8E44AD))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(formattedDate, fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun IconWithText(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = iconRes), contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontWeight = FontWeight.Bold)
    }
}
