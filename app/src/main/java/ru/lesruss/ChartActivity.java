package ru.lesruss;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.lesruss.Adapters.ChartAdapter;
import ru.lesruss.Class.ChartClass;
import ru.lesruss.Module.AsyncHttpPost;

public class ChartActivity extends AppCompatActivity {

    ChartAdapter adapter;
    ListView listView;
    Button createOrder;
    TextView total;
    List<ChartClass> list=new ArrayList<>();
    basket basket;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        setTitle("Корзина");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        total=findViewById(R.id.textView8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        basket=new basket(ChartActivity.this);
        list.addAll(basket.getList());
        listView=findViewById(R.id.listChart);
        adapter=new ChartAdapter(this,list);
        listView.setAdapter(adapter);
        createOrder=findViewById(R.id.button6);
        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basket.getList().size()>0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChartActivity.this);
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    final View dialogView = LayoutInflater.from(ChartActivity.this).inflate(R.layout.dialog_order, viewGroup, false);
                    builder.setView(dialogView);
                    alertDialog = builder.create();
                    alertDialog.show();
                    Button payment=dialogView.findViewById(R.id.button7);
                    EditText name=dialogView.findViewById(R.id.nameCustomer);
                    EditText phone=dialogView.findViewById(R.id.phoneCustomer);
                    EditText address=dialogView.findViewById(R.id.address);

                    payment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!name.getText().toString().equals("")&&!phone.getText().toString().equals("")){
                                HashMap<String, String> data = new HashMap<String, String>();
                                data.put("name", name.getText().toString());
                                data.put("phone", phone.getText().toString());
                                data.put("total", "1");
                                data.put("productList", basket.printOrder());
                                data.put("address",address.getText().toString());

                                AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data,"https://ekb-app.ru/PHP/newOrder.php");
                                asyncHttpPost.setListener(new AsyncHttpPost.Listener() {
                                    @Override
                                    public void onResult(String result) {
                                        Log.e("ChartLink",result);
                                        startActivityForResult((new Intent(ChartActivity.this,PayActivity.class).putExtra("link",result)),1);
                                    }
                                });
                                asyncHttpPost.execute();
                            }else {
                                Toast.makeText(ChartActivity.this, "Укажите данные", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(ChartActivity.this, "Корзина пуста", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button clear=findViewById(R.id.button5);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket.clear();
                adapter.notifyDataSetChanged();
            }
        });
        basket.calculateCost(total);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Заказ не оплачен. попробуйте снова", Toast.LENGTH_SHORT).show();
        }else {
            alertDialog.hide();
            basket.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Заказ создан, чек на почте", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ChartActivity.this,MainActivity.class));
            finish();
        }



    }
}