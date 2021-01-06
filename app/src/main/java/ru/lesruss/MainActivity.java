package ru.lesruss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import ru.lesruss.Adapters.SectionRecyclerViewAdapter;
import ru.lesruss.Class.ItemModel;
import ru.lesruss.Class.SectionModel;
import ru.lesruss.Module.SharedVoid;

import static ru.lesruss.Enum.RecyclerViewType.GRID;
import static ru.lesruss.Enum.RecyclerViewType.LINEAR_HORIZONTAL;
import static ru.lesruss.Enum.RecyclerViewType.LINEAR_VERTICAL;

public class MainActivity extends AppCompatActivity {

    public final static String SETTINGS_S="SETTINGS_S";
    public final static String FIRS_START_S="FIRS_START_S";
    RecyclerView recyclerView;
    SectionRecyclerViewAdapter adapter;
    ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleMain);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<ItemModel> itemArrayList = new ArrayList<>();
        itemArrayList.addAll(DATA.getMain(this));
        sectionModelArrayList.add(new SectionModel("" , itemArrayList,LINEAR_VERTICAL,null));

        adapter = new SectionRecyclerViewAdapter(this, sectionModelArrayList);
        recyclerView.setAdapter(adapter);
    }
}