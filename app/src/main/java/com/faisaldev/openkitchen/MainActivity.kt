package com.faisaldev.openkitchen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.faisaldev.openkitchen.dtos.PaymentRequest
import com.faisaldev.openkitchen.webview.WebViewActivity
import com.faisaldev.pg3dssdk.Pg3DSPaymentSDK
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pg3DSPaymentSDK  : Pg3DSPaymentSDK
    val REQUEST_CODE_WEBVIEW: Int = 1


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val paymentRequest = PaymentRequest()

        val payButton = findViewById<MaterialButton>(R.id.payBtn)

        payButton.setOnClickListener {
            val credentials = "admin:password";
            val basicAuth = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP);
                pg3DSPaymentSDK.pay(paymentRequest.toJson()).observe(this){redirectUrl ->
                    println("RESPONSE:."+redirectUrl)
                // open the webView
                pg3DSPaymentSDK.startWebSocket("ws://10.20.2.91:30681/ws/card3ds?uniqueId=" + paymentRequest.transactionid, paymentRequest.transactionid,basicAuth);

                // nvigate to wweebView activyt and then set the redirectUrl to the intent
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("webviewform", redirectUrl)
                startActivityForResult(intent, REQUEST_CODE_WEBVIEW);
            }
        }




    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        pg3DSPaymentSDK.reset()
        if (requestCode == REQUEST_CODE_WEBVIEW && resultCode == RESULT_OK) {
            if (data != null) {
                val transactionId = data.getStringExtra("transactionId")
                val status = data.getStringExtra("status")
                val message = data.getStringExtra("message")

                println("Received Transaction ID: $transactionId")
                println("Received Status: $status")
                println("Received Message: $message")

                Toast.makeText(this, "Transaction: $message", Toast.LENGTH_LONG).show()


                val textView = findViewById<TextView>(R.id.message)
                textView.setText("STATUS : "+message)

            }
        }
    }



}