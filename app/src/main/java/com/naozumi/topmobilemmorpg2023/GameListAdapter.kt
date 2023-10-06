package com.naozumi.topmobilemmorpg2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.naozumi.topmobilemmorpg2023.databinding.ItemRowGameBinding
import com.naozumi.topmobilemmorpg2023.model.Game
import com.naozumi.topmobilemmorpg2023.util.ViewUtils

class GameListAdapter: ListAdapter<Game, GameListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)
    }

    inner class ListViewHolder(var binding: ItemRowGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            with(binding) {
                tvItemGameName.text = game.name
                tvItemGamePublisher.text = game.publisher
                tvItemGameRating.text = "${game.rating}★"
                tvItemGameTotalDownloads.text = ViewUtils.formatGameDownloads(game.totalDownloads)
                tvItemGameDescription.text = game.description
                btnExpand.setOnClickListener {
                    if (tvItemGameDescription.visibility == View.GONE) {
                        btnExpand.setImageResource(R.drawable.baseline_expand_less_24)
                        divider1.visibility = View.VISIBLE
                        tvItemGameDescription.visibility = View.VISIBLE
                    } else {
                        btnExpand.setImageResource(R.drawable.baseline_expand_more_24)
                        divider1.visibility = View.GONE
                        tvItemGameDescription.visibility = View.GONE
                    }
                }
                Glide.with(itemView.context)
                    .load(game.icon)
                    .error(R.drawable.animugurl)
                    .into(ivItemGameIcon)
                itemView.setOnClickListener {
                    ViewUtils.moveActivity(
                        itemView.context,
                        DetailActivity::class.java,
                        game
                    )
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Game> =
            object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(oldGame: Game, newGame: Game): Boolean {
                    return oldGame.name == newGame.name
                }

                override fun areContentsTheSame(oldGame: Game, newGame: Game): Boolean {
                    return oldGame == newGame
                }
            }
    }

}