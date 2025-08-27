package com.pe.mascotapp.vistas

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pe.mascotapp.R
import com.pe.mascotapp.caprasimoTitleStyle
import com.pe.mascotapp.colorDisabled
import com.pe.mascotapp.colorHeader
import com.pe.mascotapp.colorHorizontal
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.colorPrimary
import com.pe.mascotapp.databinding.ActivityProfileBinding
import com.pe.mascotapp.descriptionTextStyle
import com.pe.mascotapp.domain.models.Sex
import com.pe.mascotapp.semiBoldTitleStyle
import com.pe.mascotapp.vistas.entities.PetEntity

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        binding.composeViewProfile.setContent {
            UserProfileScreen()
        }
        setContentView(binding.root)
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        ViewCompat.setOnApplyWindowInsetsListener(w.decorView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }
    }
}
@Preview
@Composable
fun UserProfileScreen() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var isEditing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .background(colorHeader)
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 21.dp, top = 32.dp, bottom = 11.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(
                    contentPadding = PaddingValues(),
                    elevation = ButtonDefaults.buttonElevation(0.dp),
                    onClick = {
                        (context as? Activity)?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(colorHeader)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(colorPrimary)
                    )
                }
                Text(
                    text = "Perfil",
                    style = caprasimoTitleStyle.copy(color = colorPrimary)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color.White)
        ) {
            ProfileHeader(
                imageUrl = R.drawable.img_prueba,
                name = "Julian Alvarez",
                onEditClick = { isEditing = !isEditing }
            )
            Spacer(modifier = Modifier.height(32.dp))

            UserInfo(
                id = "47717687",
                email = "jalvarez@gmail.com",
                phone = "+51 999 888 777",
                birthDate = "14 de Diciembre, 1992",
                gender = "Masculino",
                address = "Jr. 24, 243 San Borja, Dept 101, Lima Metropolitana",
                isEditing = isEditing
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalLine()
            Spacer(modifier = Modifier.height(16.dp))
            PetsSection(
                pets = listOf(
                    PetEntity(
                        null,
                        "https://www.telegraph.co.uk/content/dam/news/2023/06/10/TELEMMGLPICT000296384999_16864028803870_trans_NvBQzQNjv4BqrCS9JVgwgb8GODK1xmD4xlHwtdpQwyNje2OyIL7x97s.jpeg",
                        "Paul Pugba1",
                        "Perro",
                        "100",
                        Sex.MALE.toString(),
                        birthdate = "12/02/2010",
                        false
                    ),
                    PetEntity(
                        null,
                        "https://static01.nyt.com/images/2024/01/16/multimedia/16xp-dog-01-lchw/16xp-dog-01-lchw-videoSixteenByNineJumbo1600.jpg",
                        "Paul",
                        "Perro",
                        "101",
                        Sex.MALE.toString(),
                        birthdate = "12/02/2010",
                        false
                    ),
                    PetEntity(
                        null,
                        "https://cdn.britannica.com/79/232779-050-6B0411D7/German-Shepherd-dog-Alsatian.jpg",
                        "Paul Pugba3",
                        "Perro",
                        "102",
                        Sex.MALE.toString(),
                        birthdate = "12/02/2010",
                        false
                    )
                )
            )
            PreferencesSection()
        }
    }
}
@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    color: Color = colorHorizontal,
    maxWidth: Dp = Dp.Infinity
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(color)
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileHeader(imageUrl: Int, name: String, onEditClick: () -> Unit) {
    var editableName by remember { mutableStateOf(name) }
    var isEditing by remember { mutableStateOf(false) }

    Box(
        Modifier
            .background(color = Color(0xFFF8F8F8))
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bkg_profile_head),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop,
            alpha = 0.5f
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 16.dp)
        ) {

            Image(
                painter = painterResource(id = imageUrl),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.height(2.dp))
            if (isEditing) {
                TextField(
                    value = editableName,
                    onValueChange = { editableName = it },
                    textStyle = semiBoldTitleStyle.copy(fontSize = 20.sp, color = Color.Black),
                    singleLine = true,
                    modifier = Modifier
                        .wrapContentWidth()
                        .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .background(Color.White)
                )
            } else {
                Text(
                    text = editableName,
                    style = semiBoldTitleStyle.copy(fontSize = 30.sp, color = colorMediumBlue),
                )
            }
        }

        // Botón de edición
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .width(26.dp)
                .background(colorMediumBlue, RoundedCornerShape(4.dp))
                .clickable {
                    isEditing = !isEditing
                    onEditClick()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_edit_new),
                contentDescription = "Edit Icon",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun UserInfo(
    id: String,
    email: String,
    phone: String,
    birthDate: String,
    gender: String,
    address: String,
    isEditing: Boolean
) {
    var editableId by remember { mutableStateOf(id) }
    var editableEmail by remember { mutableStateOf(email) }
    var editablePhone by remember { mutableStateOf(phone) }
    var editableBirthDate by remember { mutableStateOf(birthDate) }
    var editableGender by remember { mutableStateOf(gender) }
    var editableAddress by remember { mutableStateOf(address) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 32.dp)
    ) {
        Text(
            text = "Información",
            style = caprasimoTitleStyle.copy(fontSize = 18.sp, color = colorPrimary),
        )


        InfoRow(label = "Número de identidad", value = editableId, isEditable = isEditing) { editableId = it }
        InfoRow(label = "Email", value = editableEmail, isEditable = isEditing) { editableEmail = it }
        InfoRow(label = "Celular", value = editablePhone, isEditable = isEditing) { editablePhone = it }
        InfoRow(label = "Fecha de nacimiento", value = editableBirthDate, isEditable = isEditing) { editableBirthDate = it }
        InfoRow(label = "Género", value = editableGender, isEditable = isEditing) { editableGender = it }
        InfoRow(label = "Dirección", value = editableAddress, isEditable = isEditing) { editableAddress = it }
    }
}


private fun ColumnScope.DropdownMenuItem(
    onClick: () -> Unit,
    interactionSource: @Composable () -> Unit
) {
}

@Composable
fun InfoRow(label: String, value: String, isEditable: Boolean, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            style = semiBoldTitleStyle.copy(color = colorDisabled, fontSize = 18.sp),
        )

        if (isEditable) {
            TextField(
                value = value,
                onValueChange = { newValue -> onValueChange(newValue) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = descriptionTextStyle.copy(color = colorDisabled, fontSize = 16.sp),
                singleLine = true
            )
        } else {
            Text(
                text = value,
                style = descriptionTextStyle.copy(color = colorDisabled, fontSize = 16.sp)
            )
        }
    }
}


@Composable
fun PetsSection(pets: List<PetEntity>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Mis mascotas",
            style = caprasimoTitleStyle.copy(fontSize = 18.sp, color = colorPrimary),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            pets.forEach { pet ->
                PetItem(pet = pet)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PetItem(pet: PetEntity, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            model = pet.image,
            contentDescription = pet.name,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pet.name,
                style = semiBoldTitleStyle.copy(color = colorDisabled, fontSize = 20.sp),
            )
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = pet.specieDrawable()),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = pet.specie,
                    style = descriptionTextStyle.copy(color = colorDisabled, fontSize = 18.sp)
                )
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun PreferencesSection() {
    var notificationsEnabled by remember { mutableStateOf(false) }
    var soundEnabled by remember { mutableStateOf(false) }
    var vibrationEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Preferencias",
            style = caprasimoTitleStyle.copy(fontSize = 18.sp, color = colorPrimary),
        )
        Spacer(modifier = Modifier.height(8.dp))

        SwitchPreference(
            label = "Notificaciones",
            isEnabled = notificationsEnabled,
            onCheckedChange = { notificationsEnabled = it }
        )
        SwitchPreference(
            label = "Sonido",
            isEnabled = soundEnabled,
            onCheckedChange = { soundEnabled = it }
        )
        SwitchPreference(
            label = "Vibración",
            isEnabled = vibrationEnabled,
            onCheckedChange = { vibrationEnabled = it }
        )
    }
}

@Composable
fun SwitchPreference(label: String, isEnabled: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = semiBoldTitleStyle.copy(color = colorDisabled, fontSize = 18.sp),
        )
        CustomSwitch(
            checked = isEnabled,
            onCheckedChange = onCheckedChange,
            borderColor = Color.White,
            thumbColor = Color.White
        )
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 2.dp,
    thumbColor: Color = Color.White,
    checkedTrackColor: Color = Color.Black,
    uncheckedTrackColor: Color = colorDisabled
) {
    val thumbSize = 15.dp
    val trackHeight = 20.dp
    val trackWidth = 44.dp

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) trackWidth - thumbSize - 4.dp else 4.dp,
        animationSpec = tween(durationMillis = 200)
    )

    Box(
        modifier = modifier
            .size(trackWidth, trackHeight)
            .border(borderWidth, borderColor, RoundedCornerShape(10.dp))
            .clickable {
                onCheckedChange(!checked)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (checked) checkedTrackColor else uncheckedTrackColor,
                    RoundedCornerShape(10.dp)
                )
        )

        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = thumbOffset)
                .background(thumbColor, CircleShape)
        )
    }
}
