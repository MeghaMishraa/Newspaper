
package com.example.dhakkan.newsudpate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class FoldingCellAdapter extends RecyclerView.Adapter<FoldingCellHolder> {
        Context c;
        ArrayList<CellTable> arrayList;
        private static final int SECOND_MILLIS = 1000;
        private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

        public FoldingCellAdapter(Context c, ArrayList<CellTable> arrayList) {
            this.c = c;
            this.arrayList = arrayList;
        }

        @Override
        public FoldingCellHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.foldingcell, null);
            return new FoldingCellHolder(view);
        }


        @Override
        public void onBindViewHolder(final FoldingCellHolder holder, int position) {
            holder.mTitleText.setText("" + arrayList.get(position).getTitle().toString());
            holder.mDescription.setText("" + arrayList.get(position).getDescription().toString());
            holder.imageurl = (this.arrayList.get(position).getImage());
            holder.textViewReadFullStoryLink.setText("" + arrayList.get(position).getFullStory().toString());

            holder.mTextTime.setText("" + arrayList.get(position).getTime().toString());
            String toParse = arrayList.get(position).getTime().toString(); // Results in "2-5-2012 20:43"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
            Date date = null; // You will need try/catch around this
            try {
                date = formatter.parse(toParse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long millis = date.getTime();
            holder.mTextTime.setText(String.valueOf(getTimeAgo(millis)));
            Picasso.with(holder.context).load(this.arrayList.get(position).getImage()).fit().into(holder.mImageUrl);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public static String getTimeAgo(long time) {
            if (time < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time *= 1000;
            }

            long now = new Date().getTime();
            if (time > now || time <= 0) {
                return null;
            }

            // TODO: localize
            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                return diff / MINUTE_MILLIS + " minutes ago";
            } else if (diff < 90 * MINUTE_MILLIS) {
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                return diff / HOUR_MILLIS + " hours ago";
            } else if (diff < 48 * HOUR_MILLIS) {
                return "yesterday";
            } else {
                return diff / DAY_MILLIS + " days ago";
            }
        }
    }







