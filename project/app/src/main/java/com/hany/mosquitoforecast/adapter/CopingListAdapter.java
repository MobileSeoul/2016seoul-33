package com.hany.mosquitoforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.vo.coping.CopingInfo;

import java.util.List;

/**
 * Created by hany  16. 8. 17..
 */
public class CopingListAdapter extends BaseAdapter {

    private Context mContext;
    private List<CopingInfo> mInfoList;


    public CopingListAdapter(Context context, List<CopingInfo> infoList) {
        mContext = context;
        mInfoList = infoList;
    }

    @Override
    public int getCount() {
        return mInfoList.size();
    }

    @Override
    public CopingInfo getItem(int position) {
        return mInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.view_coping_base_item, null);
            holder.mGroupTitle = (TextView) view.findViewById(R.id.group_title_text_view);
            holder.mTxtStepDefenseTop = (TextView) view.findViewById(R.id.txt_step_defense_top);
            holder.mTxtStepActiveTop = (TextView) view.findViewById(R.id.txt_step_active_top);
            holder.mTxtStepDefenseMiddle = (TextView) view.findViewById(R.id.txt_step_defense_middle);
            holder.mTxtStepActiveMiddle = (TextView) view.findViewById(R.id.txt_step_active_middle);
            holder.mTxtStepDefenseBottom = (TextView) view.findViewById(R.id.txt_step_defense_bottom);
            holder.mTxtStepActiveBottom = (TextView) view.findViewById(R.id.txt_step_active_bottom);
            holder.mTxtStepTop = (TextView) view.findViewById(R.id.txt_step_top);
            holder.mTxtStepMiddle = (TextView) view.findViewById(R.id.txt_step_middle);
            holder.mTxtStepBottom = (TextView) view.findViewById(R.id.txt_step_bottom);
            holder.mTxtStepDefaultInfo = (TextView)view.findViewById(R.id.txt_step_default_info);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String groupStep = new StringBuilder().append((position + 1)).append(" 단계").toString();
        holder.mGroupTitle.setText(groupStep);
        CopingInfo copingInfo = getItem(position);


        holder.mTxtStepDefaultInfo.setText(copingInfo.getStepDefaultInfo());

        String stepTop =  new StringBuilder().append("상").append("\n").append("(").append(copingInfo.getmStepTop()).append(")").toString();
        holder.mTxtStepTop.setText(stepTop);

        String stepMiddle= new StringBuilder().append("중").append("\n").append("(").append(copingInfo.getmStepMiddle()).append(")").toString();
        holder.mTxtStepMiddle.setText(stepMiddle);

        String stepBottom = new StringBuilder().append("하").append("\n").append("(").append(copingInfo.getmStepBottom()).append(")").toString();
        holder.mTxtStepBottom.setText(stepBottom);

        holder.mTxtStepDefenseTop.setText(copingInfo.getStepDefenceTop());
        holder.mTxtStepActiveTop.setText(copingInfo.getStepActiveTop());
        holder.mTxtStepDefenseMiddle.setText(copingInfo.getStepDefenceMiddle());
        holder.mTxtStepActiveMiddle.setText(copingInfo.getStepActiveMiddle());


        holder.mTxtStepActiveBottom.setText(copingInfo.getStepActiveBottom());
        holder.mTxtStepDefenseBottom.setText(copingInfo.getStepDefenceBottom());

        return view;
    }

    private class ViewHolder {
        public TextView mGroupTitle;
        public TextView mTxtStepTop;
        public TextView mTxtStepDefenseTop;
        public TextView mTxtStepActiveTop;
        public TextView mTxtStepMiddle;
        public TextView mTxtStepDefenseMiddle;
        public TextView mTxtStepActiveMiddle;
        public TextView mTxtStepBottom;
        public TextView mTxtStepDefenseBottom;
        public TextView mTxtStepActiveBottom;
        public TextView mTxtStepDefaultInfo;


    }
}
