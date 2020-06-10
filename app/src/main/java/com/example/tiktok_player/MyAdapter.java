package com.example.tiktok_player;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<VideoInfo> videolist;
    private Context context;

    public MyAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder holder, final int position) {
        holder.imageView.setVisibility(View.VISIBLE);
        holder.videoView.setVisibility(View.GONE);
        holder.TextView1.setText(videolist.get(position).getDescription());
        holder.TextView2.setText(videolist.get(position).getNickname());

        Glide.with(context)
                .load(videolist.get(position).getAvatar())
                .transition(withCrossFade())
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                holder.imageView.setVisibility(View.GONE);
                holder.videoView.setVisibility(View.VISIBLE);
                holder.videoView.setVideoPath(videolist.get(position).getUrl());
            }
        });

        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                holder.videoView.requestFocus();
                holder.videoView.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(videolist==null)
            return 0;
        else
            return videolist.size();
    }

    public void setData(List<VideoInfo> list) { this.videolist = list; }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private VideoView videoView;
        private ImageView imageView;
        private TextView TextView1;
        private TextView TextView2;
        private GestureDetector myGestureDetector;

        @SuppressLint("ClickableViewAccessibility")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            imageView = itemView.findViewById(R.id.imageView);
            TextView1 = itemView.findViewById(R.id.description);
            TextView2 = itemView.findViewById(R.id.nickname);

            myGestureDetector = new GestureDetector(context, new MyOnGestureListener());
            videoView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    myGestureDetector.onTouchEvent(event);
                    return true;
                }
            });

        }

        class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
                videoView.performClick();
                return super.onSingleTapConfirmed(e);
            }
        }
    }



}
