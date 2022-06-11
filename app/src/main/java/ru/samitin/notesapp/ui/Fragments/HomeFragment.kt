package ru.samitin.notesapp.ui.Fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ru.samitin.notesapp.Model.Notes
import ru.samitin.notesapp.R
import ru.samitin.notesapp.ViewModel.NotesViewModel
import ru.samitin.notesapp.databinding.FragmentHomeBinding
import ru.samitin.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    val viewModel : NotesViewModel by viewModels()
    private var oldMyNotes :List<Notes> = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
        viewModel.getNotes().observe(viewLifecycleOwner) {
            oldMyNotes = it
            adapter = NotesAdapter(requireContext(), notesList = it)
            binding.rcvAllNotes.adapter = adapter
        }
       creteFilter()
        return binding.root
    }

    private fun creteFilter() {
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it
                adapter = NotesAdapter(requireContext(), notesList = it)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it
                adapter = NotesAdapter(requireContext(), notesList = it)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it
                adapter = NotesAdapter(requireContext(), notesList = it)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) {
                oldMyNotes = it
                adapter = NotesAdapter(requireContext(), notesList = it)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty())
                    notesFilteting(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null!!
    }
    private fun notesFilteting(newText:String){
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes){
            if(i.title.contains(newText) || i.subTitle.contains(newText)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}