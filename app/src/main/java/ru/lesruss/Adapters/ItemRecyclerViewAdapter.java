package ru.lesruss.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.lesruss.Class.ItemModel;
import ru.lesruss.R;


public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder> {
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemLabel;
        CardView cardView;
        ImageView icon;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemLabel = (TextView) itemView.findViewById(R.id.textView);
            cardView=itemView.findViewById(R.id.cardMenu);
            icon=itemView.findViewById(R.id.imageView2);

        }
    }

    private Activity context;
    private ArrayList<?> arrayList;



    public ItemRecyclerViewAdapter(Activity context, ArrayList<?> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

        if (arrayList.get(position)==null){
            Animation titleAnimation= AnimationUtils.loadAnimation(context,R.anim.login_title_loop);
            holder.itemLabel.setAnimation(titleAnimation);
            holder.cardView.setAnimation(titleAnimation);
            holder.itemLabel.setBackgroundColor(context.getResources().getColor(R.color.background));
        }else {

            if(arrayList.get(position) instanceof ItemModel){
                holder.cardView.setAnimation(null);
                holder.itemLabel.setAnimation(null);
                ItemModel itemModel=(ItemModel) arrayList.get(position);
                holder.itemLabel.setText(itemModel.getTitle());
                if (itemModel.getBackground()!=null){
                    holder.icon.setImageBitmap(itemModel.getBackground());
                }
                if (itemModel.getIntent()!=null){
                    holder.cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(itemModel.getIntent());
                        }
                    });
                }

            }



        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
