package com.moin.newsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.moin.newsapp.R;

public class NewsFragment extends Fragment {
    private View view;
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);

        initUI();
        setUpWebView();

        return view;
    }

    private void initUI() {
        webView = view.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
    }

    private void setUpWebView() {
        webView.loadUrl(NewsFragmentArgs.fromBundle(getArguments()).getUrl());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}