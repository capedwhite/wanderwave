package com.example.wanderwave.view

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wanderwave.model.ChallenegesModel
import com.example.wanderwave.repository.ChallengeRepoImpl
import com.example.wanderwave.viewmodel.ChallengeViewModel

// ── Palette (same as HomeScreen) ──────────────────────────────────────────────
private val Turcoise      = Color(0xFF638C80)
private val TurcoiseDeep  = Color(0xFF3D6457)
private val TurcoiseLight = Color(0xFFE8F2EE)
private val TurcoiseGlow  = Color(0xFFB2CEC6)
private val Cream         = Color(0xFFF5F7F5)
private val TextDark      = Color(0xFF1A2E28)
private val TextMid       = Color(0xFF4A6660)
private val TextSoft      = Color(0xFF8AABA3)
private val CardBg        = Color(0xFFFFFFFF)
private val ChipBg        = Color(0xFFDCEDE7)

@Composable
fun ChallengeScreen() {
    var challengetitle  by remember { mutableStateOf("") }
    var challengedetail by remember { mutableStateOf("") }
    var challengetime   by remember { mutableStateOf("") }
    var showDialog      by remember { mutableStateOf(false) }
    val challengeviewmodel = remember { ChallengeViewModel(ChallengeRepoImpl()) }
    val selectedchallenge by challengeviewmodel.selectedChallenge.observeAsState(null)
    val allChallenges     by challengeviewmodel.allChallenges.observeAsState(initial = emptyList())
    val context = LocalContext.current

    LaunchedEffect(Unit) { challengeviewmodel.getAllChallenges() }

    LaunchedEffect(selectedchallenge) {
        selectedchallenge?.let {
            challengetitle  = it.title
            challengedetail = it.description
            challengetime   = it.time
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Cream)) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {

            // ── Header ───────────────────────────────────────────────────────
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(TurcoiseDeep, Turcoise)))
                        .padding(horizontal = 24.dp)
                        .padding(top = 52.dp, bottom = 32.dp)
                ) {
                    // Decorative circles
                    Box(
                        Modifier
                            .size(160.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 50.dp, y = (-30).dp)
                            .background(Color.White.copy(alpha = 0.07f), CircleShape)
                    )
                    Box(
                        Modifier
                            .size(90.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = (-20).dp, y = 20.dp)
                            .background(Color.White.copy(alpha = 0.06f), CircleShape)
                    )

                    Column {
                        Text(
                            "WANDER",
                            color = TurcoiseGlow,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 3.sp
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Challenges",
                            color = Color.White,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Push your limits, explore more",
                            color = Color.White.copy(alpha = 0.65f),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            // ── Count strip ──────────────────────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${allChallenges.size} Active Challenges",
                        color = TextDark,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(ChipBg)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text("All", color = Turcoise, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // ── Challenge cards ──────────────────────────────────────────────
            itemsIndexed(allChallenges) { _, data ->
                ChallengeCard(
                    data = data,
                    challengeViewModel = challengeviewmodel,
                    onEditClick = {
                        challengeviewmodel.getChallengeById(data.challengeId)
                        showDialog = true
                    }
                )
                Spacer(Modifier.height(16.dp))
            }
        }

        // ── FAB ───────────────────────────────────────────────────────────────
        FloatingActionButton(
            onClick = { context.startActivity(Intent(context, AddChallenges::class.java)) },
            modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp),
            containerColor = TurcoiseDeep,
            contentColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Text("Add", fontWeight = FontWeight.SemiBold)
            }
        }

        // ── Edit dialog ───────────────────────────────────────────────────────
        if (showDialog && selectedchallenge != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                shape = RoundedCornerShape(24.dp),
                containerColor = CardBg,
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(TurcoiseLight, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = null,
                                tint = Turcoise,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Update Challenge",
                            fontWeight = FontWeight.Bold,
                            color = TextDark,
                            fontSize = 18.sp
                        )
                    }
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        ChallengeDialogField("Challenge Name", challengetitle, Icons.Outlined.EmojiEvents) { challengetitle = it }
                        ChallengeDialogField("Description", challengedetail, Icons.Outlined.Description) { challengedetail = it }
                        ChallengeDialogField("Duration / Time", challengetime, Icons.Outlined.Schedule) { challengetime = it }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val updated = ChallenegesModel(
                                challengeId = selectedchallenge!!.challengeId,
                                title       = challengetitle,
                                description = challengedetail,
                                time        = challengetime
                            )
                            challengeviewmodel.updateChallenge(updated) { success, _ ->
                                if (success) {
                                    challengeviewmodel.getAllChallenges()
                                    showDialog = false
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Turcoise),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Update", fontWeight = FontWeight.SemiBold) }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel", color = TextMid)
                    }
                }
            )
        }
    }
}

@Composable
fun ChallengeCard(
    data: ChallenegesModel,
    challengeViewModel: ChallengeViewModel,
    onEditClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val chevronRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(300),
        label = "chevron"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(if (expanded) 12.dp else 4.dp, RoundedCornerShape(24.dp))
            .animateContentSize(animationSpec = tween(300)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = CardBg),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column {

            // ── Coloured accent bar at top ─────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(Brush.horizontalGradient(listOf(TurcoiseDeep, Turcoise, TurcoiseGlow)))
            )

            Column(modifier = Modifier.padding(18.dp)) {

                // ── Title row ──────────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon badge
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(TurcoiseLight, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Outlined.EmojiEvents,
                            contentDescription = null,
                            tint = Turcoise,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            data.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextDark,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(2.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Outlined.Schedule,
                                contentDescription = null,
                                tint = TextSoft,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(data.time, color = TextSoft, fontSize = 12.sp)
                        }
                    }

                    // Expand chevron
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(TurcoiseLight)
                    ) {
                        Icon(
                            Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Turcoise,
                            modifier = Modifier.rotate(chevronRotation)
                        )
                    }
                }

                // Description preview
                Spacer(Modifier.height(10.dp))
                Text(
                    data.description,
                    color = TextSoft,
                    fontSize = 13.sp,
                    maxLines = if (expanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis
                )

                // ── Expanded section ───────────────────────────────────────
                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
                        Spacer(Modifier.height(14.dp))
                        HorizontalDivider(color = TurcoiseLight, thickness = 1.dp)
                        Spacer(Modifier.height(14.dp))

                        Text(
                            "About this challenge",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = TextDark
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            data.description,
                            color = TextMid,
                            fontSize = 14.sp,
                            lineHeight = 22.sp
                        )

                        Spacer(Modifier.height(6.dp))

                        // Time chip
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(TurcoiseLight)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Icon(Icons.Outlined.Schedule, contentDescription = null, tint = Turcoise, modifier = Modifier.size(14.dp))
                            Text(data.time, color = TurcoiseDeep, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Spacer(Modifier.height(16.dp))

                        // Action buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedButton(
                                onClick = onEditClick,
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.5.dp, Turcoise)
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null, tint = Turcoise, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Edit", color = Turcoise, fontWeight = FontWeight.SemiBold)
                            }

                            Button(
                                onClick = {
                                    challengeViewModel.deleteChallenge(data.challengeId) { success, _ ->
                                        if (success) challengeViewModel.getAllChallenges()
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE))
                            ) {
                                Icon(Icons.Outlined.DeleteOutline, contentDescription = null, tint = Color(0xFFE53935), modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(6.dp))
                                Text("Delete", color = Color(0xFFE53935), fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChallengeDialogField(
    label: String,
    value: String,
    icon: ImageVector,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label, fontSize = 13.sp) },
        leadingIcon = {
            Icon(icon, contentDescription = null, tint = Turcoise, modifier = Modifier.size(18.dp))
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Turcoise,
            unfocusedBorderColor = TurcoiseGlow,
            focusedContainerColor = TurcoiseLight.copy(alpha = 0.3f),
            unfocusedContainerColor = Color.White,
            cursorColor = Turcoise,
            focusedTextColor = TextDark,
            unfocusedTextColor = TextDark,
            focusedLabelColor = Turcoise
        )
    )
}