package ru.lesruss.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.lesruss.Class.SectionModel;
import ru.lesruss.R;


public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder>  {
    class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionLabel, showAllButton;
        private RecyclerView itemRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            sectionLabel = (TextView) itemView.findViewById(R.id.section_label);
            showAllButton = (TextView) itemView.findViewById(R.id.section_show_all_button);
            itemRecyclerView = (RecyclerView) itemView.findViewById(R.id.item_recycler_view);
        }
    }

    private Activity context;
    private ArrayList<SectionModel> sectionModelArrayList;

    public SectionRecyclerViewAdapter(Activity context, ArrayList<SectionModel> sectionModelArrayList) {
        this.context = context;
        this.sectionModelArrayList = sectionModelArrayList;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, final int position) {
        final SectionModel sectionModel = sectionModelArrayList.get(position);
        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        switch (sectionModelArrayList.get(position).getType()) {
            case LINEAR_VERTICAL:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case LINEAR_HORIZONTAL:
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager1);
                break;
            case GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                holder.itemRecyclerView.setLayoutManager(gridLayoutManager);
                break;

        }
        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList());
        holder.itemRecyclerView.setAdapter(adapter);

        //show toast on click of show all button
        holder.showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sectionModelArrayList.get(position).getIntent()!=null){
                    context.startActivity(sectionModelArrayList.get(position).getIntent());
                }
            }
        });
        if (sectionModel.getSectionLabel().equals("")){
            Animation titleAnimation= AnimationUtils.loadAnimation(context, R.anim.login_title_loop);
            holder.showAllButton.setVisibility(View.GONE);
            holder.sectionLabel.setAnimation(titleAnimation);
        }
    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }

}
