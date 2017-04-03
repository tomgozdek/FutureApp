package com.tomekgozdek.futureapp.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomekgozdek.futureapp.R;
import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tomasz Gozdek on 03.04.2017.
 */

class FutureItemAdapter extends RecyclerView.Adapter<FutureItemAdapter.ViewHolder> {
    /** Number of caracters used for displaying item description */
    private static final int SHORT_DESCRIPTION_LENGTH = 400;
    private View.OnClickListener mClickListener;
    private List<FutureItem> mList;
    private Context mContext;

    public FutureItemAdapter(View.OnClickListener clickListener){
        mClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.future_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FutureItem item = mList.get(position);

        if(item != null){
            holder.setTag(item.getOrderId());
            holder.title.setText(item.getTitle().toUpperCase());
            holder.description.setText(mContext.getString(R.string.item_short_description,
                    item.getDescription().length() > SHORT_DESCRIPTION_LENGTH
                    ? item.getDescription().substring(0, SHORT_DESCRIPTION_LENGTH)
                    : item.getDescription()));
            holder.description.setOnClickListener(holder);
            Picasso.with(mContext).load(item.getImageUrl()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : -1;
    }

    public void setItems(List<FutureItem> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View parent;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.image)
        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            parent = ((ViewGroup) itemView).getChildAt(0);
            ButterKnife.bind(this,itemView);
        }

        public void setTag(int orderId){
            parent.setTag(orderId);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onClick(parent);
        }
    }
}
