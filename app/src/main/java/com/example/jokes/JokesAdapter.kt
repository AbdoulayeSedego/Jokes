package com.example.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jokes.databinding.ListItemBinding


class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

   inner class JokesViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffcallback = object : DiffUtil.ItemCallback<Jokes>()  {
        override fun areItemsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Jokes, newItem: Jokes): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffcallback)
    var jokes : List<Jokes>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = jokes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            ListItemBinding.inflate(

                    LayoutInflater.from(parent.context),
            parent,
            false
        )
            )

    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.binding.apply {
            val joke = jokes[position]
            tvType.text = joke.type
            tvJoke.text = joke.punchline
            tvSetup.text = joke.setup
        }
    }
} //end