package com.naozumi.topmobilemmorpg2023

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.naozumi.topmobilemmorpg2023.databinding.ActivityDetailBinding
import com.naozumi.topmobilemmorpg2023.model.Game
import com.naozumi.topmobilemmorpg2023.util.ViewUtils

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameBundle =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("data", Game::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("data")
            }
        if (gameBundle != null) {
            game = gameBundle
        }

        binding.apply {
            tvGameName.text = game.name
            tvGameRating.text = game.rating
            rbGameRating.rating = game.rating.toFloat()
            tvGameDescription.text = game.description
            tvGameTotalDownloads.text = ViewUtils.formatGameDownloadsDetail(game.totalDownloads)
            tvGameReviewCount.text = game.reviewCount
            tvGameMinimumOs.text = game.minimumOS
            tvGameReleasedOn.text = game.releasedOn
            tvGameLastUpdated.text = game.lastUpdated

            Glide.with(applicationContext)
                .load(game.icon)
                .placeholder(R.drawable.fumo)
                .error(R.drawable.fumo)
                .into(ivGameIcon)

            btnShare.setOnClickListener {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hey, check out ${game.name} made by ${game.publisher} on Play Store! it's one of the best mobile MMORPG out there!"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}