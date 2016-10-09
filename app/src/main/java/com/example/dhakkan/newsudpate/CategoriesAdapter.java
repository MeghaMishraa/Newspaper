package com.example.dhakkan.newsudpate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhakkan on 7/22/2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {
    Context c;
    List<CategoryTable> list = new ArrayList<>();
    ArrayList<Integer> idlist = new ArrayList<>();
    int pos=0;
    public CategoriesAdapter(Context c, List<CategoryTable> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_model, null);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoriesViewHolder holder, int position) {

        holder.checkedTextView.setText(""+list.get(position).getTitle().toString());
        holder.textViewId.setText(""+list.get(position).getId());
        holder. checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.checkedTextView.isChecked()) {
                    holder.checkedTextView.setChecked(true);
                } else
                    holder.checkedTextView.setChecked(false);
            }
        }
    );

}

    @Override
    public int getItemCount() {
        return list.size();
    }
}

