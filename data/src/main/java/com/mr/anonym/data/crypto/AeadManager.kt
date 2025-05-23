package com.mr.anonym.data.crypto

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import kotlin.jvm.java

object AeadManager {
    fun getAead(context: Context): Aead{
        val keysetManager = AndroidKeysetManager.Builder()
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM )
            .withSharedPref(context,"my_keyset","my_prefs_file")
            .withMasterKeyUri( "android-keystore://my_master_key" )
            .build()
            .keysetHandle
        return keysetManager.getPrimitive(Aead::class.java)
    }
}