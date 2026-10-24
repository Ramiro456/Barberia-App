package com.example.barberia_app.components

import android.widget.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.example.barberia_app.ui.theme.goldPrimary
import com.example.barberia_app.ui.theme.textDark

@Composable
fun BarberButton(
    text: String,
    onClick: () -> Unit, // la accion que se lanzara cuando el usuario presione el boton
    modifier: Modifier = Modifier,
    enabled: Boolean = true //para desactivar
    ){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp), // esquinas redondeadas
        colors = ButtonDefaults.buttonColors(
            containerColor = goldPrimary,
            contentColor = textDark,
            disabledContainerColor = goldPrimary.copy(alpha = 0.4f), //atenua el color para que el usuario entienda que no puede presionar el boton (QUIZAS LO SAQUE)
            disabledContentColor = textDark.copy(alpha = 0.5f)
        )
    ){
      Text(
          text = text,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
      )
    }
}