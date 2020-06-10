package com.example.video_plyaer;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import android.os.Handler;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<VideoResult> data;

    public MyAdapter(List<VideoResult> data) {
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public VideoView videoView;
        public TextView id;
        public TextView nickname;
        public TextView description;
        public TextView likecount;
        public ImageView pause;
        public ImageView thumbs_up;
        public ImageView thumbs_up2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            id = itemView.findViewById(R.id.id);
            id.setTextColor(Color.WHITE);
            nickname = itemView.findViewById(R.id.nickname);
            nickname.setTextColor(Color.WHITE);
            description = itemView.findViewById(R.id.description);
            description.setTextColor(Color.WHITE);
            likecount = itemView.findViewById(R.id.likecount);
            likecount.setTextColor(Color.WHITE);
            pause = itemView.findViewById(R.id.pause);
            thumbs_up = itemView.findViewById(R.id.thumbs_up);
            thumbs_up2 = itemView.findViewById(R.id.thumbs_up2);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.videoView.setVideoPath(data.get(position).getFeedurl());

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                holder.videoView.start();
            }
        });
        GestureDetector gestureDetector = new GestureDetector(holder.itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            //单击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if(holder.videoView.isPlaying()){
                    holder.videoView.pause();
                    holder.pause.setVisibility(View.VISIBLE);
                }else{
                    holder.videoView.start();
                    holder.pause.setVisibility(View.INVISIBLE);
                }
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e){
                holder.thumbs_up2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        holder.thumbs_up2.setVisibility(View.INVISIBLE);
                    }
                };
                handler.postDelayed(runnable, 500);
                Handler handler2 = new Handler();
                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        holder.thumbs_up2.animate().scaleX(3);
                        holder.thumbs_up2.animate().scaleY(3);
                    }
                };
                handler2.postDelayed(runnable2, 100);

                Handler handler3 = new Handler();
                Runnable runnable3 = new Runnable() {
                    @Override
                    public void run() {
                        holder.thumbs_up2.animate().scaleX(1);
                        holder.thumbs_up2.animate().scaleY(1);
                    }
                };
                handler3.postDelayed(runnable3, 500);
                //点赞数加一
                data.get(position).setLikecount(data.get(position).getLikecount() + 1);
                holder.likecount.setText("" + data.get(position).getLikecount());
                return true;
            }
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        holder.id.setText("ID" + data.get(position).get_id());
        holder.nickname.setText("@" + data.get(position).getNickname());
        holder.description.setText(data.get(position).getDescription());
        holder.likecount.setText("" + data.get(position).getLikecount());
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
        holder.videoView.start();
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
