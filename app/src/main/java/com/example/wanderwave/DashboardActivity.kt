package com.example.wanderwave

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wanderwave.ui.theme.Lightgreen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
Dashboard()
        }
    }
}

@Composable
fun Dashboard() {
    data class NavItem(val label: String, val icon: Int)

    val context = LocalContext.current
    val activity = context as Activity
    var selectedIndex by remember { mutableStateOf(0) }

    val navlist = listOf(
        NavItem(label = "Explore", icon = R.drawable.baseline_time_to_leave_24),
        NavItem(label = "request package", icon = R.drawable.outline_add_location_alt_24),
        NavItem(label = "Challenges", icon = R.drawable.baseline_add_a_photo_24),
        NavItem(label = "about", icon = R.drawable.outline_airplane_ticket_24)
    )
//    Scaffold(
//         topBar = {
//            TopAppBar(
//                title = {
//                    Image(
//                        painter = painterResource(R.drawable.wandericon),
//                        contentDescription = null,
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.White
//                ),
//                navigationIcon = {
//                    IconButton(onClick = {
//                        activity.finish()
//                    }) {
//                        Icon(
//                            painter = painterResource(R.drawable.outline_dehaze_24),
//                            contentDescription = null
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(onClick = { }) {
//                        Icon(
//                            painter = painterResource(R.drawable.baseline_visibility_24),
//                            contentDescription = null
//                        )
//                    }
//                }
//            )
//        }
//    )
}

    @Preview
@Composable
fun Dashboardpreview() {
Dashboard()
    }
