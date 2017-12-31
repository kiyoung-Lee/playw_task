package com.example.playwingstask.Main;

import static com.example.playwingstask.Util.Layout.getFormattedPrice;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.playwingstask.Main.Data.MainRepositoryImpl;
import com.example.playwingstask.Main.Data.Model.DetailPage;
import com.example.playwingstask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.activity {

    @BindView(R.id.company_name)
    TextView companyName;
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
    private LinearLayoutManager manager;
    private int documentViewHeight;
    private int count = 0;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MainAdapter();
        presenter = new MainPresenterImpl(new MainRepositoryImpl());
        presenter.setActivityView(this);
        presenter.setDocumentAdapterModel(adapter);
        presenter.setDocumentAdapterView(adapter);

        manager = new LinearLayoutManager(this);
        documentList.setLayoutManager(manager);
        documentList.setAdapter(adapter);
        adapter.setPresenter(presenter);
        documentList.setNestedScrollingEnabled(false);

        presenter.start();

    }

    @Override
    public void showTripDetailView(DetailPage detailPageData) {
        companyName.setText(detailPageData.getCompany().getName());
        title.setText(detailPageData.getTitle());
        mainInfo.setText(detailPageData.getInfo());
        Glide.with(this).load(detailPageData.getImageUrl()).into(mainImageView);
        likeCount.setText(String.valueOf(detailPageData.getLikecount()));
        replyCount.setText(String.valueOf(detailPageData.getCommentCount()));
        cityName.setText(detailPageData.getMainCityName());
        tripPrice.setText(String.valueOf(getFormattedPrice(detailPageData.getMainPrice())));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDocumentViewHeight(float height) {
        documentViewHeight += height;
        count++;

        if(count == adapter.getItemCount()){
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            documentList.setLayoutParams(new LinearLayout.LayoutParams(documentList.getLayoutParams().MATCH_PARENT, documentViewHeight));
                        }
                    });
                }
            };
            mHandler = new Handler();
            mHandler.postDelayed(mRunnable, 400);
        }
    }
}
