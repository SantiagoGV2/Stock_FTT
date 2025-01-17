package com.fibrateltec.stockapp.herramientas


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.fibrateltec.stockapp.R
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley

//se crea esta clase para poder almacernar los datos que se asignan a los empleados
class HerramientasAdapter(private val herramientas: List<Herramienta>) : RecyclerView.Adapter<HerramientasAdapter.HerramientaViewHolder>() {

    class Herramienta(
        val codigo: String,
        val descripcion: String,
        val estado: String,
        val entrega: String,
        val devolucion: String,
        val observacion: String,
        val expedicion : String,
        val vencimiento : String,
        val cedula : String

        )
    inner class HerramientaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Aquí puedes encontrar y asignar tus vistas de item_herramienta.xml
        val codigoTextView: TextView = itemView.findViewById(R.id.codigo)
        val descripcionTextView: TextView = itemView.findViewById(R.id.descripcion)
        val estadoTextView: TextView = itemView.findViewById(R.id.estado)
        val entregaTextView: TextView = itemView.findViewById(R.id.entrega)
        val devolucionTextView: TextView = itemView.findViewById(R.id.devolucion)
        val observacionTextView: TextView = itemView.findViewById(R.id.observacion)
        val expedicionTextView: TextView = itemView.findViewById(R.id.expedi)
        val vencimientoTextView: TextView = itemView.findViewById(R.id.venci)
        val cedulaTextView: TextView = itemView.findViewById(R.id.cedula)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HerramientaViewHolder {
        //aqui se infla el layout para mostrar los datos
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_herramientas, parent, false)


        return HerramientaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HerramientaViewHolder, position: Int) {
        //se establecen los campos que se mostraran en los campos correspondientes
        val currentItem = herramientas[position]
        holder.codigoTextView.text = currentItem.codigo
        holder.descripcionTextView.text = currentItem.descripcion
        holder.estadoTextView.text = currentItem.estado
        holder.entregaTextView.text = currentItem.entrega
        holder.devolucionTextView.text = currentItem.devolucion
        holder.observacionTextView.text = currentItem.observacion
        holder.expedicionTextView.text = currentItem.expedicion
        holder.vencimientoTextView.text = currentItem.vencimiento
        holder.cedulaTextView.text = currentItem.cedula

        //Actualizacion de los datos
        holder.itemView.findViewById<ImageView>(R.id.btn_actualizar).setOnClickListener {
            val currentItem = herramientas[position]
            val intent = Intent(holder.itemView.context, Herramientas2::class.java).apply {
                putExtra("codigo", currentItem.codigo)
                putExtra("descripcion", currentItem.descripcion)
                putExtra("estado", currentItem.estado)
                putExtra("entrega", currentItem.entrega)
                putExtra("devolucion", currentItem.devolucion)
                putExtra("observacion", currentItem.observacion)
                putExtra("expedicion", currentItem.expedicion)
                putExtra("vencimiento", currentItem.vencimiento)
            }
            holder.itemView.context.startActivity(intent)
        }

        //Eliminar de los datos por su codigo
        holder.itemView.findViewById<ImageView>(R.id.btn_eliminar).setOnClickListener {
            val currentItem = herramientas[position]

            val url = "https://fibrateltecsas.com/eliminar_herramienta.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener { response ->
                    Toast.makeText(holder.itemView.context, response, Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
                },
                Response.ErrorListener { error ->
                    Toast.makeText(holder.itemView.context, "Error: $error", Toast.LENGTH_SHORT).show()
                }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["her_cod"] = currentItem.codigo
                    return params
                }
            }

            val requestQueue = Volley.newRequestQueue(holder.itemView.context)
            requestQueue.add(stringRequest)
        }
    }
    //ste método sobrescribe getItemCount del adaptador, que se utiliza para devolver el número de elementos en la lista herramientas
    override fun getItemCount() = herramientas.size

//Estas variables almacenan funciones lambda que reciben un Int como parámetro y no devuelven nada (Unit). Se utilizan para manejar eventos de clic en los elementos para actualizar o eliminar.
    private var actualizarClickListener: ((Int) -> Unit)? = null
    private var eliminarClickListener: ((Int) -> Unit)? = null

    fun setOnActualizarClickListener(listener: (Int) -> Unit) {
        actualizarClickListener = listener
    }

    fun setOnEliminarClickListener(listener: (Int) -> Unit) {
        eliminarClickListener = listener
    }

}