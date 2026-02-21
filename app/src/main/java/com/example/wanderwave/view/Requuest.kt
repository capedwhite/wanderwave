package com.example.wanderwave.view

package com.example.wanderwave.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

// ── Data ──────────────────────────────────────────────────────────────────────
data class PackageType(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val tag: String
)

private val packageTypes = listOf(
    PackageType("adventure", "Adventure",    "Hiking, trekking & outdoor thrills",   Icons.Outlined.Terrain,       "Active"),
    PackageType("beach",     "Beach Escape", "Sun, sand & ocean relaxation",          Icons.Outlined.BeachAccess,   "Relaxing"),
    PackageType("cultural",  "Cultural",     "History, art & local experiences",      Icons.Outlined.AccountBalance,"Enriching"),
    PackageType("luxury",    "Luxury",       "5-star stays & premium experiences",    Icons.Outlined.Diamond,       "Premium"),
    PackageType("family",    "Family Trip",  "Fun for all ages, kid-friendly",        Icons.Outlined.FamilyRestroom,"Family"),
    PackageType("solo",      "Solo Travel",  "Freedom to explore at your own pace",   Icons.Outlined.PersonPin,     "Solo"),
)

data class BudgetRange(val id: String, val label: String, val sublabel: String)

private val budgets = listOf(
    BudgetRange("economy",  "Economy",   "Under \$500"),
    BudgetRange("mid",      "Mid-range", "\$500 – \$1500"),
    BudgetRange("premium",  "Premium",   "\$1500 – \$3000"),
    BudgetRange("luxury",   "Luxury",    "\$3000+"),
)

data class DurationOption(val id: String, val label: String, val sublabel: String)

private val durations = listOf(
    DurationOption("weekend", "Weekend",  "2–3 days"),
    DurationOption("week",    "1 Week",   "5–7 days"),
    DurationOption("two",     "2 Weeks",  "12–14 days"),
    DurationOption("month",   "1 Month+", "30+ days"),
)

// ── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun RequestPackageScreen(onBack: () -> Unit = {}) {
    var selectedType     by remember { mutableStateOf<String?>(null) }
    var selectedBudget   by remember { mutableStateOf<String?>(null) }
    var selectedDuration by remember { mutableStateOf<String?>(null) }
    var destination      by remember { mutableStateOf("") }
    var extraNotes       by remember { mutableStateOf("") }
    val scrollState      = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize().background(Cream)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 100.dp)
        ) {

            // ── Header ───────────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TurcoiseDeep)
                    .statusBarsPadding()
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = 30.dp, y = (-10).dp)
                        .background(Color.White.copy(alpha = 0.05f), CircleShape)
                )
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color.White.copy(alpha = 0.1f), CircleShape)
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                    Spacer(Modifier.height(14.dp))
                    Text("REQUEST",    color = TurcoiseGlow, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 3.sp)
                    Spacer(Modifier.height(2.dp))
                    Text("Custom Package", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text("Tell us what you're looking for", color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                    Spacer(Modifier.height(20.dp))
                }
            }

            Spacer(Modifier.height(20.dp))

            // ── Step 1: Package Type ──────────────────────────────────────────
            SectionHeader(step = "01", title = "Trip Type", subtitle = "What kind of experience are you after?")

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                packageTypes.chunked(2).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { pkg ->
                            PackageTypeCard(
                                pkg = pkg,
                                selected = selectedType == pkg.id,
                                onClick = { selectedType = pkg.id },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (row.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }

            Spacer(Modifier.height(28.dp))

            // ── Step 2: Destination ───────────────────────────────────────────
            SectionHeader(step = "02", title = "Destination", subtitle = "Where do you want to go?")

            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                OutlinedTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = { Text("e.g. Bali, Tokyo, Paris...", color = TextSoft, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Outlined.TravelExplore, contentDescription = null, tint = Turcoise, modifier = Modifier.size(20.dp))
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Turcoise,
                        unfocusedBorderColor = TurcoiseGlow,
                        focusedContainerColor = TurcoiseLight.copy(alpha = 0.4f),
                        unfocusedContainerColor = CardBg,
                        cursorColor = Turcoise,
                        focusedTextColor = TextDark,
                        unfocusedTextColor = TextDark
                    )
                )
            }

            Spacer(Modifier.height(28.dp))

            // ── Step 3: Budget ────────────────────────────────────────────────
            SectionHeader(step = "03", title = "Budget", subtitle = "What's your price range per person?")

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                budgets.forEach { b ->
                    BudgetChip(
                        budget = b,
                        selected = selectedBudget == b.id,
                        onClick = { selectedBudget = b.id },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            // ── Step 4: Duration ──────────────────────────────────────────────
            SectionHeader(step = "04", title = "Duration", subtitle = "How long is your trip?")

            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                durations.forEach { d ->
                    DurationChip(
                        duration = d,
                        selected = selectedDuration == d.id,
                        onClick = { selectedDuration = d.id },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(Modifier.height(28.dp))

            // ── Step 5: Extra notes ───────────────────────────────────────────
            SectionHeader(step = "05", title = "Special Requests", subtitle = "Anything else we should know?")

            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                OutlinedTextField(
                    value = extraNotes,
                    onValueChange = { extraNotes = it },
                    placeholder = { Text("Dietary needs, accessibility, special occasions...", color = TextSoft, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(Icons.Outlined.EditNote, contentDescription = null, tint = Turcoise, modifier = Modifier.size(20.dp))
                    },
                    singleLine = false,
                    minLines = 3,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Turcoise,
                        unfocusedBorderColor = TurcoiseGlow,
                        focusedContainerColor = TurcoiseLight.copy(alpha = 0.4f),
                        unfocusedContainerColor = CardBg,
                        cursorColor = Turcoise,
                        focusedTextColor = TextDark,
                        unfocusedTextColor = TextDark
                    )
                )
            }

            Spacer(Modifier.height(28.dp))

            // ── Summary preview ───────────────────────────────────────────────
            if (selectedType != null || selectedBudget != null || selectedDuration != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(TurcoiseLight)
                        .padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Your Selection", fontWeight = FontWeight.Bold, color = TurcoiseDeep, fontSize = 14.sp)
                        HorizontalDivider(color = TurcoiseGlow, thickness = 1.dp)
                        selectedType?.let {
                            val pkg = packageTypes.find { p -> p.id == it }
                            SummaryRow(Icons.Outlined.Terrain, "Type", pkg?.title ?: "")
                        }
                        if (destination.isNotBlank())
                            SummaryRow(Icons.Outlined.TravelExplore, "Destination", destination)
                        selectedBudget?.let {
                            val b = budgets.find { b -> b.id == it }
                            SummaryRow(Icons.Outlined.AttachMoney, "Budget", "${b?.label} (${b?.sublabel})")
                        }
                        selectedDuration?.let {
                            val d = durations.find { d -> d.id == it }
                            SummaryRow(Icons.Outlined.Schedule, "Duration", "${d?.label} (${d?.sublabel})")
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }

        // ── Submit FAB ────────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Cream)
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .navigationBarsPadding()
        ) {
            Button(
                onClick = { /* handle submit */ },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = TurcoiseDeep),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Icon(Icons.Outlined.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Submit Request", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ── Sub-components ────────────────────────────────────────────────────────────

@Composable
fun SectionHeader(step: String, title: String, subtitle: String) {
    Row(
        modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(TurcoiseDeep, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(step, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextDark)
            Text(subtitle, fontSize = 12.sp, color = TextSoft)
        }
    }
}

@Composable
fun PackageTypeCard(pkg: PackageType, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bg     = if (selected) TurcoiseDeep else CardBg
    val border = if (selected) TurcoiseDeep else TurcoiseGlow
    val txtCol = if (selected) Color.White  else TextDark
    val subCol = if (selected) Color.White.copy(alpha = 0.7f) else TextSoft
    val tagBg  = if (selected) Color.White.copy(alpha = 0.15f) else TurcoiseLight

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(bg)
            .border(1.5.dp, border, RoundedCornerShape(18.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(tagBg, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(pkg.icon, contentDescription = null, tint = if (selected) Color.White else Turcoise, modifier = Modifier.size(22.dp))
                }
                if (selected) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                    }
                }
            }
            Text(pkg.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = txtCol)
            Text(pkg.subtitle, fontSize = 11.sp, color = subCol, lineHeight = 15.sp)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(tagBg)
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(pkg.tag, fontSize = 10.sp, color = if (selected) Color.White else Turcoise, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun BudgetChip(budget: BudgetRange, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bg     = if (selected) TurcoiseDeep else CardBg
    val border = if (selected) TurcoiseDeep else TurcoiseGlow
    val txtCol = if (selected) Color.White  else TextDark
    val subCol = if (selected) Color.White.copy(alpha = 0.7f) else TextSoft

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .border(1.5.dp, border, RoundedCornerShape(14.dp))
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(budget.label,    fontWeight = FontWeight.Bold, fontSize = 12.sp, color = txtCol, textAlign = TextAlign.Center)
        Text(budget.sublabel, fontSize = 10.sp, color = subCol, textAlign = TextAlign.Center)
    }
}

@Composable
fun DurationChip(duration: DurationOption, selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val bg     = if (selected) TurcoiseDeep else CardBg
    val border = if (selected) TurcoiseDeep else TurcoiseGlow
    val txtCol = if (selected) Color.White  else TextDark
    val subCol = if (selected) Color.White.copy(alpha = 0.7f) else TextSoft

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .border(1.5.dp, border, RoundedCornerShape(14.dp))
            .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Text(duration.label,    fontWeight = FontWeight.Bold, fontSize = 12.sp, color = txtCol, textAlign = TextAlign.Center)
        Text(duration.sublabel, fontSize = 10.sp, color = subCol, textAlign = TextAlign.Center)
    }
}

@Composable
fun SummaryRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Turcoise, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(8.dp))
        Text("$label: ", fontSize = 13.sp, color = TextMid, fontWeight = FontWeight.SemiBold)
        Text(value, fontSize = 13.sp, color = TextDark)
    }
}

@Preview(showBackground = true)
@Composable
fun RequestPackagePreview() {
    RequestPackageScreen()
}