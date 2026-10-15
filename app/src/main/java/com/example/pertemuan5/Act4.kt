package com.example.pertemuan5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun FormDataDiri(modifier: Modifier = Modifier) {
// Variable untuk mengingat nilai masukan dari keyboard
    var textName by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    val gender: List<String> = listOf("laki-laki", "Perempuan")
// Variabel-variabel untuk menyimpan data yang diperoleh dari komponen UI
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
// Column Utama
    Column(
        modifier = modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = textName,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(250.dp),
            label = { Text(text = "Nama Lengkap") },
            onValueChange = { textName = it }
        )
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp) // Tambahan padding agar lebih rapi
        ) {
            gender.forEach { item ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = textJK == item,
                            onClick = { textJK = item }
                        )
                        .padding(end = 16.dp), // Tambahan padding antar radio button
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = textJK == item,
                        onClick = { textJK = item }
                    )
                    Text(item)
                    OutlinedTextField(
                        value = textAlamat,
                        singleLine = true,
                        modifier = Modifier.width(250.dp),
                        label = { Text("Alamat") },
                        onValueChange = { textAlamat = it }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.padding_medium),
                            top = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        thickness = dimensionResource(id = R.dimen.divider_tipis),
                        color = Color.DarkGray
                    )
                    Button(
                        modifier = Modifier.width(250.dp), // Disamakan dengan lebar text field agar rapi
                        // the button is enabled when the user makes a selection
                        enabled = textAlamat.isNotEmpty() && textName.isNotEmpty() && textJK.isNotEmpty(), // Diperbarui agar button aktif jika semua form diisi
                        onClick = {
                            nama = textName
                            jenis = textJK
                            alamat = textAlamat
                        }
                    ) {
                        Text(stringResource(id = R.string.submit))
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.padding_medium),
                            top = dimensionResource(id = R.dimen.padding_medium)
                        ),
                        thickness = dimensionResource(id = R.dimen.divider_tipis),
                        color = Color.DarkGray
                    )
                    // ElevatedCard diletakkan di DALAM Column utama
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Black),
                        modifier = Modifier
                            .padding(top = 16.dp) // Tambahan padding atas agar tidak menempel dengan divider
                            .height(100.dp)
                            .width(300.dp)
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
                            Text(text = "Nama : $nama", color = Color.White)
                            Text(text = "Gender : $jenis", color = Color.White)
                            Text(text = "Alamat : $alamat", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}