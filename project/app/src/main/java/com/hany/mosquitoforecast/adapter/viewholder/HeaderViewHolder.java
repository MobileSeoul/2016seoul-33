package com.hany.mosquitoforecast.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by HanyLuv on 2016-08-23.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {
    public HeaderViewHolder(Context context, int footerRes) {
        this(LayoutInflater.from(context).inflate(footerRes, null));
    }
    public HeaderViewHolder(View itemView) {
        super(itemView);
    }
}
