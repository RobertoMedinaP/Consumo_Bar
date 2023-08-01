package com.example.consumo_bar.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.consumo_bar.Modelo.Model.Consumo
import com.example.consumo_bar.Modelo.Model.ConsumoDatabase
import com.example.consumo_bar.R
import com.example.consumo_bar.ViewModel.ConsumoViewModel
import com.example.consumo_bar.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //instancia viewmodel

    private val viewModel: ConsumoViewModel by activityViewModels()
    private var idConsumo: Int=0
    private var consumoSeleccionado: Consumo?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val database = Room.databaseBuilder(
            requireContext().applicationContext,
            ConsumoDatabase::class.java,
            "ConsumoDb"
        )
            .build()


        binding.btagregar.setOnClickListener {
            //poner esto en una funcion?? se ve poco elegante
           /* var total = binding.textInputLayoutPrecio.editText?.text.toString()
                .toInt() * binding.textInputLayoutCantidad.editText?.text.toString().toInt()

            val nuevoConsumo = Consumo(

                producto = binding.textInputLayoutProducto.editText?.text.toString(),
                precio = binding.textInputLayoutPrecio.editText?.text.toString().toInt(),
                cantidad = binding.textInputLayoutCantidad.editText?.text.toString().toInt(),
                //el total debe ser precio por cantidad, podre llamarlo dentro de la db?
                total = total
            )

            binding.textViewTotal.text="Total\n $total"
             *//**  despertar la base de datos*//*

            viewModel.insertConsumo(nuevoConsumo)*/
            guardarDatos()


        }
        //boton agregar funciona, podria agregar validaciones

        binding.btmostrar.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btborrar.setOnClickListener {
            viewModel.deleteAllConsumo()
        }

        //recibir lo seleccionado en el recycler view, podria mandar una booleana para visibilidad
        //de boton borrar esta tarea

        /**ACA el problema, se me habia olvidado pasar a string */
        viewModel.consumoSeleccionado().observe(viewLifecycleOwner){
            it?.let {
                consumoElegido->
                binding.textInputLayoutProducto.editText?.setText(consumoElegido.producto)
                binding.textInputLayoutPrecio.editText?.setText(consumoElegido.precio.toString())
                binding.textInputLayoutCantidad.editText?.setText(consumoElegido.cantidad.toString())
                val totalidad=consumoElegido.precio*consumoElegido.cantidad
                binding.textViewTotal.setText(totalidad.toString())
                idConsumo=consumoElegido.id
                consumoSeleccionado=consumoElegido
            }

        }





    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun guardarDatos(){
        var total= binding.textInputLayoutPrecio.editText?.text.toString()
            .toInt() * binding.textInputLayoutCantidad.editText?.text.toString().toInt()
        val producto = binding.textInputLayoutProducto.editText?.text.toString()
        val precio = binding.textInputLayoutPrecio.editText?.text.toString().toInt()
        val cantidad = binding.textInputLayoutCantidad.editText?.text.toString().toInt()
        //el total debe ser precio por cantidad, podre llamarlo dentro de la db?
        total = total
        binding.textViewTotal.text="Total\n $total"


        if (idConsumo==0){
            val nuevoConsumo=Consumo(
                producto=producto,
                precio=precio,
                cantidad = cantidad,
                total = total
            )
            viewModel.insertConsumo(nuevoConsumo)
        }else{
            val nuevoConsumo1=Consumo(
                id=idConsumo,
                producto=producto,
                precio=precio,
                cantidad = cantidad,
                total = total
            )
            viewModel.updateConsumo(nuevoConsumo1)
        }

    }
}