package com.benjicode.benjlibrary.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.benjicode.benjlibrary.R;

public class webViewActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_acitivity);
        webView = findViewById(R.id.webwiewid);
        webView.loadUrl("https://github.com/benji348");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
        super.onBackPressed();
        }
    }
}