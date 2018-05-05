package com.alextroy.galleryalextroy;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.alextroy.galleryalextroy.Model.PhotoItem;
import com.alextroy.galleryalextroy.Model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    public static final String FLICKR_PHOTOS_GET_RECENT = "flickr.photos.getRecent";
    public static final String KEY = "820f8209816df714b1e5ce5e035484a0";
    public static final String FORMAT_RESPONSE = "json";
    public static final String IMAGE_TYPE = "url_s";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    private List<PhotoItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_photo);

        recyclerView = findViewById(R.id.recycler_view);
        list = new ArrayList<>();

        layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        adapter = new PhotoAdapter(list, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getData();

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getData();
                    }
                });
    }

    private void getData() {
        App.getApi().getData(FLICKR_PHOTOS_GET_RECENT, KEY, FORMAT_RESPONSE, 1, IMAGE_TYPE).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body() != null) {
                    list.clear();
                    adapter.setData(response.body().getPhotos().getPhoto());
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No connections", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
