package com.example.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder> {
    String TAG = "RecyclerAdaptor";
    Context mcontext;
    ArrayList<Images>imageslist;
   /* public RecyclerAdaptor(Context context,ArrayList<Images>imageslist){
        this.mcontext=context;
        this.imageslist=imageslist;

    }*/

    public RecyclerAdaptor(Context mcontext, ArrayList<Images> imagesList) {
        this.mcontext=mcontext;
        this.imageslist=imagesList;
    }

    @NonNull
    @Override
    public RecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycelview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtdescription.setText(imageslist.get(position).getDescription());
        // holder.imageView.setImageResource(imageslist.get(position).getUrl());
        Picasso.get().load(imageslist.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     ImageView imageView;
     TextView txtdescription;
        public ViewHolder(View itemview){
            super(itemview);

                imageView=(ImageView)itemview.findViewById(R.id.imageview);
                txtdescription=itemview.findViewById(R.id.txtdescription);

        }
    }


}
