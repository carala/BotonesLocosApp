package com.example.dam2.practicaevaluable4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Carlos on 12/01/2017.
 */

public class DialogoFinal extends DialogFragment implements View.OnClickListener {

    private OnFinalDialogoListener escuchador;
    private int faseFinal;
    private String nombre;

    private TextView textViewNombre;
    private TextView textViewFase;
    private Button buttonCerrar;
    private Button buttonReiniciar;

    public static DialogoFinal newInstance(int fase, String nombre) {
        DialogoFinal fragment = new DialogoFinal();
        fragment.faseFinal=fase;
        fragment.nombre= nombre;
        return fragment;
    }




    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View finalDialog = getActivity().getLayoutInflater().inflate(R.layout.final_dialog, null);

        textViewNombre = (TextView) finalDialog.findViewById(R.id.textViewNombre);
        textViewFase = (TextView) finalDialog.findViewById(R.id.textViewFase);
        buttonCerrar = (Button) finalDialog.findViewById(R.id.buttonCerrar);
        buttonReiniciar = (Button) finalDialog.findViewById(R.id.buttonReiniciar);

        buttonCerrar.setOnClickListener(this);
        buttonReiniciar.setOnClickListener(this);

        textViewNombre.setText("El juego ha terminado "+nombre);
        textViewFase.setText("Ha llegado al nivel " + faseFinal);
        builder.setTitle("Game Over");
        builder.setView(finalDialog);



        //Devuelvo el AlertDialog ya configurado
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonCerrar){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.buttonReiniciar){
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            startActivity(intent);
        }
    }


    public interface OnFinalDialogoListener {

    }

    //Método con el cual las actividades se nos suscribirán a nuestros eventos
    public void setOnFinalDialogoListener (OnFinalDialogoListener escuchador) {
        this.escuchador = escuchador;
    }

}
