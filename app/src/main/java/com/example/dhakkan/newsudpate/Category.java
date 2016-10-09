package com.example.dhakkan.newsudpate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Category extends AppCompatActivity {
    RecyclerView recyclerViewCategories;
    ArrayList<CategoryTable> list1 = new ArrayList<>();
    ArrayList<Integer> list;
    CategoriesAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerviewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this));
        fab = (FloatingActionButton) findViewById(R.id.fab_more);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=1;
                list = adapter.idlist;
                Log.d("SIZE", String.valueOf(list.size()));
                for (int j=adapter.idlist.size()-1;j>0;j--)
                {
                    if (list.get(j) == 0)
                    {
                        list.remove(j);
                        Log.d("J", String.valueOf(j));
                    }
                }
                Log.d("HERE", String.valueOf(list));
                Intent intent = new Intent(Category.this , MainActivity.class);
                intent.putExtra("FINAL_CATEGORIES",list);
                intent.putExtra("CHECK",i);
                startActivity(intent);
                finish();
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news.vaetas.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService mailchimpService = retrofit.create(NewsService.class);

        Call<List<CategoryResponse>> call = mailchimpService.fetchCategory();
        call.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                Log.d("CATEGORY", "onrespone called");
                for (int i = 0; i < response.body().size(); i++) {
                    CategoryTable table = new CategoryTable();
                    table.setTitle("" + response.body().get(i).getTitle());
                    table.setId(response.body().get(i).getId());
                    list1.add(table);
                    Log.d("CATEGORY", String.valueOf(response.body().get(i).getId()));
                }
                adapter = new CategoriesAdapter(Category.this, list1);
                recyclerViewCategories.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Log.d("CATEGORY", "onfailure called");
                t.getMessage();
                t.printStackTrace();
            }
        });
    }


}

