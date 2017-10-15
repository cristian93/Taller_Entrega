package com.i053114.taller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i053114.taller.MainActivity;
import com.i053114.taller.MainActivityComment;
import com.i053114.taller.MainActivityPosts;
import com.i053114.taller.Models.Posts;
import com.i053114.taller.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 14/10/2017.
 */

public class AdapterPost extends RecyclerView.Adapter<AdapterPost.ViewHolder> {
    List<Posts> postsList = new ArrayList<>();
    Context context;

    public AdapterPost (List<Posts> postsList, Context context){
        this.postsList= postsList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itempost,parent, false);
      ViewHolder viewHolder =new ViewHolder(item);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textViewUserId.setText(Integer.toString(postsList.get(position).getUserId()));
        holder.textViewid.setText(Integer.toString(postsList.get(position).getId()));
        holder.textViewTitle.setText(postsList.get(position).getTitle());
        holder.textViewBody.setText(postsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewUserId;
        TextView    textViewid;
        TextView textViewTitle;
        TextView textViewBody;

        public ViewHolder (View item){
            super(item);
            item.setOnClickListener(this);
            textViewUserId =(TextView) item.findViewById(R.id.id_tv_userinput_name);
            textViewid =(TextView) item.findViewById(R.id.id_tv_userinput_username);
            textViewTitle=(TextView) item.findViewById(R.id.id_tv_userinput_email);
            textViewBody=(TextView) item.findViewById(R.id.id_tv_userinput_phone);

        }


        @Override
        public void onClick(View view) {
            Context contextItem=view.getContext();
            Intent intent = new Intent(context, MainActivityComment.class);
            intent .putExtra("postId", postsList.get(getLayoutPosition()).getUserId());
            contextItem.startActivity(intent);

        }
    }
}
