package com.mr.anonym.toyonamobile.presentation.utils

import android.content.Context
import android.os.Build
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import com.mr.anonym.domain.model.FriendsModel

@RequiresApi(Build.VERSION_CODES.O)
fun getContacts(context: Context): List<FriendsModel>{
    val contacts = mutableListOf<FriendsModel>()
    val contentResolver = context.contentResolver
    val cursor = contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER
        ),
        null,
        null,
        "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} ASC"
    )
    cursor?.use {
        val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        while (it.moveToNext()){
            val name = it.getString(nameIndex)
            contacts.add(
                FriendsModel(
                    name = name,
                )
            )
        }
    }
    return contacts
}