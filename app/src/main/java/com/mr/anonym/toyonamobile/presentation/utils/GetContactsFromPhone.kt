package com.mr.anonym.toyonamobile.presentation.utils

import android.content.Context
import android.provider.ContactsContract
import com.mr.anonym.domain.model.FriendsModel

fun getContacts(context: Context): List<FriendsModel>{

    val contacts = mutableListOf<FriendsModel>()
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
            contacts.add(FriendsModel(name = name, phone = number))
        }
    }
    return contacts
}