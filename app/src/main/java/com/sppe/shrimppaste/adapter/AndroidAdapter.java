package com.sppe.shrimppaste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.adapter.holder.AndroidHolder;
import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.util.TextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/12.
 */

public class AndroidAdapter extends RecyclerView.Adapter<AndroidHolder> {


    private ArrayList<GankEntry> gankEntrieList;
    private LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public AndroidAdapter(Context context, ArrayList<GankEntry> gankEntrieList) {
        this.layoutInflater = LayoutInflater.from(context);

        if (gankEntrieList == null) {
            this.gankEntrieList = new ArrayList<>();
        } else {
            this.gankEntrieList = gankEntrieList;
        }
    }


    @Override
    public AndroidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AndroidHolder(layoutInflater.inflate(R.layout.item_android, parent, false));
    }

    @Override
    public void onBindViewHolder(AndroidHolder holder, int position) {
        if (position >= gankEntrieList.size()) {
            return;
        }
        GankEntry gankEntry = gankEntrieList.get(position);
        holder.tvTitle.setText(TextUtil.dealAtricleTitle(gankEntry.getDesc()));
        holder.tvType.setText(gankEntry.getType());

        String time = gankEntry.getPublishedAt();
        String formatTime = TextUtil.dealTime(time);
        Log.e("========",time+"||"+formatTime);
        holder.tvDate.setText(formatTime);

        if (gankEntry.getWho() == null) {
            holder.tvAuthor.setText("佚名");
        } else {
            holder.tvAuthor.setText(gankEntry.getWho());
        }
    }

    @Override
    public int getItemCount() {
        return gankEntrieList.size();
    }

    public void setDateList(List<GankEntry> gankEntryList) {
        this.gankEntrieList = (ArrayList<GankEntry>) gankEntryList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
