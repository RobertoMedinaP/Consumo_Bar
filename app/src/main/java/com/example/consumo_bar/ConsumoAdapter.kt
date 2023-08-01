package com.example.consumo_bar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.consumo_bar.Modelo.Model.Consumo
import com.example.consumo_bar.databinding.ItemListBinding

class ConsumoAdapter():RecyclerView.Adapter<ConsumoAdapter.AdapterViewHolder>() {

    private var dataSet= listOf<Consumo>()
    private val consumoSeleccionado= MutableLiveData<Consumo>()


    inner class AdapterViewHolder(private val binding: ItemListBinding):
            RecyclerView.ViewHolder(binding.root),



    View.OnClickListener{


                fun bind(consumo: Consumo){
                    binding.tvProducto.text=consumo.producto
                    binding.tvPrecio.text=consumo.cantidad.toString()
                    binding.tvCantidad.text=consumo.precio.toString()
                    binding.tvTotal.text=consumo.total.toString()

                    itemView.setOnClickListener(this)
                }

        override fun onClick(p0: View?) {
            consumoSeleccionado.value=dataSet[adapterPosition]

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding= ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val consumoActual=dataSet[position]
        holder.bind(consumoActual)

    }

    fun updateData(newDataSet: List<Consumo>){
        dataSet=newDataSet
        notifyDataSetChanged()
    }

    fun selectedItem(): LiveData<Consumo> =consumoSeleccionado


}