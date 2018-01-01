package com.example.playwingstask.Main;

import com.example.playwingstask.Common.BaseAdapter;
import com.example.playwingstask.Common.BasePresenter;
import com.example.playwingstask.Main.Data.Model.DetailPage;

/**
 * Created by KiyoungLee on 2017-12-29.
 */

public interface MainContract {

    interface activity {

        void showTripDetailView(DetailPage detailPageData);

        void showToast(String msg);

        void setDocumentViewHeight(float height);
    }

    interface presenter extends BasePresenter {

        void setActivityView(MainContract.activity activityView);

        void setDocumentAdapterModel(BaseAdapter.Model documentAdapterModel);

        void setDocumentAdapterView(BaseAdapter.View documentAdapterView);

        void setHolderHeight(float height);
    }
}
