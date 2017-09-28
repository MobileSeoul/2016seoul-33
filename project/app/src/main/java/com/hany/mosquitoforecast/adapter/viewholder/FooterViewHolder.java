package com.hany.mosquitoforecast.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by 160229i-b on 16. 8. 22..
 */
public class FooterViewHolder extends RecyclerView.ViewHolder {
    public FooterViewHolder (Context context,int footerRes){
        this(LayoutInflater.from(context).inflate(footerRes,null));
    }

    public FooterViewHolder(View itemView) {
        super(itemView);
    }
}
