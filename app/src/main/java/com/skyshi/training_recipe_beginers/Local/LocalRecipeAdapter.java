package com.skyshi.training_recipe_beginers.Local;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.skyshi.training_recipe_beginers.ItemClickListener;
import com.skyshi.training_recipe_beginers.R;

import java.util.List;

/**
 * Created by skyshi on 19/04/16.
 */
public class LocalRecipeAdapter extends RecyclerView.Adapter<LocalRecipeAdapter.ViewHolder>{
    List<LocalObject> localObjectList;
    Activity act;
    private ItemClickListener listener;
    public  LocalRecipeAdapter(List<LocalObject> localObjectList,Activity act,ItemClickListener listener){
        this.localObjectList = localObjectList;
        this.act = act;
        this.listener = listener;
    }
    @Override
    public LocalRecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_local_recipe,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final LocalRecipeAdapter.ViewHolder holder, final int position) {
        holder.txt_title_local_recipe.setText(localObjectList.get(position).getName_food());
        holder.txt_description_local_recipe.setText(localObjectList.get(position).getType());
        Glide.with(act).load(localObjectList.get(position).getImage_name()).asBitmap()
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(new BitmapImageViewTarget(holder.img_local_recipe) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable roundedCircle = RoundedBitmapDrawableFactory.create(act.getResources(), resource);
                        roundedCircle.setCircular(true);
                        holder.img_local_recipe.setImageDrawable(roundedCircle);
                    }
                });
        holder.img_local_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.viewItem(
                        localObjectList.get(position).getImage_name(),
                        localObjectList.get(position).getName_food(),
                        localObjectList.get(position).getType(),
                        localObjectList.get(position).getPrice(),
                        localObjectList.get(position).getOriginal_place(),
                        localObjectList.get(position).getMain_ingredients(),
                        localObjectList.get(position).getIngredients(),
                        localObjectList.get(position).getTools(),
                        localObjectList.get(position).getStep()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return localObjectList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_local_recipe;
        TextView txt_title_local_recipe,txt_description_local_recipe;
        public ViewHolder(View itemView) {
            super(itemView);
            img_local_recipe = (ImageView)itemView.findViewById(R.id.img_local_list_recipe);
            txt_title_local_recipe =(TextView)itemView.findViewById(R.id.txt_title_local_recipe);
            txt_description_local_recipe = (TextView)itemView.findViewById(R.id.txt_description_local_recipe);
        }
    }
}
