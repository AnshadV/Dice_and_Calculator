package com.example.multiscreen;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.multiscreen.databinding.EntitySlideBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<SlideModel> slideModelArrayList;
    Context context;

    public  CustomAdapter(Context context, ArrayList<SlideModel> slideModelArrayList) {
        this.context = context;
        this.slideModelArrayList = slideModelArrayList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        EntitySlideBinding entitySlideBinding;
        MyViewHolder(EntitySlideBinding b){
            super(b.getRoot());
            entitySlideBinding = b;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(EntitySlideBinding.inflate(li, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.entitySlideBinding.textViewName.setText((slideModelArrayList.get(position).name+""));
        slideModelArrayList.get(position).slide_no = (position+1)+"/"+slideModelArrayList.size()+"";
        holder.entitySlideBinding.tvSlidenumber.setText(slideModelArrayList.get(position).slide_no+"");

        String image_url = slideModelArrayList.get(position).img_thumb+"";
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.mipmap.ic_launcher_round);
        Glide.with(context).load(image_url).apply(options).into(holder.entitySlideBinding.imageView);
        //don't start the media player if video_url is empty
        if(!Objects.equals(slideModelArrayList.get(position).video_url, "")) {
            try {
                final String uriPath = slideModelArrayList.get(position).video_url;
                holder.entitySlideBinding.videoView.setVideoPath(uriPath);

                holder.entitySlideBinding.videoView.requestFocus();
                holder.entitySlideBinding.videoView.start();
                holder.entitySlideBinding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        holder.entitySlideBinding.videoView.seekTo(0);
                        mp.setLooping(true);
                        mp.setVolume(0, 0);
                        //mp.setVolume(1,1);

                        mp.start();
                        holder.entitySlideBinding.imageView.setVisibility(View.GONE);
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return slideModelArrayList.size();
    }
}
