package com.i053114.taller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i053114.taller.Models.Coment;
import com.i053114.taller.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 13/10/2017.
 */

public class AdapterComent extends RecyclerView.Adapter<AdapterComent.ViewHolder>{
    List<Coment> comentList= new ArrayList<>();
    Context context;

    public AdapterComent (List<Coment>  comentList, Context context){
        this.comentList= comentList;
        this.context= context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater .from(parent.getContext()).inflate(R.layout.itemcomments,parent, false);

        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewPostId.setText(Integer.toString(comentList.get(position).getPostId()));
        holder.textViewid.setText(Integer.toString(comentList.get(position).getId()));
        holder.textViewEmail.setText(comentList.get(position).getEmail());
        holder.textViewBody.setText(comentList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return comentList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewPostId;
        TextView    textViewid;
        TextView textViewEmail;
        TextView textViewBody;

        public ViewHolder (View item){
            super(item);
            itemView.setOnClickListener(this);
            textViewPostId =(TextView) item.findViewById(R.id.id_tv_userinput_name);
            textViewid =(TextView) item.findViewById(R.id.id_tv_userinput_username);
            textViewEmail=(TextView) item.findViewById(R.id.id_tv_userinput_email);
            textViewBody=(TextView) item.findViewById(R.id.id_tv_userinput_phone);

        }


        @Override
        public void onClick(View view) {


        }
    }
}
