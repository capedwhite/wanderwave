package com.example.wanderwave.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.R
import com.example.wanderwave.ui.theme.turcoise

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
Dashboard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    data class NavItem(
        val label: String,
        val icon: Int
    )

    val context = LocalContext.current
    val activity = context as? Activity
    var selectedIndex by remember { mutableStateOf(0) }

    val navlist = listOf(
        NavItem("Explore", R.drawable.outline_directions_car_24),
        NavItem("Request", R.drawable.outline_add_location_alt_24),
        NavItem("Challenges", R.drawable.baseline_add_a_photo_24),
        NavItem("About", R.drawable.outline_airplane_ticket_24)
    )

    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Vaccation Package",
                        color = turcoise,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
//                            activity?.finish()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_dehaze_24),
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* action here */ }) {
                        Icon(
                            painter = painterResource(R.drawable.img_2),
                            contentDescription = "View"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(containerColor = turcoise) {
                navlist.forEachIndexed { index, item ->
                    NavigationBarItem(

                        icon = {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        label = {
                            Text(item.label, color = Color.White)

                        },

                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },

                        )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> ""
                2 -> ChallengeScreen()
                3 -> AboutVacationScreen()
                else -> HomeScreen()
            }

        }
    }
}

    @Preview
@Composable
fun Dashboardpreview() {
Dashboard()
    }
