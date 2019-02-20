package com.example.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationbar.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwelaryAdapter extends RecyclerView.Adapter<JwelaryAdapter.Holder> {

    Integer[] setOfImages = new Integer[]{
            R.drawable.gold_earrings,
            R.drawable.golden_band,
            R.drawable.golden_earring,
            R.drawable.golden_hand_band,
            R.drawable.hand_band,
            R.drawable.jhumka,
            R.drawable.necklase_perl,
            R.drawable.neckless,
            R.drawable.ring
    };

    private Context context = null;
    List<String> jwelName = null;
    List<String> jwelPrice = null;

    public JwelaryAdapter(Context context) {
        this.context = context;
        jwelName = Arrays.asList(context.getResources().getStringArray(R.array.jwel_name));
        jwelPrice = Arrays.asList(context.getResources().getStringArray(R.array.jwel_rate));
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.jwellary_card,parent,false);
        GridLayoutManager.LayoutParams lp=(GridLayoutManager.LayoutParams) v.getLayoutParams();
        lp.height=parent.getMeasuredHeight();
        v.setLayoutParams(lp);

        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jwellary_card, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.image.setImageResource(setOfImages[position]);
        holder.name.setText(jwelName.get(position));
        holder.rate.setText(jwelPrice.get(position));
    }

    @Override
    public int getItemCount() {
        try {
            return setOfImages.length;
        }catch (Exception e){
            return 0;
        }
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView name, rate;
        ImageView image;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_jwelary_name);
            rate = itemView.findViewById(R.id.txt_jwelary_price);
            image = itemView.findViewById(R.id.img_jwellary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
              public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Intent detailIntent = new Intent(context, DetailActivity.class);
                    detailIntent.putExtra("key_name", jwelName.get(pos));
                    detailIntent.putExtra("key_price", jwelPrice.get(pos));
                    detailIntent.putExtra("key_image", setOfImages[pos]);
                    detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(detailIntent);
                }

            });
        }
    }
}
