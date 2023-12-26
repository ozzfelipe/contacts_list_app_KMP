package com.example.contactslistkmp.core.utils

var PHONE_PATTERN: Regex = Regex("^(\\d{2})(9)(\\d{4})(\\d{4})\$")
var CLEAR_TEXT_PATTERN: Regex = Regex("[^0-9]")

fun CharSequence.isPhoneNumber() : Boolean = PHONE_PATTERN.matches(this.replace(CLEAR_TEXT_PATTERN, ""))

var EMAIL_PATTERN: Regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")

fun CharSequence.isValidEmail() : Boolean = EMAIL_PATTERN.matches(this)