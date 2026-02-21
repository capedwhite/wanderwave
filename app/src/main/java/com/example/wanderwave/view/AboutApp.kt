package com.example.wanderwave.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.R
import com.example.wanderwave.ui.theme.turcoise

@Composable
fun AboutVacationScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F9FF))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       Image(
           painter = painterResource(id = R.drawable.wandericon),
            contentDescription = "Vacation App",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 🏝 App / Company Name
        Text(
            text = "WanderWave Vacations",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = turcoise
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your Journey, Our Passion",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Text(
                text = "At WanderWave Vacations, we specialize in creating unforgettable travel experiences. From tropical beaches to snowy mountains, we offer handpicked vacation packages designed to give you comfort, adventure, and memories for a lifetime.",
                modifier = Modifier.padding(16.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🌍 Highlights Section
        Text(
            text = "Why Travel With Us?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        FeatureItem(
            icon = Icons.Default.LocationOn,
            title = "Top Destinations",
            description = "Explore popular destinations across the world with carefully planned itineraries."
        )

        FeatureItem(
            icon = Icons.Default.Star,
            title = "Premium Experience",
            description = "We focus on comfort, safety, and personalized travel experiences."
        )

        FeatureItem(
            icon = Icons.Default.Notifications,
            title = "All-Inclusive Packages",
            description = "Flights, hotels, food, and activities — everything covered."
        )

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = turcoise.copy(alpha = 0.5f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Contact Us",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Phone, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "+977 98XXXXXXX")
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = "Email: support@wanderwave.com")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Made with love for travelers",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun FeatureItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = turcoise,
            modifier = Modifier.size(32.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
@Preview
@Composable
fun aboutpreview(){
    AboutVacationScreen()
}