package ru.samitin.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import ru.samitin.notesapp.Model.Notes
import ru.samitin.notesapp.R
import ru.samitin.notesapp.ViewModel.NotesViewModel
import ru.samitin.notesapp.databinding.FragmentEditNotesBinding
import ru.samitin.notesapp.databinding.FragmentHomeBinding
import java.util.*

class EditNotesFragment : Fragment() {

    private lateinit var _binding: FragmentEditNotesBinding
    private val binding get() = _binding
    private val notes by navArgs<EditNotesFragmentArgs>()
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditNotesBinding.inflate(inflater,container,false)
        binding.apply {
            editTitle.setText( notes.data.title)
            editSubTitle.setText( notes.data.subTitle)
            editNotes.setText( notes.data.notes)

            when (notes.data.priority){
                "1" -> pRed.setImageResource(R.drawable.ic_baseline_done_24)
                "2" -> pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                "3" -> pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            }
            pRed.setOnClickListener {
                notes.data.priority = "1"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            pYellow.setOnClickListener {
                notes.data.priority = "2"
                pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                pGreen.setImageResource(0)
                pRed.setImageResource(0)
            }
            pGreen.setOnClickListener {
                notes.data.priority = "3"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }


            btnEditNotes.setOnClickListener { 
                updateNotes(it)
            }
        }


        return binding.root
    }

    private fun updateNotes(view: View) {
        notes.data.title = binding.editTitle.text.toString()
        notes.data.subTitle = binding.editSubTitle.text.toString()
        notes.data.notes = binding.editNotes.text.toString()

        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        notes.data.date = notesDate.toString()
        viewModel.updateNotes(notes.data)

        Navigation.findNavController(view).navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }

}