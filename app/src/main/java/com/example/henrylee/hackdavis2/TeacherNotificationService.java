package com.example.henrylee.hackdavis2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class TeacherNotificationService extends Service {
    NotificationManager notificationManager;
    public TeacherNotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Intent notificationIntent = new Intent(this, TeacherCreateQuiz.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, 0);


        Notification notification = new Notification.Builder(this,"ID")
                .setContentTitle("Quiz is running")
                .setContentText("quez")
                .setContentIntent(pendingIntent)
                .setTicker("test")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setOngoing(true)
                .build();

        startForeground(2,notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service killed", Toast.LENGTH_SHORT).show();
        notificationManager.cancel(1);
    }
}
