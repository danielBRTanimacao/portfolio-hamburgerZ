package com.example.hamburgueriaz;

import android.os.Bundle;
import android.view.InputEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void subtractBtn(View v){
        TextView txt = findViewById(R.id.QuantityBurger);
        int quantity = Integer.parseInt(txt.getText().toString());
        if (quantity <= 0) {
            txt.setText(String.valueOf(quantity));
        } else {
            quantity--;
            txt.setText(String.valueOf(quantity));
        }
    }

    public void sumBtn(View v){
        TextView txt = findViewById(R.id.QuantityBurger);
        int quantity = Integer.parseInt(txt.getText().toString());
        quantity++;
        txt.setText(String.valueOf(quantity));
    }

    public void sendRequest(View v){
        int baseBurgerPrice = 20;
        int priceBacon = 2;
        int priceCheese = 2;
        int priceOnionRings = 3;
        int finalPrice = 0;

        TextView txtQuantity = findViewById(R.id.QuantityBurger);
        int quantity = Integer.parseInt(txtQuantity.getText().toString());

        TextView result = findViewById(R.id.Result);
        TextView userName = findViewById(R.id.UserName);

        CheckBox baconChosed = findViewById(R.id.Bacon);
        CheckBox cheeseChosed = findViewById(R.id.Cheese);
        CheckBox onionRingsChosed = findViewById(R.id.OnionRings);

        String haveBacon;
        String haveCheese;
        String haveOnionRings;

        if (baconChosed.isChecked()) {
            haveBacon = "Sim";
            finalPrice += priceBacon;
        } else {
            haveBacon = "Não";
        }
        if (cheeseChosed.isChecked()) {
            haveCheese = "Sim";
            finalPrice += priceCheese;
        } else {
            haveCheese = "Não";
        }
        if (onionRingsChosed.isChecked()) {
            haveOnionRings = "Sim";
            finalPrice += priceOnionRings;
        } else {
            haveOnionRings = "Não";
        }

        finalPrice+= quantity * baseBurgerPrice;

        result.setText(userName.getText() +"\nTem Bacon?" + haveBacon + "\nTem Queijo?"+haveCheese+"\nTem Onion Rings?" +haveOnionRings + "\nQuantidade:" +quantity + "\nPreço final: R$" + finalPrice);

    }
}