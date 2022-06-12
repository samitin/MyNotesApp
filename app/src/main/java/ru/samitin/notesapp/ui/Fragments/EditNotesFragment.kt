package ru.samitin.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditNotesBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
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

        findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            val bottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete,)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(notes.data.id!!)

                findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
                bottomSheet.dismiss()
            }
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }

}