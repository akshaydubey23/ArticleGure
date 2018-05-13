package com.articlegure.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.articlegure.Model.ArticleData;
import com.articlegure.Adapter.DataAdapter;
import com.articlegure.R;
import com.articlegure.Helper.SQLiteHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SQLiteHandler db;
    DataAdapter mAdapter;
    private int itemCount = 0;
    private ArrayList<HashMap<String, String>> artLists = new ArrayList<HashMap<String, String>>();
    List<ArticleData> mainartList = new ArrayList<ArticleData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();

        db = new SQLiteHandler(this);
        loadallitems();
        mAdapter = new DataAdapter(this,mainartList);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void initRecyclerView() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void loadallitems() {
        artLists.clear();
        mainartList.clear();

        artLists = db.getUserDetails();

        for (int i = 0; i < artLists.size(); ++i) {

            String id = artLists.get(i).get("uid");
            String img = artLists.get(i).get("images");
            String des = artLists.get(i).get("description");
            String like = artLists.get(i).get("likes");

            ArticleData feeds = new ArticleData(id,des,img,like);
            //  shopIDS.add(i, shopid);
            mainartList.add(feeds);
        }
        //  mAdapter.notifyDataSetChanged();



    }
    public void updateItemCount(boolean ifIncrement) {
        if (ifIncrement) {
            itemCount++;
        }


    }
}
