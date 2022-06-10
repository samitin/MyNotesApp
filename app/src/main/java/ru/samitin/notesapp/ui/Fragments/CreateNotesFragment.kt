package ru.samitin.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ru.samitin.notesapp.Model.Notes
import ru.samitin.notesapp.R
import ru.samitin.notesapp.ViewModel.NotesViewModel
import ru.samitin.notesapp.databinding.FragmentCreateNotesBinding

import java.util.*

class CreateNotesFragment : Fragment() {
    private lateinit var _binding: FragmentCreateNotesBinding
    private val binding get() = _binding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNotesBinding.inflate(inflater,container,false)
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "2"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(view: View) {
        var title = binding.editTitle.text.toString()
        var subTitle = binding.editSubTitle.text.toString()
        var notes = binding.editNotes.text.toString()

        val d = Date()
        val notesDate : CharSequence = DateFormat.format("MMMM d, yyyy ",d.time)
        val data = Notes(id = null,title = title, subTitle = subTitle, notes = notes, date = notesDate.toString(), priority = priority)
        viewModel.addNotes(data)

        Navigation.findNavController(view).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }

}