package com.ritixplore;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText urlInput;
    private Button searchButton;
    private TextView quoteText;
    private String[] quotes = {
        "Stay focused and never give up.",
        "Your only limit is your mind.",
        "Success is the sum of small efforts.",
        "Discipline is the bridge between goals and accomplishment.",
        "Stay hungry, stay foolish."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        urlInput = findViewById(R.id.urlInput);
        searchButton = findViewById(R.id.searchButton);
        quoteText = findViewById(R.id.quoteText);

        Random random = new Random();
        quoteText.setText(quotes[random.nextInt(quotes.length)]);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(false);
        webSettings.setBlockNetworkImage(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        searchButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://www.google.com/search?q=" + url;
            }
            webView.loadUrl(url);
        });
    }
}
