package com.example.playwingstask.Main.Data;

import com.example.playwingstask.Main.Data.Model.DetailPage;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by KiyoungLee on 2017-12-29.
 */

public class MainRepositoryImpl implements MainRepository {

    @Override
    public DetailPage getOrderDetailPageData() {

        String detailPageData = MainMockApi.getDetailPageJosn;

        DetailPage detailPageInfo = new Gson().fromJson(detailPageData, DetailPage.class);
        return detailPageInfo;
    }
}
