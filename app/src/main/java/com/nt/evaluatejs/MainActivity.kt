package com.nt.evaluatejs

import android.content.Context
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var mEditText : EditText
    lateinit var mButtonSend : Button
    private val localhost = "http://10.0.2.2:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEditText = findViewById(R.id.editInput)
        val nextWebView: WebView = findViewById(R.id.nextjsWeb)

        nextWebView.settings.javaScriptEnabled = true
        nextWebView.addJavascriptInterface(JSBridge(this,mEditText),"JSBridge")
        nextWebView.loadUrl(localhost)

        mButtonSend = findViewById(R.id.sendData)
        mButtonSend.setOnClickListener {
            sendDataToWebView(nextWebView, this)
        }
    }

    /**
     * Receive message from webview and pass on to native.
     */
    class JSBridge(val context: Context, val editTextInput: EditText){
        @JavascriptInterface
        fun showMessageInNative(message:String){
            Toast.makeText(context,message, Toast.LENGTH_LONG).show()
            editTextInput.setText(message)
        }
    }

    /**
     * Send data to webview through function updateFromNative.
     */
    private fun sendDataToWebView(webViewSample: WebView, context: Context){
        webViewSample.evaluateJavascript(
            "javascript: " +"update(\"" + mEditText.text + "\")",
            null)
    }
}
