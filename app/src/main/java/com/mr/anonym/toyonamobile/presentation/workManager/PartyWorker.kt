package com.mr.anonym.toyonamobile.presentation.workManager

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.google.crypto.tink.shaded.protobuf.LazyStringArrayList.emptyList
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.NOTIFICATION_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull

@HiltWorker
class PartyWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParameters: WorkerParameters,
//    private val notificationController: NotificationController,
    private val notificationManager: NotificationManager,
    private val sharedPrefs: SharedPreferencesInstance,
    private val localUseCases: LocalUseCases,
    private val remoteUseCases: RemoteUseCases,
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo()
    }
    override suspend fun doWork(): Result {

        setForeground(createForegroundInfo())
        val id = sharedPrefs.getID()
        val remoteParties = mutableListOf<PartysItem>()
        delay(1000L)
        val localParties = localUseCases.getAllLocalParty.execute().firstOrNull() ?: emptyList()


        delay(1000L)
        val user = remoteUseCases.getUserUseCase.execute(id).firstOrNull() ?: UserModelItem()
        remoteParties.addAll(user.partylist.filter { it.status })
        val friendList = remoteUseCases.getAllMyFriendUseCase.execute().firstOrNull()
            ?: emptyList<AddedByMeItem>()
        friendList.forEach { friend ->
            val friendItem = remoteUseCases.getUserUseCase.execute(friend.friendId).firstOrNull()
                ?: UserModelItem()
            remoteParties.addAll(friendItem.partylist.filter { it.status })
        }
        remoteParties.forEach { remoteParty ->
            val model = LocalPartyModel(
                id = remoteParty.id,
                userId = remoteParty.userId,
                userName = remoteParty.userName,
                name = remoteParty.name,
                type = remoteParty.type,
                address = remoteParty.address,
                cardNumber = remoteParty.cardNumber,
                startTime = remoteParty.startTime,
                endTime = remoteParty.endTime,
                status = remoteParty.status,
                createdAt = remoteParty.createdAt
            )
            if (
                localParties.isNotEmpty()
                &&
                !localParties.contains(model)
            ) {
                val partyType = when (model.type) {
                    "1" -> appContext.getString(R.string.wedding)
                    "2" -> appContext.getString(R.string.sunnat_wedding)
                    "3" -> appContext.getString(R.string.birthday)
                    "4" -> appContext.getString(R.string.other)
                    else -> appContext.getString(R.string.other)
                }
//                val intent = Intent(appContext, NotificationReceiver::class.java).let {
//                    it.putExtra("title", partyType)
//                    it.putExtra("contentText", model.userName)
//                }
//                val flag = PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
//                val pendingIntent = PendingIntent.getBroadcast(
//                    appContext,
//                    NOTIFICATION_BROADCAST_REQUEST_CODE,
//                    intent,
//                    flag
//                )
//                pendingIntent.send()
                notificationManager.notify(
                    model.id,
                    createNotification(
                        title = model.name,
                        contentText = model.userName
                    )
                )
                localUseCases.insertLocalParty.execute(model)
            }
        }
        delay(1000L)
        return Result.success()
    }

    fun createForegroundInfo(): ForegroundInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE){
            ForegroundInfo(
                NOTIFICATION_ID,
                createNotification(appContext.getString(R.string.waiting_notification),
                    appContext.getString(
                        R.string.app_works_in_background
                    )),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        }else{
            ForegroundInfo(
                NOTIFICATION_ID,
                createNotification(appContext.getString(R.string.waiting_notification),appContext.getString(
                    R.string.app_works_in_background
                ))
            )
        }
    }
    fun createNotification(title: String,contentText: String): Notification{

        val notification = NotificationCompat.Builder(appContext, "notification channel id")
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .build()
        return notification
    }
}