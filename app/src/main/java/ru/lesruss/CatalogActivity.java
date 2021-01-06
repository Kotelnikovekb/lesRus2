package ru.lesruss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.lesruss.Adapters.SectionRecyclerViewAdapter;
import ru.lesruss.Class.CatalogClass;
import ru.lesruss.Class.ItemModel;
import ru.lesruss.Class.SectionModel;
import ru.lesruss.Module.SharedVoid;

import static ru.lesruss.Enum.RecyclerViewType.LINEAR_VERTICAL;

public class CatalogActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SectionRecyclerViewAdapter adapter;
    ArrayList<SectionModel> sectionModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        String page=getIntent().getStringExtra("nextPage");
        Log.e("CATALOG=",page);
        CatalogClass catalog= DATA.getItemCatalog(this,page);

        recyclerView = (RecyclerView) findViewById(R.id.catalogRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        if (catalog!=null){
            setTitle(catalog.getName());

            TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

            tabHost.setup();

            TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");

            tabSpec.setContent(R.id.tab1);
            tabSpec.setIndicator("Список товаров");
            tabHost.addTab(tabSpec);

            tabSpec = tabHost.newTabSpec("tag2");
            tabSpec.setContent(R.id.tab2);
            tabSpec.setIndicator("Описание");
            tabHost.addTab(tabSpec);


            tabHost.setCurrentTab(0);
            TextView description=findViewById(R.id.textView5);
            description.setText(catalog.getDescription());


            ArrayList<ItemModel> itemArrayList = new ArrayList<>();
            itemArrayList.addAll(DATA.getCatalog(this,page));
            sectionModelArrayList.add(new SectionModel("" , itemArrayList,LINEAR_VERTICAL,null));

            adapter = new SectionRecyclerViewAdapter(this, sectionModelArrayList);
            recyclerView.setAdapter(adapter);

        }else {
            setTitle("Страница не найдена");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}