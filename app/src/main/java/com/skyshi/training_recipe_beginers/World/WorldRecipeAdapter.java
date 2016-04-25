package com.skyshi.training_recipe_beginers.World;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyshi.training_recipe_beginers.ItemClickListener;
import com.skyshi.training_recipe_beginers.R;

import java.util.List;

/**
 * Created by skyshi on 18/04/16.
 */
public class WorldRecipeAdapter extends RecyclerView.Adapter<WorldRecipeAdapter.ViewHolder>{
    List<WorldObject> worldObjectList;
    Activity act;
    private ItemClickListener listener;
    public WorldRecipeAdapter(List<WorldObject> worldObjectList,Activity act,ItemClickListener listener){
        this.worldObjectList = worldObjectList;
        this.act = act;
        this.listener = listener;
    }
    @Override
    public WorldRecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_world_recipe,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WorldRecipeAdapter.ViewHolder holder, final int position) {
        holder.txt_title_world_recipe.setText(worldObjectList.get(position).getName_food());
        holder.txt_description_world_recipe.setText(worldObjectList.get(position).getType());
        Glide.with(act).load(worldObjectList.get(position).getImage_name())
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(holder.img_world_recipe);
        holder.img_world_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.viewItem(
                        worldObjectList.get(position).getImage_name(),
                        worldObjectList.get(position).getName_food(),
                        worldObjectList.get(position).getType(),
                        worldObjectList.get(position).getPrice(),
                        worldObjectList.get(position).getOriginal_place(),
                        worldObjectList.get(position).getMain_ingredients(),
                        worldObjectList.get(position).getIngredients(),
                        worldObjectList.get(position).getTools(),
                        worldObjectList.get(position).getStep()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return worldObjectList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_world_recipe;
        TextView txt_title_world_recipe;
        TextView txt_description_world_recipe;
        public ViewHolder(View itemView) {
            super(itemView);
            img_world_recipe = (ImageView)itemView.findViewById(R.id.img_world_recipe);
            txt_title_world_recipe = (TextView)itemView.findViewById(R.id.txt_title_world_recipe);
            txt_description_world_recipe = (TextView)itemView.findViewById(R.id.txt_description_world_recipe);
        }
    }

}
