package com.example.playwingstask.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playwingstask.Common.BaseAdapter;
import com.example.playwingstask.Common.BasePresenter;
import com.example.playwingstask.Common.BaseRecyclerViewHolder;
import com.example.playwingstask.Main.Data.Model.Document;
import com.example.playwingstask.R;

import java.util.List;

/**
 * Created by KiyoungLee on 2017-12-30.
 */

public class MainAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder>
            implements BaseAdapter.Model<List<Document>>, BaseAdapter.View{

    private MainContract.presenter presenter;
    private List<Document> documentList;

    @Override
    public int getItemCount() {
        if(documentList != null)
            return documentList.size();
        return 0;
    }

    @Override
    public void replaceData(List<Document> data) {
        this.documentList = data;
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (MainContract.presenter) presenter;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_documnet_holder, parent, false);
        BaseRecyclerViewHolder holder = new MainDocumentViewHolder(parent.getContext(), view);
        holder.setPresenter(presenter);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.bind(documentList.get(position));
    }

}
