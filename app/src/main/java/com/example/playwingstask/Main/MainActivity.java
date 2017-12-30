package com.example.playwingstask.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.playwingstask.Main.Data.MainRepositoryImpl;
import com.example.playwingstask.Main.Data.Model.DetailPage;
import com.example.playwingstask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.activity {

    @BindView(R.id.main_image)
    ImageView mainImageView;
    @BindView(R.id.main_info)
    TextView mainInfo;
    @BindView(R.id.main_title)
    TextView title;
    @BindView(R.id.main_arrival_date)
    TextView arrivalDate;
    @BindView(R.id.main_caution)
    TextView caution;
    @BindView(R.id.like_count)
    TextView likeCount;
    @BindView(R.id.reply_count)
    TextView replyCount;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.trip_price)
    TextView tripPrice;
    @BindView(R.id.document_list)
    RecyclerView documentList;

    private MainContract.presenter presenter;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MainAdapter();
        documentList.setLayoutManager(new LinearLayoutManager(this));
        documentList.setAdapter(adapter);

        presenter = new MainPresenterImpl(new MainRepositoryImpl());
        presenter.setActivityView(this);
        presenter.setDocumentAdapterModel(adapter);
        presenter.setDocumentAdapterView(adapter);
        presenter.start();
    }

    @Override
    public void showTripDetailView(DetailPage detailPageData) {
        title.setText(detailPageData.getTitle());
        mainInfo.setText(detailPageData.getInfo());
        Glide.with(this).load(detailPageData.getImageUrl()).into(mainImageView);
        likeCount.setText(String.valueOf(detailPageData.getLikecount()));
        replyCount.setText(String.valueOf(detailPageData.getCommentCount()));
        cityName.setText(detailPageData.getMainCityName());
        tripPrice.setText(String.valueOf(detailPageData.getMainPrice()));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
