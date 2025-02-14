package com.faisaldev.openkitchen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.faisaldev.openkitchen.dtos.PaymentRequest
import com.faisaldev.openkitchen.webview.WebViewActivity
import com.faisaldev.pg3dssdk.Pg3DSPaymentSDK
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pg3DSPaymentSDK  : Pg3DSPaymentSDK

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
            pg3DSPaymentSDK.pay(paymentRequest.toJson()).observe(this){redirectUrl ->
                // open the webView
                pg3DSPaymentSDK.startWebSocket("ws://10.20.2.91:30681/ws/card3ds?uniqueId=" + paymentRequest.transactionid, paymentRequest.transactionid);

                // nvigate to wweebView activyt and then set the redirectUrl to the intent
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("URL", redirectUrl)
                startActivity(intent)

            }
        }

//        pg3DSPaymentSDK.resultLiveData.observe(this, Observer { result ->
//            println("Transaction Result: $result")
//            try {
//                val jsonResult = JSONObject(result)
//
//                val transactionId = jsonResult.optString("transactionID", "N/A")
//                val status = jsonResult.optString("status", "Unknown")
//                val statusDescription = jsonResult.optString("statusDescription", "No description")
//
//                println("Transaction ID: $transactionId")
//                println("Status: $status")
//
//                runOnUiThread {
//                    Toast.makeText(this@MainActivity, statusDescription, Toast.LENGTH_LONG).show()
//                }
//            } catch (e: JSONException) {
//                e.printStackTrace()
//                println("❌ Invalid JSON format!")
//            }
//        })



    }



}