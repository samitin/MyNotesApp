package ru.samitin.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.notesapp.Model.Notes
import ru.samitin.notesapp.R
import ru.samitin.notesapp.databinding.ItemNotesBinding
import ru.samitin.notesapp.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,val notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList.get(position)
        holder.binding.apply {
            notesTitle.text = note.title
            notesSubtitle.text = note.subTitle
            notesDate.text = note.date
            when (note.priority){
                "1" -> viewPriority.setBackgroundResource(R.drawable.red_dot)
                "2" -> viewPriority.setBackgroundResource(R.drawable.yellow_dot)
                "3" -> viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data = note)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = notesList.size
}