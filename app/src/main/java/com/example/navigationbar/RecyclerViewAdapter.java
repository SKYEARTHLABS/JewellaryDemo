package com.example.navigationbar;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Integer[] images= new Integer[]{
            R.drawable.cover,
            R.drawable.cover2,
            R.drawable.cover3
    };

    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listview,viewGroup,false);



        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.imageview.setImageResource(images[i]);

    }

    @Override
    public int getItemCount() {
        try {
            return images.length;
        }catch (Exception e){
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview= itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Intent detailIntent = new Intent(context, MainActivity.class);
                    detailIntent.putExtra("key_image", images[pos]);
                    detailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(detailIntent);
                }

            });

        }
    }
}
