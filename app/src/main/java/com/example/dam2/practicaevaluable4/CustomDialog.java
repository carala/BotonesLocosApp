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

public class CustomDialog extends DialogFragment implements View.OnClickListener{

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
    private int dificultad;
    private String nombre;


    public static CustomDialog newInstance() {
        Bundle args = new Bundle();
        CustomDialog fragment = new CustomDialog();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Construyo y muestro el diálogo
        //Primero genero un constructor de diálogos de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View customDialog = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog, null);

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

        builder.setView(customDialog);

        AlertDialog dialog = builder.setTitle("Botones Locos").create();
        dialog.setCanceledOnTouchOutside(false);

        //Devuelvo el AlertDialog ya configurado
        return builder.create();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonJugar) {
            if(radio1.isChecked()){
                this.dificultad = 1 ;
            }else if(radio2.isChecked()){
                this.dificultad = 2 ;
            }else if(radio3.isChecked()){
                this.dificultad = 3 ;
            }
            this.nombre = editTextNombre.getText().toString();

            escuchador.onOpcionElegida(nombre,dificultad);
            dismiss();
        }
    }

    public interface OnFragmentoDialogoListener {
        void onOpcionElegida(String nombre, int dificultad);

    }

    public void setOnFragmentoDialogoListener (OnFragmentoDialogoListener escuchador) {
        this.escuchador = escuchador;
    }

}
