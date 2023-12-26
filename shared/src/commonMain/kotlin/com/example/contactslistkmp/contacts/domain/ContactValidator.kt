package com.example.contactslistkmp.contacts.domain

import com.example.contactslistkmp.contacts.domain.entities.Contact
import com.example.contactslistkmp.core.utils.isPhoneNumber
import com.example.contactslistkmp.core.utils.isValidEmail

object ContactValidator {
    fun validateContact(contact: Contact): ValidationResult {
        var result = ValidationResult()

        if (contact.firstName.isBlank()) {
            result = result.copy(firstNameError = "First name can't be empty.")
        }
        if (contact.lastName.isBlank()) {
            result = result.copy(lastNameError = "Last name can't be empty.")
        }

        if (!contact.email.isValidEmail()) {
            result = result.copy(emailError = "Invalid e-mail")
        }

        if (!contact.phone.isPhoneNumber()) {
            result = result.copy(phoneError = "Invalid phone number")
        }

        return result
    }

    data class ValidationResult(
        val firstNameError: String? = null,
        val lastNameError: String? = null,
        val phoneError: String? = null,
        val emailError: String? = null,
    ) {
        companion object

        fun hasError(): Boolean {
            val errors = listOfNotNull(
                firstNameError,
                lastNameError,
                emailError,
                phoneError,
            )
            return errors.isNotEmpty()
        }
    }
}