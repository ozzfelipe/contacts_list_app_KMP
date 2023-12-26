package com.example.contactslistkmp.core.presentation

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
actual fun rememberBitmapFromBytes(byteArray: ByteArray?): ImageBitmap? {
    return remember {
        if (byteArray != null) {
            BitmapFactory
                .decodeByteArray(byteArray, 0, byteArray.size)
                .asImageBitmap()
        } else {
            null
        }
    }
}