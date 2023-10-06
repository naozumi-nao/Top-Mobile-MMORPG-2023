package com.naozumi.topmobilemmorpg2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.naozumi.topmobilemmorpg2023.databinding.ActivityMainBinding
import com.naozumi.topmobilemmorpg2023.model.Game
import com.naozumi.topmobilemmorpg2023.util.ViewUtils

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Game>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGames.setHasFixedSize(true)

        list.addAll(getListGame())

        val gameListAdapter = GameListAdapter()

        binding.rvGames.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameListAdapter
        }

        gameListAdapter.submitList(list)

        binding.appBar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.about_page -> {
                    ViewUtils.moveActivity(this, AboutActivity::class.java)
                }
            }
            true
        }
    }

    private fun getListGame(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_game_name)
        val dataIcon = resources.obtainTypedArray(R.array.data_game_icon)
        val dataPublisher = resources.getStringArray(R.array.data_game_publisher)
        val dataRating = resources.getStringArray(R.array.data_game_rating)
        val dataReviewCount = resources.getStringArray(R.array.data_game_review_count)
        val dataDescription = resources.getStringArray(R.array.data_game_description)
        val dataTotalDownloads = resources.getIntArray(R.array.data_game_total_downloads)
        val dataMinimumOS = resources.getStringArray(R.array.data_game_minimum_os)
        val dataReleasedOn = resources.getStringArray(R.array.data_game_released_on)
        val dataLastUpdated = resources.getStringArray(R.array.data_game_last_updated)

        val listGame = ArrayList<Game>()
        for (i in dataName.indices) {
            val game = Game(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataPublisher[i],
                dataRating[i],
                dataReviewCount[i],
                dataDescription[i],
                dataTotalDownloads[i],
                dataMinimumOS[i],
                dataReleasedOn[i],
                dataLastUpdated[i]
            )
            listGame.add(game)
        }
        return listGame
    }
}