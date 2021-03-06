package com.example.nayan.swiperefresh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nayan.swiperefresh.R;
import com.example.nayan.swiperefresh.model.MList;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/9/2017.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<MList> list;
    private MList mList;
    private Context context;

    public AdapterList(Context context) {
        this.context = context;
        mList = new MList();
        list = new ArrayList();
        inflater = LayoutInflater.from(context);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(ArrayList<MList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.image_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mList = list.get(position);
        holder.title.setText(mList.getTitle());
        holder.serial.setText(mList.getRank() + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView serial;
        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            serial = (TextView) itemView.findViewById(R.id.serial);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
