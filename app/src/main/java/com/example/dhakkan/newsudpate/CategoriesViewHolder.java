package com.example.dhakkan.newsudpate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

/**
 * Created by Ashu on 7/8/2016.
 */
public class CategoriesViewHolder extends RecyclerView.ViewHolder  {
    CheckedTextView checkedTextView;
    TextView textViewId;


    public CategoriesViewHolder(final View itemView) {
        super(itemView);
        checkedTextView = (CheckedTextView) itemView.findViewById(R.id.ctv);
        textViewId = (TextView) itemView.findViewById(R.id.tv_id);

    }
}
