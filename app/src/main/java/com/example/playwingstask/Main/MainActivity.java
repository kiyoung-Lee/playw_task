package com.example.playwingstask.Main;

import static com.example.playwingstask.Util.Layout.getFormattedPrice;

import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.playwingstask.Main.Data.MainRepositoryImpl;
import com.example.playwingstask.Main.Data.Model.DetailPage;
import com.example.playwingstask.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.activity {

    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.main_scrollview)
    ScrollView mainScrollView;
    @BindView(R.id.main_image)
    ImageView mainImageView;
    @BindView(R.id.city_names)
    TextView cityNames;
    @BindView(R.id.main_title)
    TextView title;
    @BindView(R.id.main_start_date)
    TextView startDate;
    @BindView(R.id.main_end_date)
    TextView endDate;
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
    @BindView(R.id.end_deal)
    TextView endDeal;
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
        documentList.setFocusable(false);
        mainScrollView.requestFocus();
    }

    @Override
    public void showTripDetailView(DetailPage detailPageData) {
        if(detailPageData.getCompany() != null)
            companyName.setText(detailPageData.getCompany().getName());

        title.setText(detailPageData.getTitle());
        cityNames.setText(detailPageData.getCityNames());
        Glide.with(this).load(detailPageData.getImageUrl()).into(mainImageView);
        setDateTime(detailPageData);
        caution.setText(detailPageData.getInfo());

        likeCount.setText(String.valueOf(detailPageData.getLikecount()));
        replyCount.setText(String.valueOf(detailPageData.getCommentCount()));
        cityName.setText(detailPageData.getMainCityName());
        tripPrice.setText(String.valueOf(getFormattedPrice(detailPageData.getMainPrice())));
        endDeal.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void setDateTime(DetailPage detailPageData){
        try {
            SimpleDateFormat startOriginFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date originDate = startOriginFormat.parse(detailPageData.getStartDate());

            SimpleDateFormat startParseFormat = new SimpleDateFormat("yyyy-MM-dd");
            String parseStartDate = startParseFormat.format(originDate);

            startDate.setText(parseStartDate);

            SimpleDateFormat endOriginFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date originEndDate = endOriginFormat.parse(detailPageData.getEndDate());

            SimpleDateFormat endParseFormat = new SimpleDateFormat("yyyy-MM-dd");
            String parseEndDate = endParseFormat.format(originEndDate);
            endDate.setText(" ~ " + parseEndDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
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
