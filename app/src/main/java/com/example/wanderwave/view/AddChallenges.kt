package com.example.wanderwave.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.model.ChallenegesModel
import com.example.wanderwave.repository.ChallengeRepoImpl
import com.example.wanderwave.viewmodel.ChallengeViewModel

// ── Palette ───────────────────────────────────────────────────────────────────
private val Turcoise      = Color(0xFF638C80)
private val TurcoiseDeep  = Color(0xFF3D6457)
private val TurcoiseLight = Color(0xFFE8F2EE)
private val TurcoiseGlow  = Color(0xFFB2CEC6)
private val Cream         = Color(0xFFF5F7F5)
private val TextDark      = Color(0xFF1A2E28)
private val TextMid       = Color(0xFF4A6660)
private val TextSoft      = Color(0xFF8AABA3)
private val CardBg        = Color(0xFFFFFFFF)

class AddChallenges : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { AddChallengesScreen(onBack = { finish() }) }
    }
}

@Composable
fun AddChallengesScreen(onBack: () -> Unit = {}) {
    var title       by remember { mutableStateOf("") }
    var detail      by remember { mutableStateOf("") }
    var time        by remember { mutableStateOf("") }
    var isLoading   by remember { mutableStateOf(false) }
    val viewModel   = remember { ChallengeViewModel(ChallengeRepoImpl()) }
    val context     = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            // ── Header block ─────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TurcoiseDeep)
                    .statusBarsPadding()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                // Decorative circle
                Box(
                    Modifier
                        .size(120.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 30.dp, y = (-20).dp)
                        .background(Color.White.copy(alpha = 0.06f), CircleShape)
                )

                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.White.copy(alpha = 0.12f), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        "CHALLENGES",
                        color = TurcoiseGlow,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "New Challenge",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Set a goal and push your limits",
                        color = Color.White.copy(alpha = 0.6f),
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }

            // ── Form card ────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(CardBg)
                    .padding(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

                    // Section label
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(4.dp)
                                .background(Turcoise, CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Challenge Details",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextDark
                        )
                    }

                    AddChallengeField(
                        value = title,
                        onValueChange = { title = it },
                        label = "Challenge Title",
                        placeholder = "e.g. Hike 10km this weekend",
                        icon = Icons.Outlined.EmojiEvents,
                        keyboardType = KeyboardType.Text
                    )

                    AddChallengeField(
                        value = detail,
                        onValueChange = { detail = it },
                        label = "Description",
                        placeholder = "What does this challenge involve?",
                        icon = Icons.Outlined.Description,
                        keyboardType = KeyboardType.Text,
                        singleLine = false,
                        minLines = 3
                    )

                    AddChallengeField(
                        value = time,
                        onValueChange = { time = it },
                        label = "Time Limit",
                        placeholder = "e.g. 3 days, 1 week",
                        icon = Icons.Outlined.Schedule,
                        keyboardType = KeyboardType.Text
                    )

                    Spacer(Modifier.height(4.dp))

                    // ── Submit button ─────────────────────────────────────────
                    Button(
                        onClick = {
                            when {
                                title.isBlank() || detail.isBlank() || time.isBlank() ->
                                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                                else -> {
                                    isLoading = true
                                    val model = ChallenegesModel(
                                        title       = title,
                                        description = detail,
                                        time        = time
                                    )
                                    viewModel.addChallenge(model) { success, msg ->
                                        isLoading = false
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                        if (success) {
                                            title  = ""
                                            detail = ""
                                            time   = ""
                                        }
                                    }
                                }
                            }
                        },
                        enabled = !isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TurcoiseDeep,
                            disabledContainerColor = TurcoiseGlow
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        if (isLoading) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp
                                )
                                Text("Saving...", color = Color.White, fontWeight = FontWeight.SemiBold)
                            }
                        } else {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Outlined.EmojiEvents, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                Text(
                                    "Add Challenge",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // ── Tip card ─────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 32.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(TurcoiseLight)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.Top) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Turcoise.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.Lightbulb,
                            contentDescription = null,
                            tint = Turcoise,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "Pro Tip",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TurcoiseDeep
                        )
                        Spacer(Modifier.height(3.dp))
                        Text(
                            "Set a realistic time limit and break your challenge into smaller milestones for better results.",
                            fontSize = 12.sp,
                            color = TextMid,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddChallengeField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextMid
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(placeholder, color = TextSoft, fontSize = 14.sp)
            },
            leadingIcon = {
                Icon(icon, contentDescription = null, tint = Turcoise, modifier = Modifier.size(20.dp))
            },
            singleLine = singleLine,
            minLines = minLines,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Turcoise,
                unfocusedBorderColor = TurcoiseGlow,
                focusedContainerColor = TurcoiseLight.copy(alpha = 0.4f),
                unfocusedContainerColor = Color.White,
                cursorColor = Turcoise,
                focusedTextColor = TextDark,
                unfocusedTextColor = TextDark
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}