package com.example.wanderwave.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.R
import com.example.wanderwave.ui.theme.Darkgreen
import com.example.wanderwave.ui.theme.bggrey
import com.example.wanderwave.ui.theme.turcoise

@Composable
fun HomeScreen() {
    var searchItem by remember { mutableStateOf("") }

    Column(
        modifier=Modifier.fillMaxSize().background(color= bggrey),
         

    ) {

        TextField(
            value = searchItem,
            onValueChange = { searchItem = it },

            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_search_24),
                    contentDescription = null
                )
            },
            placeholder ={ Text("Search for events",style = TextStyle(
                    color = Color.LightGray
                    )) },
            modifier = Modifier.fillMaxWidth().padding(15.dp).clip(RoundedCornerShape(14.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )

        )

        Text(
            text="Discover packages",
            modifier=Modifier.padding(15.dp),
            style=TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp

            )
        )
        cards(R.drawable.img_3, "bali", "450", "3days")

    }
}

    @Composable
    fun cards(image:Int, title: String, price:String, time:String){
        Spacer(Modifier.height(15.dp))
        Card(modifier= Modifier.fillMaxWidth().height(200.dp).padding(15.dp),colors= CardDefaults.cardColors(
            containerColor = Color.White
        )) {
            Column (
                modifier=Modifier.fillMaxSize().background(Color.White)

            ) {
 Image(
     painter = painterResource(image),
     contentDescription = null,
     modifier=Modifier.height(120.dp).fillMaxWidth()
 )
              Row(
                  modifier=Modifier.fillMaxWidth().padding(10.dp),
                  verticalAlignment = Alignment.CenterVertically,

              ) {
Text(title,style= TextStyle(
    fontWeight = FontWeight.Bold
),modifier=Modifier.padding(horizontal=30.dp))
                  Spacer(Modifier.width(50.dp))
                  Text(price,color=Color.Gray)
                  Spacer(Modifier.width(10.dp))
                  Text(
                      time,color=Color.Gray
                  )
                }


            }
        }

        HorizontalDivider(
            color = Color.LightGray,
          modifier= Modifier.padding(10.dp)
        )

    }
@Preview
@Composable
fun PreviewHomescreen(){
    HomeScreen()
}

