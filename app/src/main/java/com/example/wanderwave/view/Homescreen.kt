package com.example.wanderwave.view

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.wanderwave.ui.theme.turcoise

@Composable
fun HomeScreen() {
    var searchItem by remember { mutableStateOf("") }
    Column(
        modifier=Modifier.fillMaxSize().background(Color.White)
    ) {

        TextField(
            value = searchItem,
            onValueChange = { searchItem = it },

            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_calendar_today_24),
                    contentDescription = null
                )
            },
            placeholder ={ Text("Search for events",style = TextStyle(
                    color = Color.LightGray
                    )) },
            modifier = Modifier.fillMaxWidth().padding(15.dp).clip(RoundedCornerShape(14.dp))

        )
//        Image(
//            painter = painterResource(R.drawable.homescreenimg),
//            contentDescription = null,
//            modifier = Modifier.fillMaxWidth().padding().width(400.dp)
//        )
        Text(
            text="Discover packages",
            modifier=Modifier.padding(15.dp),
            style=TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp

            )
        )
        cards(R.drawable.loginbg, "bali", "450", "3days")

    }
}
    @Composable
    fun cards(image:Int, title: String, price:String, time:String){
        Spacer(Modifier.height(15.dp))
        Card(modifier= Modifier.fillMaxWidth().height(200.dp).padding(15.dp),colors= CardDefaults.cardColors(
            containerColor = turcoise
        )) {
            Row (
                modifier=Modifier.fillMaxSize().padding(10.dp).background(Color.White).clip(RoundedCornerShape(14.dp))

            ) {
 Image(
     painter = painterResource(image),
     contentDescription = null,
     modifier=Modifier.size(200.dp)
 )
                Column {

                }
            }
        }

    }


@Preview
@Composable
fun PreviewHomescreen(){
    HomeScreen()
}

