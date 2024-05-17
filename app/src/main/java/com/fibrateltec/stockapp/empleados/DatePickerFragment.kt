package com.fibrateltec.stockapp.empleados

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
//Función: Esta clase representa un fragmento de diálogo que muestra un DatePicker para que el usuario seleccione una fecha.
//Constructor: Toma un listener como parámetro, que es una función lambda con tres parámetros (day, month, year) y sin valor de retorno (Unit). Este listener se invocará cuando el usuario seleccione una fecha.
//Herencia: La clase hereda de DialogFragment y implementa la interfaz DatePickerDialog.OnDateSetListener.

class DatePickerFragment (val listener : (day:Int,month:Int, year:Int)->Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener{
//
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //Parámetros:
    //view: El DatePicker asociado con este listener.
    //year: El año seleccionado.
    //month: El mes seleccionado (0-11, donde 0 es enero y 11 es diciembre).
    //dayOfMonth: El día del mes seleccionado.
        listener(dayOfMonth,month,year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //val c: Calendar = Calendar.getInstance(): Obtiene una instancia de Calendar con la fecha y hora actuales.
        //val day: Int = c.get(Calendar.DAY_OF_MONTH): Obtiene el día actual.
        //val month: Int = c.get(Calendar.MONTH): Obtiene el mes actual.
        //val year: Int = c.get(Calendar.YEAR): Obtiene el año actual.
        val c : Calendar= Calendar.getInstance()
        val day : Int= c.get(Calendar.DAY_OF_MONTH)
        val month : Int = c.get(Calendar.MONTH)
        val year : Int = c.get(Calendar.YEAR)
        //DatePickerDialog(activity as Context, this, year, month, day): Crea una instancia de DatePickerDialog inicializada con la fecha actual y configura this como el listener de la fecha seleccionada.
        val picker= DatePickerDialog(activity as Context,this,year,month,day)
        return picker
    }
}