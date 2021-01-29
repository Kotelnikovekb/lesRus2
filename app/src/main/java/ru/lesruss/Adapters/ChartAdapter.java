package ru.lesruss.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.lesruss.Class.ChartClass;
import ru.lesruss.R;
import ru.lesruss.basket;

public class ChartAdapter  extends ArrayAdapter<ChartClass> {
    List<ChartClass> list=new ArrayList<>();
    Activity Context;


    public ChartAdapter(Activity context, List<ChartClass> ListInput) {
        super(context, R.layout.item_chart, ListInput);
        list.addAll(ListInput);
        Context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChartClass[] chart = {getItem(position)};
        basket basket=new basket(Context);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_chart, null);
        }
        TextView title=convertView.findViewById(R.id.textView6);
        title.setText(chart[0].getPrice().getName());
        TextView description=convertView.findViewById(R.id.textView7);
        description.setText(chart[0].getPrice().getTitle());
        ImageView close=convertView.findViewById(R.id.imageView4);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket.deleteChart(chart[0]);
                updateChartList(basket.getList());
                basket.calculateCost(((TextView)Context.findViewById(R.id.textView8)));
            }
        });


        ImageView addChart=convertView.findViewById(R.id.imageButton2);
        ImageView minusChart=convertView.findViewById(R.id.imageButton);
        TextView count=convertView.findViewById(R.id.textView9);

        count.setText(chart[0].getCount()+"");
        addChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart[0] =basket.setCountChart(chart[0], ru.lesruss.basket.Action.PLUS);
                notifyDataSetChanged();
                basket.calculateCost(((TextView)Context.findViewById(R.id.textView8)));
            }
        });
        minusChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart[0] =basket.setCountChart(chart[0], ru.lesruss.basket.Action.MINUS);
                notifyDataSetChanged();
                basket.calculateCost(((TextView)Context.findViewById(R.id.textView8)));
            }
        });
        return convertView;
    }
    public void updateChartList(List<ChartClass> chartList) {
        list.clear();
        list.addAll(chartList);
        this.notifyDataSetChanged();
    }
}
