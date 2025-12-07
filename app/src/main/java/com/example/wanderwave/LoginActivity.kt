package com.example.wanderwave

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.ui.theme.Darkgreen
import com.example.wanderwave.ui.theme.Lightgreen
import com.example.wanderwave.ui.theme.turcoise
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Loginpage()
        }
    }
}
@Composable
fun Loginpage() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as Activity
//
    val sharedPreferences = context.getSharedPreferences(
        "User",
        Context.MODE_PRIVATE
    )

    val localEmail: String? = sharedPreferences.getString("email", "")
    val localPassword: String? = sharedPreferences.getString("password", "")

    Scaffold { padding ->
        Box(Modifier.fillMaxSize()) {
Image(
    painter = painterResource(id=R.drawable.loginbg),
    contentDescription = null,
    modifier = Modifier
        .fillMaxSize()
        .blur(5.dp),
    contentScale = ContentScale.Crop,
    alpha = 0.7f
)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)

            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier=Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.wandericon),
                        contentDescription = null,


                        )
                    Text(
                        text = "Adventure Awaits. Pack Your Dreams.",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Color.Black.copy(0.6f),
                            fontFamily = FontFamily.SansSerif

                        ),
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp));
                Text(
                    text = "Log In",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        color = turcoise,
                        fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp));
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)

                ) {
                    MediaCard(
                        Modifier
                            .height(70.dp)
                            .weight(1f),
                        R.drawable.facebooklogo, "Facebook"

                    )
                    Spacer(modifier = Modifier.width(10.dp));
                    MediaCard(Modifier
                        .height(70.dp)
                        .weight(1f), R.drawable.envelope, "Gmail")
                }
                Row(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HorizontalDivider(
                        color = Darkgreen,
                        modifier = Modifier.weight(2f)
                    )
                    Text("OR", Modifier.padding(horizontal = 10.dp))
                    HorizontalDivider(
                        color = Darkgreen,
                        modifier = Modifier.weight(2f)
                    )
                }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { data ->
                            email = data
                        },
                        label = { Text("Email") },
                        shape = RoundedCornerShape(15.dp),
                        placeholder = {
                            Text(
                                "abcd@gmail.com", style = TextStyle(
                                    color = Color.White
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Lightgreen.copy(alpha = 0.4f),
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = turcoise,
                            unfocusedIndicatorColor = Color.Transparent
                        )

                    )

                Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = password,
        onValueChange = { data ->
            password = data
        },
        label = { Text("Password") },
        trailingIcon = {
            IconButton(onClick = {
                visibility = !visibility
            }) {
                Icon(
                    painter = if (visibility)
                        painterResource(R.drawable.baseline_visibility_24)
                    else
                        painterResource(R.drawable.baseline_visibility_off_24),
                    contentDescription = null
                )
            }

        },
        visualTransformation = if (visibility) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                "********", style = TextStyle(
                    color = Color.White
                )
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Lightgreen.copy(alpha = 0.6f),
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = turcoise,
            unfocusedIndicatorColor = Color.Transparent
        )

    )

                Text(
                    "Forget password",
                    style = TextStyle(
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                )

                Button(
                    onClick = {
                        if (email.isNullOrBlank() ||  password.isNullOrBlank()){
Toast.makeText(
    context,"Fields cant be empty",Toast.LENGTH_SHORT).show()
return@Button
                        }
                        if (localEmail == email && localPassword == password) {
                            val intent = Intent(

                                context, DashboardActivity::class.java

                            )
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)

                            context.startActivity(intent)
                            activity.finish()
                            return@Button
                        } else {
                            Toast.makeText(
                                context,
                                "invalid login",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = turcoise,       // Background color
                        contentColor = Color.White         // Text & icon color
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 15.dp

                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 15.dp),

                    shape = RoundedCornerShape(10.dp),

                    ) {
                    Text("Log In")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Darkgreen)) {
                            append("Don't have account?")
                        }

                        withStyle(SpanStyle(color = Color.White)) {
                            append("   Sign  up")
                        }


                    },
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(
                                context, RegisterActivity::class.java
                            )
                            context.startActivity(intent)
                            activity.finish()
                        }
                        .padding(15.dp),

style = TextStyle(
    fontWeight=FontWeight.Bold
)

                )


            }
        }
    }
}

@Composable
fun MediaCard(modifier: Modifier,image:Int,label: String){
    Card(
        modifier=modifier
    ){
        Row( modifier=Modifier
            .fillMaxSize()
            .background(color = turcoise),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Image(
                painter = painterResource(id = image),
                contentDescription = "null",
                modifier= Modifier.size(25.dp)
            )
            Spacer(modifier= Modifier.width(10.dp))
            Text(
                text=label,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

    }
}
@Preview
@Composable
fun preview(){
    Loginpage()
}



