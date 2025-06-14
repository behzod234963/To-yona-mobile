package com.mr.anonym.toyonamobile.presentation.managers

import android.content.Context
import android.provider.ContactsContract
import com.mr.anonym.domain.model.UserModelItem

fun getContacts(context: Context): List<UserModelItem>{

    val contacts = mutableListOf<UserModelItem>()
    val contentResolver = context.contentResolver
    val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    val cursor = contentResolver.query(
        uri,
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        ),
        null,
        null,
        null,
        null,
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        while (it.moveToNext()){
            val name = it.getString(nameIndex)
            val number = it.getString(numberIndex)
            contacts.add(UserModelItem(username = name, phonenumber = number))
        }
    }
    return contacts
}