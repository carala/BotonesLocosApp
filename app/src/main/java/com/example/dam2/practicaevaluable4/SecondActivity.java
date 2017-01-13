package com.example.dam2.practicaevaluable4;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, CustomDialog.OnFragmentoDialogoListener{

    private TextView textViewUsuario;
    private TextView textViewFase;

    private ArrayList<Button> arrayBotones;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private ProgressBar progressBar;
    private TextView textViewProgreso;

    private int progreso;
    private int contadorBotones;
    private final int n = 4 ;
    private int tiempo = 100;

    private MyAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewUsuario = (TextView) findViewById(R.id.textViewNombreUsuario);
        textViewFase = (TextView) findViewById(R.id.textViewFase);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textViewProgreso = (TextView) findViewById(R.id.textViewProgreso);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        arrayBotones = new ArrayList<Button>();
        arrayBotones.add(button1);
        arrayBotones.add(button2);
        arrayBotones.add(button3);
        arrayBotones.add(button4);

        contadorBotones = 1;


        CustomDialog customDialog = CustomDialog.newInstance();
        customDialog.setOnFragmentoDialogoListener(this);
        customDialog.show(getFragmentManager(),null);




    }



    @Override
    public void onClick(View v) {
        Button botonPulsado = (Button) v;

        if(Integer.parseInt(botonPulsado.getText().toString()) == contadorBotones){
            botonPulsado.setEnabled(false);
            contadorBotones++;
            if(contadorBotones == 5){
                task.cancel(false);
            }
        }

    }


    private void numerarBotones(ArrayList<Integer> arrayEnteros){
        for(int i = 0 ; i < arrayEnteros.size() ; i++){
            arrayBotones.get(i).setEnabled(true);
            arrayBotones.get(i).setText(String.valueOf(arrayEnteros.get(i)));
        }
    }

    private ArrayList<Integer> generarArrayAleatorio(int n){
        ArrayList<Integer> arr = new ArrayList<Integer>(n);

        for(int i = 0 ; i < n ; i++){
            arr.add(i+1);
        }
        Random rand = new Random();
        int r;
        int tmp;

        for(int i = n ; i>0; i--){
            r = rand.nextInt(i);
            tmp = arr.get(i - 1);
            arr.set(i - 1 , arr.get(r));
            arr.set(r,tmp);
        }
        return arr;
    }

    @Override
    public void onOpcionElegida(String nombre, int dificultad) {

        textViewUsuario.setText(nombre);
        if(dificultad == 1){
            this.tiempo = 100;
        }else if(dificultad == 2){
            this.tiempo = 75;
        }else if(dificultad == 3){
            this.tiempo = 50;
        }

        task = new MyAsyncTask();
        task.execute(tiempo);

    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        private int i = 0 ;

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(i <= 100) {
                SystemClock.sleep(integers[0]);
                if(isCancelled()){
                    break;
                }else {
                    publishProgress(i);
                    i++;

                }

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            numerarBotones(generarArrayAleatorio(n));
            progreso = 0;
            progressBar.setProgress(progreso);
            textViewProgreso.setText(progreso + " / 100");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            descativarBotones();
            Toast.makeText(SecondActivity.this, "Fin del juego", Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onProgressUpdate(Integer... integers){
            progreso=integers[0];
            progressBar.setProgress(progreso);
            textViewProgreso.setText(progreso + "/100");

        }

        private void descativarBotones(){
            button1.setEnabled(false);
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            progreso =0 ;
            contadorBotones = 1 ;
            tiempo = tiempo - 10;
            task = new MyAsyncTask();
            task.execute(tiempo);



        }


    }


}

