package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity<whipped> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberOfCoffees=1;
    EditText name;
    String Name;
    CheckBox Whipped;
    CheckBox Chocolate;
    boolean whipped=false;
    boolean chocolate=false;
    int extra=0;



    public void getName(View view){
        name=(EditText)findViewById(R.id.editTextTextPersonName);
        Name=name.getText().toString();
    }
    public void submitOrder(View view){
        display(numberOfCoffees);
        String priceMessage="Name: " +Name+ "\nAdd whipped cream? " +whipped+ "\nAdd chocolate? " +chocolate+  "\nQuantity: " +numberOfCoffees + "\nTotal: $" +((numberOfCoffees*5)+extra)+ "\nThank you!";
        displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " +Name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void whippedCream(View view){
        Whipped=(CheckBox)findViewById(R.id.checkBox);
        whipped=Whipped.isChecked();
        if(whipped==true){
            extra=extra+1;
        }
    }
    public void wellChocolate(View view){
        Chocolate=(CheckBox)findViewById(R.id.checkBox2);
        chocolate=Chocolate.isChecked();
        if(chocolate==true){
            extra=extra+1;
        }
    }
    public void increment(View view){
        if(numberOfCoffees==100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees=numberOfCoffees+1;
        display(numberOfCoffees);
    }
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    public void decrement(View view){
        if(numberOfCoffees==1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            numberOfCoffees=numberOfCoffees-1;
            display(numberOfCoffees);
        }

    }
    private void display(int number){
        TextView quantityTextView=(TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


}