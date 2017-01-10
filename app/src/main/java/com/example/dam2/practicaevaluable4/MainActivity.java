package com.example.dam2.practicaevaluable4;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonJugar;
    private TextView textViewInstrucciones;
    private TextView textViewInstrucciones2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonJugar = (ImageButton) findViewById(R.id.imageButtonJugar);
        textViewInstrucciones = (TextView) findViewById(R.id.textViewInstrucciones);
        textViewInstrucciones2 = (TextView) findViewById(R.id.textViewInstrucciones2);

        botonJugar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}
