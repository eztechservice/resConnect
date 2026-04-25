package com.example.resconnect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmergencyNumbersList()
                }
            }
        }
    }
}

data class EmergencyNumber(val name: String, val number: String, val category: String)

val emergencyNumbers = listOf(
    // National
    EmergencyNumber("National Emergency", "911", "National"),
    EmergencyNumber("Philippine Red Cross", "143", "National"),
    EmergencyNumber("National Complaint Hotline", "8888", "National"),
    
    // Disaster & Weather
    EmergencyNumber("NDRRMC Operations", "0289111406", "Disaster"),
    EmergencyNumber("PAGASA", "0282840800", "Weather"),
    EmergencyNumber("PHIVOLCS", "0284261468", "Earthquake/Volcano"),
    EmergencyNumber("PCG (Philippine Coast Guard)", "09177243682", "Maritime"),
    
    // Metro Manila Specific
    EmergencyNumber("MMDA Hotline", "136", "Metro Manila"),
    EmergencyNumber("BFP NCR", "0284260219", "Fire"),
    
    // Local Government
    EmergencyNumber("Manila Emergency", "0285275174", "Local (Manila)"),
    EmergencyNumber("Quezon City", "122", "Local (QC)"),
    EmergencyNumber("Makati", "168", "Local (Makati)"),
    EmergencyNumber("Taguig", "1623", "Local (Taguig)"),
    EmergencyNumber("Pasig", "0286430000", "Local (Pasig)"),
    EmergencyNumber("Mandaluyong", "0285332225", "Local (Mandaluyong)"),
    EmergencyNumber("Marikina", "161", "Local (Marikina)"),
    
    // Utilities
    EmergencyNumber("Meralco", "16211", "Utilities"),
    EmergencyNumber("Maynilad", "1626", "Utilities"),
    EmergencyNumber("Manila Water", "1627", "Utilities"),
    
    // Health
    EmergencyNumber("DOH Emergency", "0286517800", "Health"),
    EmergencyNumber("Poison Control (UP-PGH)", "0285241078", "Health"),
    EmergencyNumber("PGH (Manila)", "0285548400", "Hospitals"),
    EmergencyNumber("St. Luke's (BGC)", "0287897700", "Hospitals"),
    EmergencyNumber("Makati Medical Center", "0288888999", "Hospitals"),
    EmergencyNumber("The Medical City", "0289881000", "Hospitals")
)

@Composable
fun EmergencyNumbersList() {
    val context = LocalContext.current
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "PH Emergency Hotlines",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyColumn {
            val grouped = emergencyNumbers.groupBy { it.category }
            grouped.forEach { (category, numbers) ->
                item {
                    Text(
                        text = category,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(numbers) { item ->
                    EmergencyItem(item) {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${item.number}")
                        }
                        context.startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun EmergencyItem(item: EmergencyNumber, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = item.name, fontWeight = FontWeight.Medium)
                Text(text = item.number, color = MaterialTheme.colorScheme.secondary)
            }
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
