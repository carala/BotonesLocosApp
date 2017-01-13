package com.example.dam2.practicaevaluable4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by dam2 on 13/01/2017.
 */

public class CustomDialog extends DialogFragment implements DialogInterface.OnClickListener, View.OnClickListener{

    private Button buttonJugar;
    private Button buttonVolver;
    private TextView texto1;
    private TextView textViewNombre;
    private EditText editTextNombre;
    private RadioGroup grupoBotones;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private OnFragmentoDialogoListener escuchador;


    public static CustomDialog newInstance() {
        CustomDialog fragment = new CustomDialog();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Construyo y muestro el diálogo
        //Primero genero un constructor de diálogos de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.custom_dialog,null));

        buttonJugar = (Button) getActivity().findViewById(R.id.buttonJugar);
        buttonVolver = (Button) getActivity().findViewById(R.id.buttonVolver);
        texto1 = (TextView) getActivity().findViewById(R.id.textViewTexto1);
        textViewNombre = (TextView) getActivity().findViewById(R.id.textViewNombre);
        editTextNombre = (EditText) getActivity().findViewById(R.id.editTextNombre);
        grupoBotones = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        radio1 = (RadioButton) getActivity().findViewById(R.id.radioButton1);
        radio2 = (RadioButton) getActivity().findViewById(R.id.radioButton2);
        radio3 = (RadioButton) getActivity().findViewById(R.id.radioButton3);

        buttonJugar.setOnClickListener(this);
        buttonVolver.setOnClickListener(this);
        /*radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);*/




        //Devuelvo el AlertDialog ya configurado
        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonJugar) {
            escuchador.onOpcionElegida(textViewNombre.getText().toString(), grupoBotones.getCheckedRadioButtonId());
        }
    }

    public interface OnFragmentoDialogoListener {
        void onOpcionElegida(String nombre, int dificultad);

    }

    public void setOnFragmentoDialogoListener (OnFragmentoDialogoListener escuchador) {
        this.escuchador = escuchador;
    }

}
