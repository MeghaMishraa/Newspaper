package com.example.dhakkan.newsudpate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;


public class FoldingCellHolder extends RecyclerView.ViewHolder {
    public TextView mTitleText;
    public TextView mTextTime;
    public TextView mDescription;
    public ImageView mImageUrl;

    public String imageurl = "";
    public TextView textViewReadFullStory, textViewReadFullStoryLink;


    Context context;

    public FoldingCellHolder(View itemView) {
        super(itemView);
        final FoldingCell fc = (FoldingCell) itemView.findViewById(R.id.folding_cell);
        mTitleText = (TextView) itemView.findViewById(R.id.title_textView);
        mTextTime = (TextView) itemView.findViewById(R.id.text_time);
        mDescription = (TextView) itemView.findViewById(R.id.description_textview);
        mImageUrl = (ImageView) itemView.findViewById(R.id.url_imageview);
        textViewReadFullStoryLink = (TextView) itemView.findViewById(R.id.tv_fullstory_unfolded_link);
        textViewReadFullStory = (TextView) itemView.findViewById(R.id.tv_fullstory_unfolded);
        context = itemView.getContext();

        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

        textViewReadFullStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url = textViewReadFullStoryLink.getText().toString();
            Uri webpage = Uri.parse(url);
            Intent i = new Intent(Intent.ACTION_VIEW,webpage);
            v.getContext().startActivity(i);

            }
        });


    }
}


