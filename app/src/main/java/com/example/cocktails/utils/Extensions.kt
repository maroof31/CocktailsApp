package com.example.cocktails.utils

inline fun String?.orEmpty(): String  = this ?: ""
inline fun Boolean?.orFalse(): Boolean = this?: false
