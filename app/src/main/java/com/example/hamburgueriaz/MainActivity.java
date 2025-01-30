package com.example.hamburgueriaz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void subtractBtn(View v) {
        TextView txt = findViewById(R.id.QuantityBurger);
        int quantity = Integer.parseInt(txt.getText().toString());
        if (quantity > 0) {
            quantity--;
            txt.setText(String.valueOf(quantity));
        }
    }

    public void sumBtn(View v) {
        TextView txt = findViewById(R.id.QuantityBurger);
        int quantity = Integer.parseInt(txt.getText().toString());
        quantity++;
        txt.setText(String.valueOf(quantity));
    }

    private void submitEmail(String msg) {
        String destination = "CHANGE-ME";
        String subject = "Pedido HamburguerZ";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{destination});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            System.out.println("Nenhum aplicativo de e-mail encontrado!");
        }
    }

    public void sendRequest(View v) {
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

        boolean hasBacon = baconChosed.isChecked();
        boolean hasCheese = cheeseChosed.isChecked();
        boolean hasOnionRings = onionRingsChosed.isChecked();

        if (hasBacon) finalPrice += priceBacon;
        if (hasCheese) finalPrice += priceCheese;
        if (hasOnionRings) finalPrice += priceOnionRings;

        finalPrice += quantity * baseBurgerPrice;

        String message = String.format(
                "Nome: %s\nTem Bacon? %s\nTem Queijo? %s\nTem Onion Rings? %s\nQuantidade: %d\nPreço final: R$ %d",
                userName.getText().toString(),
                hasBacon ? "Sim" : "Não",
                hasCheese ? "Sim" : "Não",
                hasOnionRings ? "Sim" : "Não",
                quantity,
                finalPrice
        );

        result.setText(message);
        submitEmail(message);
    }
}
