package com.example.contactslistkmp.contacts.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.contactslistkmp.contacts.domain.entities.Contact
import com.example.contactslistkmp.contacts.presentation.ContactListEvent
import com.example.contactslistkmp.contacts.presentation.ContactsListState
import com.example.contactslistkmp.core.presentation.components.BottomSheetView
import com.example.contactslistkmp.core.utils.PhoneVisualTransformation


@Composable
fun AddContactSheet(
    state: ContactsListState,
    newContact: Contact?,
    isOpen: Boolean,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomSheetView(
        visible = isOpen,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(60.dp))
                if (newContact?.photoBytes == null) {
                    EmptyContactPhoto {
                        onEvent(it)
                    }
                } else {
                    ContactPhoto(
                        contact = newContact,
                        modifier = Modifier.size(150.dp)
                            .clickable {
                                onEvent(ContactListEvent.OnAddPhotoClicked)
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                FormFields(newContact, state, onEvent)
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onEvent(ContactListEvent.SaveContact) }
                ) {
                    Text("Save")
                }
            }
            IconButton(
                onClick = { onEvent(ContactListEvent.DismissContact) }
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                )
            }
        }
    }
}

@Composable
fun EmptyContactPhoto(
    modifier: Modifier = Modifier,
    onEvent: (ContactListEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(40))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                onEvent(ContactListEvent.OnAddPhotoClicked)
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = RoundedCornerShape(40)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add photo",
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun FormFields(
    newContact: Contact?,
    state: ContactsListState,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    ContactTextField(
        value = newContact?.firstName ?: "",
        placeholder = "First name",
        error = state.firstNameError,
        onValueChanged = {
            onEvent(ContactListEvent.OnFirstNameChanged(it))
        },
        Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
        ),
    )
    Spacer(Modifier.height(16.dp))
    ContactTextField(
        value = newContact?.lastName ?: "",
        placeholder = "Last name",
        error = state.lastNameError,
        onValueChanged = {
            onEvent(ContactListEvent.OnLastNameChanged(it))
        },
        Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
        ),
    )
    Spacer(Modifier.height(16.dp))
    ContactTextField(
        value = newContact?.email ?: "",
        placeholder = "E-mail",
        error = state.emailError,
        onValueChanged = {
            onEvent(ContactListEvent.OnEmailChanged(it))
        },
        Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
        ),
    )
    Spacer(Modifier.height(16.dp))
    ContactTextField(
        value = newContact?.phone ?: "",
        placeholder = "Phone number",
        error = state.phoneError,
        onValueChanged = {
            if (it.length <= 11)
                onEvent(ContactListEvent.OnPhoneChanged(it))
        },
        Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Phone,
        ),
        visualTransformation = PhoneVisualTransformation("(00) 0 0000-0000", '0')
    )
}

@Composable
fun ContactTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier) {
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            label = {
                Text(placeholder)
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,

            )
        if (error != null)
            Text(
                error,
                color = MaterialTheme.colorScheme.error
            )
    }
}