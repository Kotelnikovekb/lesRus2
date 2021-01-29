package ru.lesruss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.lesruss.Class.ChartClass;
import ru.lesruss.Class.Price;
import ru.lesruss.Module.AsyncHttpPost;

import static ru.lesruss.MainActivity.BASKET_S;
import static ru.lesruss.MainActivity.SETTINGS_S;

public class basket {
    private List<ChartClass> list=new ArrayList<>();
    private Context context;
    private SharedPreferences sharedPreferences;

    public basket(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(SETTINGS_S,Context.MODE_PRIVATE);

        try{
            JSONArray array=new JSONArray(sharedPreferences.getString(BASKET_S,"[]"));
            for (int i=0;i<array.length();i++){
                JSONObject obj=array.getJSONObject(i);
                list.add(new ChartClass(
                       new Price(
                               obj.getInt("id"),
                               obj.getString("name"),
                               obj.getString("description"),
                               obj.getInt("cost"),
                               obj.getString("page"),
                               obj.getString("unit"),
                               obj.getString("title")
                       ),
                        obj.getInt("count")
                ));
            }
        }catch (JSONException e){
            e.printStackTrace();

        }
    }

    public List<ChartClass> getList() {
        return list;
    }
    public boolean isProductInBasket(int id){
        for (ChartClass chart:list){
            if (chart.getPrice().getId()==id){
                return true;
            }
        }
        return false;
    }
    public void addToChart(Price price){
        list.add(new ChartClass(price,1));
        saveChart();
    }
    public void deleteChart(ChartClass chartClass){
        Log.e("backet","s"+list.size());
        list.remove(chartId(chartClass));
        Log.e("backet","e "+list.size());
        saveChart();
    }
    private void saveChart(){
        try {
            JSONArray array = new JSONArray() ;
            for (ChartClass chart:list){
                JSONObject obj=new JSONObject();
                obj.put("id",chart.getPrice().getId());
                obj.put("name",chart.getPrice().getName());
                obj.put("count",chart.getCount());
                obj.put("description",chart.getPrice().getDescription());
                obj.put("cost",chart.getPrice().getCost());
                obj.put("page",chart.getPrice().getPage());
                obj.put("unit",chart.getPrice().getUnit());
                obj.put("title",chart.getPrice().getTitle());
                array.put(obj);
            }
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(BASKET_S,array.toString());
            editor.commit();
            Log.e("basket",array.toString());

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public ChartClass setCountChart(ChartClass chart, Action action){
        int id=chartId(chart);
        int c=chart.getCount();
        if (action==Action.PLUS){
            c++;
        }else {
            if (c>0){
                c--;
            }
        }
        chart.setCount(c);
        Log.e("basket","id "+id+" count "+c+" chart "+chart.toString());

        list.set(id,chart);

        saveChart();
        return chart;
    }
    public enum Action{
        PLUS,MINUS
    }
    public void clear(){
        list.clear();
        saveChart();
    }
    public String calculateCost(TextView textView){
        double cost=0;
        for (ChartClass chartClass:list){
            cost+=chartClass.getCount()*chartClass.getPrice().getCost();
        }
        textView.setText("Итого: "+cost+" руб.");
        return cost+"";
    }
    private int chartId(ChartClass chartClass){
        for (int i=0;i<list.size();i++){
            if (list.get(i).getPrice().getId()==chartClass.getPrice().getId()){
                return i;
            }
        }
        return -1;
    }
    public String printOrder() {
       try {
           JSONArray array=new JSONArray();
           for (ChartClass chart :getList()){
               JSONObject obj=new JSONObject();
               obj.put("id",chart.getPrice().getId());
               obj.put("name",chart.getPrice().getTitle());
               obj.put("cost",chart.getPrice().getCost());
               obj.put("quantity",chart.getCount());
               array.put(obj);
           }
           return array.toString();
       }catch (JSONException e){
           return "[]";
       }


    }

}
