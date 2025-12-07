package com.example.wanderwave

import android.app.Activity
import android.app.DatePickerDialog
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.wanderwave.ui.theme.Darkgreen
import com.example.wanderwave.ui.theme.Lightgreen
import com.example.wanderwave.ui.theme.turcoise
import java.util.Calendar

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            registration()
        }
    }
}

@Composable
fun registration() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var Reenter by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }
    var calendar = Calendar.getInstance()
    var context = LocalContext.current
    val activity = context as Activity
    var year = calendar.get(Calendar.YEAR)
    var month = calendar.get(Calendar.MONTH)
    var day = calendar.get(Calendar.DAY_OF_MONTH)
    var selectedDate by remember { mutableStateOf("") }
    var datepicker = DatePickerDialog(
        context, { _, y, m, d ->
            selectedDate = "$y/${m + 1}/$d"

        },
        year, month, day
    )
    val sharedPreference = context.
    getSharedPreferences("User",
        Context.MODE_PRIVATE)
    val savedEmail = sharedPreference.getString("email","")
    val editor = sharedPreference.edit()
    Scaffold { padding ->
        Box(Modifier.fillMaxSize()) {
            Image(
                painter=painterResource(R.drawable.loginbg),
                contentDescription = null,
                modifier=Modifier.fillMaxSize().blur(5.dp),
                contentScale = ContentScale.Crop,
                alpha=0.7f,
            )
            Column(modifier = Modifier.padding(padding)) {
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.wandericon),
                        contentDescription = null,
                        modifier = Modifier.size(180.dp),

                        )
                    Text(
                        text = "Escape the routine. Embrace the adventure. Your dream getaway is just one click away.",
                        style=TextStyle(
                            fontFamily=FontFamily.SansSerif
                        )

                    )
                }

                Text(
                    text = "Register",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        color = turcoise,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif

                    ),
                    modifier= Modifier.fillMaxWidth()
                )
                Spacer(modifier=Modifier.height(20.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { data ->
                        email=data
                    },
                    label={Text("Email")},
                    shape=RoundedCornerShape(15.dp),
                    placeholder = {
                        Text(
                            "abcd@gmail.com", style = TextStyle(
                                color = Color.LightGray
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
                Spacer(modifier=Modifier.height(10.dp))
                OutlinedTextField(
                    value  = password,
                    onValueChange = { data ->
                        password=data
                    },
                    label={Text("Password")},
                    shape=RoundedCornerShape(15.dp),
                    visualTransformation = if(passwordVisible)
                        VisualTransformation.None
                    else
                PasswordVisualTransformation(),
                    placeholder = {
                        Text(
                            "************", style = TextStyle(
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
trailingIcon = {
    IconButton(onClick = {
        passwordVisible=!passwordVisible
    }) {
        Icon(
            painter = if (passwordVisible)
            painterResource(R.drawable.baseline_visibility_24)
            else
            painterResource(R.drawable.baseline_visibility_off_24),
            contentDescription = null
        )
    }
},
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Lightgreen.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = turcoise,
                        unfocusedIndicatorColor = Color.Transparent
                    )


                )

                Spacer(modifier=Modifier.height(10.dp))

                OutlinedTextField(
                    value  = Reenter,
                    onValueChange = { data ->
                        Reenter=data
                    },
                    label={Text("Retype password")},
                    shape=RoundedCornerShape(15.dp),
                    visualTransformation = if(passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    placeholder = {
                        Text(
                            "************", style = TextStyle(
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
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisible=!passwordVisible
                        }) {
                            Icon(
                                painter = if (passwordVisible)
                                    painterResource(R.drawable.baseline_visibility_24)
                                else
                                    painterResource(R.drawable.baseline_visibility_off_24),
                                contentDescription = null
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Lightgreen.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = turcoise,
                        unfocusedIndicatorColor = Color.Transparent
                    )


                )
                Spacer(modifier=Modifier.height(10.dp))
                OutlinedTextField(
                    value  = selectedDate,
                    onValueChange = { data ->
                        selectedDate=data
                    },
                    label={Text("BirthDate")},
                    shape=RoundedCornerShape(15.dp),
                    enabled = false,
                    placeholder = {
                        Text(
                            "\"dd/mm/yyyy\"", style = TextStyle(
                                color = Color.White
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier
                        .clickable{
                            datepicker.show()
                        }.fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    trailingIcon = {

                            Icon(
                                    painterResource(R.drawable.baseline_calendar_today_24),
                                contentDescription = null
                            )

                    },
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        focusedContainerColor = Lightgreen.copy(alpha = 0.4f),
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = turcoise,
                        unfocusedIndicatorColor = Color.Transparent
                    )


                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = terms,
                        onCheckedChange = {
                            terms = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = turcoise,
                            checkmarkColor = Color.White
                        )
                    )
                    Text("I agree to terms & conditions")
                }

                Button(
                    onClick = {
                        if(email.isNullOrBlank() || password.isNullOrBlank() || Reenter.isNullOrBlank() || selectedDate.isNullOrBlank()) {
                            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if(!terms){
                            Toast.makeText(context,
                                "Please agree to terms & conditions",
                                Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        if(savedEmail?.trim()==email.trim()){
                            Toast.makeText(context,"Email already in use",Toast.LENGTH_SHORT).show()
                            return@Button
                        }
if(password!=Reenter){
    Toast.makeText(context,"MisMatch in retyped password",Toast.LENGTH_SHORT).show()
    return@Button
}
                        else{
                            editor.putString("email",email)
                            editor.putString("password",password)
                            editor.putString("date",selectedDate)
                            editor.apply()
                            Toast.makeText(context,
                                "Registration success",
                                Toast.LENGTH_SHORT).show()
                        activity.finish()
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
                    Text("Sign up")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(color = Darkgreen)) {
                            append("Already have an have account?")
                        }

                        withStyle(SpanStyle(color = Color.White)) {
                            append("    Log in")
                        }


                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            val intent = Intent(
                                context, LoginActivity::class.java
                            )
                            context.startActivity(intent)
                            activity.finish()
                        }
                )
                    }
            }
            }

        }


@Preview
@Composable
fun previewRegistration(){
    registration()
}

