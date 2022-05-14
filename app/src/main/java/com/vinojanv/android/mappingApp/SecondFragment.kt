package com.vinojanv.android.mappingApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vinojanv.android.mappingApp.database.AppDatabase
import com.vinojanv.android.mappingApp.databinding.FragmentSecondBinding
import com.vinojanv.android.mappingApp.model.Client

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //creating the room database
        val db = AppDatabase.getDatabase(view.context)
        //friend DAO
        val clientDao = db.clientDao()

        binding.addButton.setOnClickListener {
            val id = clientDao.getAll().size + 1
            val name = binding.nameEditText.text.toString();
            val lat = binding.latEditText.text.toString().toFloat()
            val lgt = binding.lngEditText.text.toString().toFloat()

            val client = Client(id, name, lat, lgt)
            clientDao.add(client)

            binding.nameEditText.setText("")
            binding.latEditText.setText("")
            binding.lngEditText.setText("")

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}