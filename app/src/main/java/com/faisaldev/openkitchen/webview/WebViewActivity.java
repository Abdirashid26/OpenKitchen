package com.faisaldev.openkitchen.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        // Get the URL from intent
        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            webView.loadUrl(url);
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

                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("❌ Invalid JSON format!");
                }
            }
        });


    }





}