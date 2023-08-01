package com.example.consumo_bar.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumo_bar.ConsumoAdapter
import com.example.consumo_bar.Modelo.Model.Consumo
import com.example.consumo_bar.R
import com.example.consumo_bar.ViewModel.ConsumoViewModel
import com.example.consumo_bar.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ConsumoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

       val adapter=ConsumoAdapter()
        val recyclerView=binding.rv1
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(context,
            DividerItemDecoration.VERTICAL)
        )

        viewModel.todoelconsumo.observe(viewLifecycleOwner){
            it?.let{
                adapter.updateData(it)
            }
        }

        adapter.selectedItem().observe(viewLifecycleOwner){
            it?.let {
                viewModel.seleccionado(it)
                Log.d("que recibo", it.producto)
                /**recibo bien, el problema es volver al primer frag*
                 * acá la seleccion funciona bien
                 */

                //voy a mandar el elemento seleccionado al primer fragmento
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}