package com.example.dhakkan.newsudpate;

/**
 * Created by Dhakkan on 7/21/2016.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {
    public RecyclerView mRecyclerView;
    FoldingCellAdapter adapter;
    FloatingActionButton fabShare;
    LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabShare = (FloatingActionButton) findViewById(R.id.fab_more);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layout = (LinearLayout) findViewById(R.id.progressbar_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Category.class);
                startActivity(intent);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("http://news.vaetas.com/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();

        NewsService newsShort_api = retrofit.create(NewsService.class);

        Call<StoryResponse> call = newsShort_api.fetchStories();


        call.enqueue(new Callback<StoryResponse>() {
            @Override

            public void onResponse(Call<StoryResponse> call, Response<StoryResponse> response) {

                ArrayList<CellTable> list1 = new ArrayList<>();
                for (int i = 0; i < response.body().getData().size(); i++) {
                    Log.d("FETCHALL", String.valueOf(response.body()));
                    CellTable cellTable = new CellTable();
                    cellTable.setTitle("" + response.body().getData().get(i).getTitle().toString());
                    cellTable.setDescription("" + response.body().getData().get(i).getDescription().toString());
                    cellTable.setFullStory("" + response.body().getData().get(i).getArticleLink().toString());
                    cellTable.setImage("" + response.body().getData().get(i).getThumbnailUrl().toString());
                    cellTable.setTime("" + response.body().getData().get(i).getCreatedAt().toString());
                    list1.add(cellTable);
                }
                adapter = new FoldingCellAdapter(MainActivity.this, list1);
                mRecyclerView.setAdapter(adapter);
                new Task().execute();

//                adapter.notifyDataSetChanged();



            }

            @Override


            public void onFailure(Call<StoryResponse> call, Throwable t) {

                Log.d("Codekamo", t.getMessage());

                t.printStackTrace();
            }
        });


    }
    class Task extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            layout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}








