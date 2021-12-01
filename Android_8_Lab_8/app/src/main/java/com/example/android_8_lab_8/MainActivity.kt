package com.example.android_8_lab_8

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import androidx.core.app.NotificationCompat
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.graphics.Color
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //The id of the notification channel
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = "my_channel_02"

        // The visible name of the channel
        val name = getString(R.string.abc_action_bar_home_description)

        // The visible description of the channel
        val description = getString(R.string.abc_action_bar_home_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)

        // Configures the notification channel
        mChannel.description = description
        mChannel.enableLights(true)

        // Sets the light color
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        mNotificationManager.createNotificationChannel(mChannel)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mywebview.setWebViewClient(WebViewClient())

        when(item?.itemId){

            R.id.item1 -> {mywebview.loadUrl("https://www.androidatc.com")


                // ID of the channel
                val CHANNEL_ID = "my_channel_02"

                // Adds the notification objects
                val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC New Course !!")

                // Creates an intent
                val resultIntent = Intent(this, ResultActivity::class.java)
                // Stack builder
                val stackBuilder = TaskStackBuilder.create(this)

                // Adds the intent that starts the activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent)
                val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
                mBuilder.setContentIntent(resultPendingIntent)
                val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNotificationManager.notify(2, mBuilder.build())


            return super.onOptionsItemSelected(item)}

            R.id.item2 -> {mywebview.loadUrl("https://www.pearsonvue.com/androidatc")
                return super.onOptionsItemSelected(item)}

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun gotoweb(view: View) {

        // Enables JavaScript, downloads images and enables a scrollbar
        mywebview.settings.javaScriptEnabled =true
        mywebview.settings.loadsImagesAutomatically =true
        mywebview.scrollBarStyle= View.SCROLLBARS_INSIDE_OVERLAY

        // The URL will be taken from url_address
        // The textview will work as an address bar
        val url = url_address.text.toString()

        // To avoid webview to launch default browser
        mywebview.setWebViewClient(WebViewClient())

        // Shows the url
        mywebview.loadUrl(url)
    }

}