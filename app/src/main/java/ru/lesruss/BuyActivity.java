package ru.lesruss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.lesruss.Module.AsyncHttpPost;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        setTitle("Оформить заказ");

        TextInputEditText contact=findViewById(R.id.contact);
        TextInputEditText name=findViewById(R.id.name);
        TextInputEditText message=findViewById(R.id.message);
        Button send=findViewById(R.id.button3);

        ProgressDialog progressDialog=new ProgressDialog(BuyActivity.this);
        progressDialog.setTitle("Создаю заказ");
        progressDialog.setMessage("Отправляю данные");
        progressDialog.setCancelable(false);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getText().toString().equals("")){
                    Snackbar.make(contact,"Укажите контакт",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (name.getText().toString().equals("")){
                    Snackbar.make(contact,"Укажите имя",Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (message.getText().toString().equals("")){
                    Snackbar.make(contact,"Укажите заказ",Snackbar.LENGTH_LONG).show();
                    return;
                }
                progressDialog.show();
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("message", message.getText().toString());
                data.put("contact", contact.getText().toString());
                data.put("name", name.getText().toString());

                AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data,"https://ekb-app.ru/PHP/sendEmail.php");
                asyncHttpPost.setListener(new AsyncHttpPost.Listener() {
                    @Override
                    public void onResult(String result) {
                        if (result.equals("ok")){
                            progressDialog.dismiss();
                            Toast.makeText(BuyActivity.this, "Заказ создан. Мы скоро свяжемся с вами ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BuyActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                });
                asyncHttpPost.execute();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
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