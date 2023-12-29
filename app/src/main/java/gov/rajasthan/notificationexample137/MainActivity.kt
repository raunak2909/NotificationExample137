package gov.rajasthan.notificationexample137

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import gov.rajasthan.notificationexample137.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var notificationManager: NotificationManager
    lateinit var mNotification: Notification
    lateinit var binding: ActivityMainBinding

    companion object {
        val CHANNED_TEST_ID = "Test"
        val CHANNED_TEST_NAME = "Test Channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //1

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationTestChannel = NotificationChannel(
                CHANNED_TEST_ID,
                CHANNED_TEST_NAME,
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationTestChannel)
        }

        createNotification()

       binding.txtAction.setOnClickListener {
           notificationManager.notify(100, mNotification)
       }
    }

    fun createNotification() {

        /* val drawable = ResourcesCompat.getDrawable(resources, R.drawable.logo, null)
         val bitmapDrawable = drawable as BitmapDrawable*/

        val imgBitmap =
            (ResourcesCompat
                .getDrawable(resources, R.drawable.logo, null) as BitmapDrawable)
                .bitmap

        //pendingIntent
        val iHome = Intent(this@MainActivity, MainActivity::class.java)
        iHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val pi = PendingIntent.getActivity(this, 100, iHome, PendingIntent.FLAG_IMMUTABLE)

        val bigPictureStyle = Notification
            .BigPictureStyle()
            .bigPicture(imgBitmap)
            .bigLargeIcon(imgBitmap)
            .setBigContentTitle("Image by Raman")
            .setSummaryText("Full Picture")

        val inboxStyle = Notification
            .InboxStyle()
            .setBigContentTitle("Full message")
            .setSummaryText("read all")
            .addLine("nsbvjdbvds1")
            .addLine("vfsvffsdvsdfv2")
            .addLine("nsbvjdbvds3")
            .addLine("vfsvffsdvsdfv4")
            .addLine("nsbvjdbvds5")
            .addLine("vfsvffsdvsdfv6")
            .addLine("nsbvjdbvds7")
            .addLine("vfsvffsdvsdfv8")
            .addLine("nsbvjdbvds9")
            .addLine("vfsvffsdvsdfv10")
            .addLine("nsbvjdbvds11")
            .addLine("vfsvffsdvsdfv12")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotification = Notification
                .Builder(this, CHANNED_TEST_ID)
                .setContentTitle("New Message")
                .setContentText("from Raman")
                .setStyle(inboxStyle)
                /*.setAutoCancel(false)
                .setOngoing(true)*/
                .setLargeIcon(imgBitmap)
                .setContentIntent(pi)
                .setChannelId(CHANNED_TEST_ID)
                .setSmallIcon(R.drawable.logo_outline)
                .build()
        } else {
            mNotification = Notification.Builder(this)
                .setContentTitle("New Message")
                .setContentText("from Raman")
                .setLargeIcon(imgBitmap)
                .setStyle(inboxStyle)
                /*.setAutoCancel(false)
                .setOngoing(true)*/
                .setContentIntent(pi)
                .setSmallIcon(R.drawable.logo_outline)
                .build()
        }
    }
}