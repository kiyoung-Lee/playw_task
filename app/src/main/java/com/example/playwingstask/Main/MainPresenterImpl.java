package com.example.playwingstask.Main;

import static android.support.v4.util.Preconditions.checkNotNull;

import android.annotation.SuppressLint;

import com.example.playwingstask.Common.BaseAdapter;
import com.example.playwingstask.Main.Data.MainRepository;
import com.example.playwingstask.Main.Data.Model.DetailPage;

/**
 * Created by KiyoungLee on 2017-12-29.
 */

public class MainPresenterImpl implements MainContract.presenter {

    private MainRepository repository;
    private MainContract.activity activityView;
    private BaseAdapter.Model documentAdapterModel;
    private BaseAdapter.View documentAdapterView;

    public MainPresenterImpl(MainRepository repository) {
        this.repository = repository;
    }

    public void setActivityView(MainContract.activity activityView) {
        this.activityView = activityView;
    }

    public void setDocumentAdapterModel(BaseAdapter.Model documentAdapterModel) {
        this.documentAdapterModel = documentAdapterModel;
    }

    public void setDocumentAdapterView(BaseAdapter.View documentAdapterView) {
        this.documentAdapterView = documentAdapterView;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void start() {
        checkNotNull(documentAdapterModel, "DocumentAdapterModel Is Null");
        checkNotNull(documentAdapterView, "documentAdapterView Is Null");

        DetailPage detailPageData = repository.getOrderDetailPageData();

        if(detailPageData != null) {
            documentAdapterModel.replaceData(detailPageData.getDocuments());
            documentAdapterView.notifyAdapter();
            activityView.showTripDetailView(detailPageData);
        }else{
            activityView.showToast("페이지를 표시할 수 없습니다.");
        }
    }

    @Override
    public void setHolderHeight(float height) {
        activityView.setDocumentViewHeight(height);
    }
}
