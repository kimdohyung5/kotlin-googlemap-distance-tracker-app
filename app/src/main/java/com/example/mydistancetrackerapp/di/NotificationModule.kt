package com.example.mydistancetrackerapp.di

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.mydistancetrackerapp.ui.MainActivity
import com.example.mydistancetrackerapp.R
//import com.example.mydistancetrackerapp.util.Constants.ACTION_NAVIGATE_TO_MAPS_FRAGMENT
import com.example.mydistancetrackerapp.util.Constants.NOTIFICATION_CHANNEL_ID
import com.example.mydistancetrackerapp.util.Constants.PENDING_INTENT_REQUEST_CODE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped


@Module
@InstallIn(ServiceComponent::class)
object NotificationModule {

    @ServiceScoped
    @Provides
    fun providePendingIntent(@ApplicationContext context: Context) : PendingIntent {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return PendingIntent.getActivity(context, PENDING_INTENT_REQUEST_CODE,
                Intent(context, MainActivity::class.java) , PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }
        return PendingIntent.getActivity(context, PENDING_INTENT_REQUEST_CODE,
            Intent(context, MainActivity::class.java) , PendingIntent.FLAG_UPDATE_CURRENT)
    }


    @ServiceScoped
    @Provides
    fun provideNotificationBuilder(@ApplicationContext context: Context, pendingIntent: PendingIntent) : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_run_24)
            .setContentIntent(pendingIntent)
    }

    @ServiceScoped
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context) : NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

}