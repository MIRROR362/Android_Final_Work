package com.example.tiktok_player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private MyAdapter myAdapter;
    private List<VideoInfo> videolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewpager2);

        myAdapter = new MyAdapter(MainActivity.this);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        videolist = new ArrayList<>();
        viewPager2.setAdapter(myAdapter);
        getData();
        Log.d("zxr","\n-------------\n"+videolist.toString()+"\n-------------");


    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVideoInfo().enqueue(new Callback<List<VideoInfo>>() {
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                if (response.body() != null) {
                    List<VideoInfo> videoInfoList = response.body();
                    if (videoInfoList.size() != 0) {
                        videolist.addAll(videoInfoList);
                        myAdapter.setData(videolist);
                        myAdapter.notifyDataSetChanged();
                    }
                }
                Log.d("zxr",videolist.toString());
            }

            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                Log.d("zxr", t.getMessage());
            }
        });

    }

}
