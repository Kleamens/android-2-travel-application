package com.example.mlkittahak

import com.google.mlkit.vision.text.Text

interface OnTextFoundListener {

    fun onTextFound(text:Text)
    fun onFailuer(exception: Exception)
}