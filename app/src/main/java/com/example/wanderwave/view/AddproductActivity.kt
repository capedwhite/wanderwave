package com.example.wanderwave.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.model.ProductModel
import com.example.wanderwave.repository.ProductRepoImpl
import com.example.wanderwave.ui.theme.Lightgreen
import com.example.wanderwave.ui.theme.turcoise
import com.example.wanderwave.viewmodel.ProductViewModel

class AddproductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
Addproduct()
        }
    }
}

@Composable
fun Addproduct(){
    val productviewmodel = remember { ProductViewModel(ProductRepoImpl()) }
    var productName by remember { mutableStateOf("") }
    var productDescription by remember {mutableStateOf("")}
    var productPrice by remember {mutableStateOf("")}
    val context = LocalContext.current
    val activity = context as Activity


Scaffold { paddingValues->
    Column(modifier=Modifier
        .padding(paddingValues)
        .fillMaxSize()
        .background(color = turcoise),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){
Text(
    text="Add Vaccation Package",
    modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    textAlign = TextAlign.Center,
    fontSize = 30.sp,
    fontWeight = FontWeight.Bold,
    color = Color.White
)
        Box(modifier= Modifier
            .padding(20.dp)
            .height(500.dp)
            .background(Color.White)){
            Column {
            OutlinedTextField(
                value = productName,
                onValueChange = { data ->
                    productName=data
                },
                label={Text("Name of package")},
                shape=RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        "Enter your package name", style = TextStyle(
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
                    focusedContainerColor = Lightgreen.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = turcoise,
                    unfocusedIndicatorColor = turcoise
                )



            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = productDescription,
                onValueChange = { data ->
                    productDescription=data
                },
                label={Text("Description of package")},
                shape=RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        "Enter your package description", style = TextStyle(
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
                    focusedContainerColor = Lightgreen.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = turcoise,
                    unfocusedIndicatorColor = turcoise
                )



            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = productPrice,
                onValueChange = { data ->
                    productPrice=data
                },
                label={Text("Price of package")},
                shape=RoundedCornerShape(15.dp),
                placeholder = {
                    Text(
                        "Enter your package name", style = TextStyle(
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
                    focusedContainerColor = Lightgreen.copy(alpha = 0.3f),
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = turcoise,
                    unfocusedIndicatorColor = turcoise
                )




            )
                Spacer(modifier=Modifier.height(20.dp))

                Button(
                    onClick = {
                        if(productName.isNullOrBlank() || productDescription.isNullOrBlank() || productDescription.isNullOrBlank()){
                            Toast.makeText(context, "cant leave empty fields", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val model = ProductModel(
                            ProductName=productName,
                                ProductPrice = productPrice,
                                ProductDescription = productDescription


                            )
                            productviewmodel.addProduct(model){ success, msg->
                                if(success){
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                            },
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = turcoise,
                        contentColor = Color.White
                    ),
                            elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 15.dp

                            ),


                ) { Text("Add Product")}
        }
    }}
}
}

@Preview
    @Composable
fun Addproductpreview() {
  Addproduct()
}