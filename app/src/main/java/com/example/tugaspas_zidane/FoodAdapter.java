package com.example.tugaspas_zidane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{

    private Context context;
    private List<FoodModel> foodList;
    private FoodAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname, tvleague, tvdateFirstEvent, tvcountry;
        public ImageView ivFoodImage;

        public MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvleague = view.findViewById(R.id.tvleague);
            tvdateFirstEvent = view.findViewById(R.id.tvdateFirstEvent);
            tvcountry = view.findViewById(R.id.tvcountry);
            ivFoodImage = view.findViewById(R.id.ivlogoteam);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onFoodSelected(foodList.get(getAdapterPosition()));
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onDeleteClickListener(foodList.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }

    public FoodAdapter(Context context, List<FoodModel> foodList, FoodAdapterListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        final FoodModel contact = this.foodList.get(position);
        holder.tvname.setText(contact.getStrSport());
        holder.tvleague.setText(contact.getStrLeague());
        holder.tvdateFirstEvent.setText(contact.getDateFirstEvent());
        holder.tvcountry.setText(contact.getStrCountry());
        Glide.with(holder.itemView.getContext()).load(contact.getStrBadge()).into(holder.ivFoodImage);
    }

    @Override
    public int getItemCount() {
        return this.foodList.size();
    }

    public interface FoodAdapterListener {
        void onFoodSelected(FoodModel foodModel);
        void onDeleteClickListener(FoodModel foodModel);
    }
}
