package com.example.playwingstask.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.playwingstask.Common.BaseRecyclerViewHolder;
import com.example.playwingstask.Main.Data.Model.Document;
import com.example.playwingstask.R;

import butterknife.BindView;

/**
 * Created by KiyoungLee on 2017-12-30.
 */

public class MainDocumentViewHolder extends BaseRecyclerViewHolder<Document, MainContract.presenter>{

    @BindView(R.id.document_title)
    TextView documentTitle;
    @BindView(R.id.document_webview)
    WebView documentWebView;

    View view;

    public MainDocumentViewHolder(Context context, View itemView) {
        super(context, itemView);
        view = itemView;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void bind(Document data) {
        super.bind(data);

        documentTitle.setText(data.getTitle());
        WebSettings settings = documentWebView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        documentWebView.loadData(data.getContent(), "text/html; charset=utf-8", "utf-8");
        documentWebView.getSettings().setJavaScriptEnabled(true);
        documentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                documentWebView.loadUrl("javascript:MyApp.resize()");
                super.onPageFinished(view, url);

            }
        });

        documentWebView.addJavascriptInterface(this, "MyApp");
    }

    @JavascriptInterface
    public void resize() {
        presenter.setHolderHeight(view.getHeight() * (context.getResources().getDisplayMetrics().density * 6));
    }
}
