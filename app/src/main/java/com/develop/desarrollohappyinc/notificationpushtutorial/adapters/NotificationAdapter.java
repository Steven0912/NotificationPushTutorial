package com.develop.desarrollohappyinc.notificationpushtutorial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.desarrollohappyinc.notificationpushtutorial.R;
import com.develop.desarrollohappyinc.notificationpushtutorial.datas.PushNotificationModel;

import java.util.ArrayList;

/**
 * Created by DESARROLLO HAPPY INC on 24/03/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private ArrayList<PushNotificationModel> mListNotifications = new ArrayList<>();

    public void setUpList(ArrayList<PushNotificationModel> listNotifications) {
        mListNotifications = listNotifications;
        notifyDataSetChanged();
    }

    public void addItem(PushNotificationModel pushMessage) {
        mListNotifications.add(pushMessage);
        notifyItemInserted(0);
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_notification, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        PushNotificationModel newNotification = mListNotifications.get(position);

        holder.mLbls[0].setText(newNotification.getTitle());
        holder.mLbls[1].setText(newNotification.getDescription());
        holder.mLbls[2].setText(String.format("Válido hasta el %s", newNotification.getExpiryDate()));
        holder.mLbls[3].setText(String.format("%d%%", (int) (newNotification.getDiscount() * 100)));
    }

    @Override
    public int getItemCount() {
        return mListNotifications.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        TextView[] mLbls = new TextView[4];

        NotificationViewHolder(View itemView) {
            super(itemView);

            int[] ids = {R.id.tv_title, R.id.tv_description, R.id.tv_expiry_date, R.id.tv_discount};
            for (int i = 0; i < mLbls.length; i++) {
                mLbls[i] = (TextView) itemView.findViewById(ids[i]);
            }
        }
    }
}
