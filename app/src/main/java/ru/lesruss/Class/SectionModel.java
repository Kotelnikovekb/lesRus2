package ru.lesruss.Class;

import android.content.Intent;

import java.util.ArrayList;

import ru.lesruss.Enum.RecyclerViewType;


public class SectionModel {
    private String sectionLabel;
    private ArrayList<ItemModel> itemArrayList;
    private RecyclerViewType Type;
    private Intent intent;

    public SectionModel(String sectionLabel, ArrayList<ItemModel> itemArrayList, RecyclerViewType type, Intent intent) {
        this.sectionLabel = sectionLabel;
        this.itemArrayList = itemArrayList;
        Type = type;
        this.intent = intent;
    }

    public void setSectionLabel(String sectionLabel) {
        this.sectionLabel = sectionLabel;
    }

    public ArrayList<ItemModel> getItemArrayList() {
        return itemArrayList;
    }

    public void setItemArrayList(ArrayList<ItemModel> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public RecyclerViewType getType() {
        return Type;
    }

    public void setType(RecyclerViewType type) {
        Type = type;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getSectionLabel() {
        return sectionLabel;
    }


}
