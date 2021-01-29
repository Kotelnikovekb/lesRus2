package ru.lesruss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ru.lesruss.Class.Price;

public class ProductActivity extends AppCompatActivity {

    Button addBasket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Price price=DATA.getPriceById(getIntent().getIntExtra("id",0));

        setTitle(price.getTitle());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addBasket=findViewById(R.id.button4);
        basket basket=new basket(this);
        if (basket.isProductInBasket(price.getId())){
            addBasket.setText("Товар в корзине. Оформить заказ");
        }
        addBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basket.isProductInBasket(price.getId())){
                    startActivity(new Intent(ProductActivity.this,ChartActivity.class));
                }else {
                    basket.addToChart(price);
                    addBasket.setText("Товар в корзине. Оформить заказ");
                }
            }
        });

    }
}