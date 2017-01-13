package com.example.dam2.practicaevaluable4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by dam2 on 13/01/2017.
 */

public class DialogoFinal extends DialogFragment implements DialogInterface.OnClickListener {

    private OnFragmentoDialogoListener escuchador;
    private int faseFinal;

    //Constructor newInstance para poder pasarle el alumno
    public DialogoFinal newInstance(int fase) {
        DialogoFinal fragment = new DialogoFinal();
        this.faseFinal = fase;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Construyo y devuelvo el diálogo
        //Primero genero un constructor de diálogos de alerta
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("El juego ha terminado.\nHa llegado al nivel " + faseFinal);
        builder.setTitle("Game Over");

        builder.setPositiveButton(R.string.string_boton_positivo, (DialogInterface.OnClickListener) this);

        builder.setNegativeButton(R.string.string_boton_negativo, (DialogInterface.OnClickListener) this);

        //Devuelvo el AlertDialog ya configurado
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE){
            //Si existe una actividad suscrita, llamo a su método relacionado con mi evento
            if (escuchador != null){
                escuchador.onFinDelJuego();
            }
        }
        else if (which == DialogInterface.BUTTON_NEGATIVE){
        }
    }


    public interface OnFragmentoDialogoListener {
        void onFinDelJuego();
    }

    //Método con el cual las actividades se nos suscribirán a nuestros eventos
    public void setOnFinalDialogoListener (OnFragmentoDialogoListener escuchador) {
        this.escuchador = escuchador;
    }

}
