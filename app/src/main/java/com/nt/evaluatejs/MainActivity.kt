package com.nt.evaluatejs

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent

class MainActivity : AppCompatActivity() {

    val url = "https://www.digitalocean.com/";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val browser = findViewById<Button>(R.id.browser)
        val customTab = findViewById<Button>(R.id.chrome)

        browser.setOnClickListener() {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
        customTab.setOnClickListener() {
            val customTabBuilder = CustomTabsIntent.Builder()
            customTabBuilder.setToolbarColor(Color.parseColor("#008000"))

            openCustomTab(this@MainActivity, customTabBuilder.build(), Uri.parse(url))
        }
    }

    public fun openCustomTab(activity: Activity, customTabsIntent: CustomTabsIntent, uri: Uri) {
        val packageName = "com.android.chrome"
        if(packageName != null) {
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(activity, uri)
        }
        else {
            activity.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}