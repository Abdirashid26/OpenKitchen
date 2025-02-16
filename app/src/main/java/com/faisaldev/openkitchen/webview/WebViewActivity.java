package com.faisaldev.openkitchen.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.faisaldev.openkitchen.R;
import com.faisaldev.openkitchen.utils.Global;
import com.faisaldev.pg3dssdk.Pg3DSPaymentSDK;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WebViewActivity extends AppCompatActivity {
    private WebView webView;

    @Inject
    Pg3DSPaymentSDK pg3DSPaymentSDK;
    
    @SuppressLint({"SetJavaScriptEnabled", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); // Allow mixed content

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println("WebView Page Loaded: " + url); // Debugging logs
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        String webviewform = getIntent().getStringExtra("webviewform");
        if (webviewform != null) {
            webView.loadDataWithBaseURL(null, webviewform, "text/html", "UTF-8", null);
        }






        pg3DSPaymentSDK.getResultLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                System.out.println("Transaction Result: " + result);
                try {
                    JSONObject jsonResult = new JSONObject(result);

                    String transactionId = jsonResult.optString("data", "N/A");
                    String status = jsonResult.optString("status", "Unknown");
                    String statusDescription = jsonResult.optString("message", "No description");

                    System.out.println("Transaction ID: " + transactionId);
                    System.out.println("Status: " + status);

                    runOnUiThread(() ->
                            Toast.makeText(WebViewActivity.this, statusDescription, Toast.LENGTH_LONG).show()
                    );

                    // Pass data back to MainActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("transactionId", transactionId);
                    resultIntent.putExtra("status", status);
                    resultIntent.putExtra("message", statusDescription);
                    setResult(RESULT_OK, resultIntent);

                    pg3DSPaymentSDK.closeSocketConnection();
                    // Finish WebViewActivity and return to MainActivity


                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("❌ Invalid JSON format!");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Closes the activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("🔥 Activity is closing!");
        pg3DSPaymentSDK.closeSocketConnection();
    }







}