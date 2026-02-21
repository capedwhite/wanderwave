package com.example.wanderwave.view

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.wanderwave.R
import com.example.wanderwave.model.ProductModel
import com.example.wanderwave.repository.ProductRepoImpl
import com.example.wanderwave.ui.theme.turcoise
import com.example.wanderwave.utils.ImageUtils
import com.example.wanderwave.viewmodel.ProductViewModel

// ─── Color Palette ───────────────────────────────────────────────────────────
private val Teal        = Color(0xFF00B4A6)
private val TealDark    = Color(0xFF007A70)
private val TealLight   = Color(0xFFE0F7F5)
private val Navy        = Color(0xFF0D1B2A)
private val SurfaceCard = Color(0xFFF8FFFE)
private val Slate       = Color(0xFF607D8B)
private val Error       = Color(0xFFE53935)

class AddproductActivity : ComponentActivity() {
    lateinit var imageUtils: ImageUtils
    var selectedImageUri by mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        imageUtils = ImageUtils(this, this)
        imageUtils.registerLaunchers { uri -> selectedImageUri = uri }
        setContent {
            AddproductScreen(
                selectedImageUri = selectedImageUri,
                onPickImage = { imageUtils.launchImagePicker() },
                onBack = { finish() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddproductScreen(
    selectedImageUri: Uri?,
    onPickImage: () -> Unit,
    onBack: () -> Unit = {}
) {
    val viewModel = remember { ProductViewModel(ProductRepoImpl()) }
    var name        by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price       by remember { mutableStateOf("") }
    var duration    by remember { mutableStateOf("") }
    var isLoading   by remember { mutableStateOf(false) }
    val context     = LocalContext.current
    val scrollState = rememberScrollState()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background( turcoise)
    ) {

        Box(
            modifier = Modifier
                .size(260.dp)
                .offset(x = (-60).dp, y = (-60).dp)
                .background(Color.White.copy(alpha = 0.06f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopEnd)
                .offset(x = 50.dp, y = 40.dp)
                .background(Color.White.copy(alpha = 0.05f), CircleShape)
        )

        Column(modifier = Modifier.fillMaxSize()) {

            // ── Top bar ───────────────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "New Package",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(SurfaceCard)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 24.dp, vertical = 28.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    // ── Section label ─────────────────────────────────────
                    Text(
                        "Package Details",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Navy
                    )


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(listOf(Teal, TealDark)),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { onPickImage() },
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedImageUri != null) {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = "Selected Image",
                                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(10.dp)
                                    .size(36.dp)
                                    .background(turcoise, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Outlined.Edit,
                                    contentDescription = "Change image",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .background(TealLight, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        Icons.Outlined.AddCircle,
                                        contentDescription = null,
                                        tint = Teal,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                                Text(
                                    "Tap to upload photo",
                                    color = Teal,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                                Text(
                                    "JPG, PNG supported",
                                    color = Slate,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }

                    // ── Fields ────────────────────────────────────────────
                    ModernField(
                        value = name,
                        onValueChange = { name = it },
                        label = "Package Name",
                        placeholder = "e.g. Bali Escape 7D/6N",
                        icon = Icons.Outlined.Home,
                        keyboardType = KeyboardType.Text
                    )
                    ModernField(
                        value = description,
                        onValueChange = { description = it },
                        label = "Description",
                        placeholder = "What's included in this trip?",
                        icon = Icons.Outlined.Info,
                        keyboardType = KeyboardType.Text,
                        singleLine = false,
                        minLines = 3
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ModernField(
                            value = price,
                            onValueChange = { price = it },
                            label = "Price ($)",
                            placeholder = "0.00",
                            icon = Icons.Outlined.AccountBox,
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.weight(1f)
                        )
                        ModernField(
                            value = duration,
                            onValueChange = { duration = it },
                            label = "Duration",
                            placeholder = "e.g. 7 days",
                            icon = Icons.Outlined.DateRange,
                            keyboardType = KeyboardType.Text,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(4.dp))

                    // ── Submit button ─────────────────────────────────────
                    Button(
                        onClick = {
                            when {
                                selectedImageUri == null ->
                                    Toast.makeText(context, "Please select an image", Toast.LENGTH_SHORT).show()
                                name.isBlank() || price.isBlank() || description.isBlank() || duration.isBlank() ->
                                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                                else -> {
                                    isLoading = true
                                    viewModel.uploadImage(context, selectedImageUri!!) { imageUrl ->
                                        imageUrl?.let {
                                            val model = ProductModel(
                                                productName = name,
                                                productPrice = price,
                                                productDescription = description,
                                                productTime = duration,
                                                productImage = imageUrl
                                            )
                                            viewModel.addProduct(model) { success, msg ->
                                                isLoading = false
                                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                            }
                                        } ?: run {
                                            isLoading = false
                                            Toast.makeText(context, "Image upload failed", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(0.dp),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = if (!isLoading)
                                        SolidColor( turcoise)
                                    else
                                        Brush.horizontalGradient(listOf(Slate, Slate)),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    CircularProgressIndicator(
                                        color = Color.White,
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp
                                    )
                                    Text("Uploading...", color = Color.White, fontWeight = FontWeight.SemiBold)
                                }
                            } else {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Outlined.Check, contentDescription = null, tint = Color.White)
                                    Text(
                                        "Publish Package",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    // Bottom nav bar padding
                    Spacer(Modifier.navigationBarsPadding())
                }

                // Scroll indicator bar on right edge
                val scrollFraction = if (scrollState.maxValue > 0)
                    scrollState.value.toFloat() / scrollState.maxValue else 0f

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 4.dp, top = 20.dp)
                        .width(3.dp)
                        .fillMaxHeight(0.85f)
                        .clip(RoundedCornerShape(2.dp))
                        .background(TealLight)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(scrollFraction.coerceAtLeast(0.05f))
                            .align(Alignment.TopCenter)
                            .background(Teal, RoundedCornerShape(2.dp))
                    )
                }
            }
        }
    }
}

@Composable
fun ModernField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Navy.copy(alpha = 0.7f)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(placeholder, color = Slate.copy(alpha = 0.6f), fontSize = 14.sp)
            },
            leadingIcon = {
                Icon(icon, contentDescription = null, tint = Teal, modifier = Modifier.size(20.dp))
            },
            singleLine = singleLine,
            minLines = minLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Teal,
                unfocusedBorderColor = Color(0xFFB2DFDB),
                focusedContainerColor = TealLight.copy(alpha = 0.4f),
                unfocusedContainerColor = Color.White,
                cursorColor = Teal,
                focusedTextColor = Navy,
                unfocusedTextColor = Navy
            ),
            textStyle = TextStyle(fontSize = 15.sp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}