package com.example.wanderwave

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wanderwave.ui.theme.Lightgreen

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
Home()
        }
    }
}

@Composable
fun Home() {
Scaffold { padding->
   Column(
       modifier = Modifier.fillMaxSize().padding(padding).
       background(color= Lightgreen)
   ) {
       Spacer(modifier = Modifier.height(20.dp))
       Column(
           modifier=Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.Start

       ) {
           Row(
               modifier=Modifier.fillMaxWidth()
           ) {
               Image(
                   painter = painterResource(id = R.drawable.justicon),
                   contentDescription = null,
                   modifier = Modifier.size(180.dp)
               )
               Spacer(modifier = Modifier.width(15.dp))

               Text(
                   "Wander Wave"
               )
           }
       }
    }
} }




@Preview
@Composable
fun Homepreview() {
Home()
    }
