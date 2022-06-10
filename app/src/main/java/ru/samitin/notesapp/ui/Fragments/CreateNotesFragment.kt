package ru.samitin.notesapp.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.samitin.notesapp.R
import ru.samitin.notesapp.databinding.FragmentCreateNotesBinding
import ru.samitin.notesapp.databinding.FragmentHomeBinding

class CreateNotesFragment : Fragment() {
    private lateinit var _binding: FragmentCreateNotesBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateNotesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNotesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}