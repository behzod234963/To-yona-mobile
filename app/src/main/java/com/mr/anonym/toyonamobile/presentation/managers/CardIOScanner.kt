package com.mr.anonym.toyonamobile.presentation.managers

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.mr.anonym.domain.model.CardModel
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard

@Composable
fun cardScannerIO(
    onSuccess: (CardModel) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("UtilsLogging", "AddCardScreen: $result result is worked!")
//            if (result.resultCode == Activity.RESULT_OK){
            val scanResult =
                result.data?.getParcelableExtra<CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)
            if (scanResult != null) {
                onSuccess(
                    CardModel(
                        number = scanResult.formattedCardNumber,
                        date = "${scanResult.expiryMonth} ${scanResult.expiryYear}",
                    )
                )
            }
//            }
        }
    return launcher
}

fun startScanning(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val intent = Intent(context, CardIOActivity::class.java).apply {
        putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true)
        putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false)
        putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
        putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)
        putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false)
        putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, false)
        putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, true)
        putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, true)
        putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
        putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, false)
    }
    launcher.launch(intent)
}