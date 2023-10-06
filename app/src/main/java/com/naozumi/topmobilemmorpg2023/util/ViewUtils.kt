package com.naozumi.topmobilemmorpg2023.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.naozumi.topmobilemmorpg2023.model.Game

object ViewUtils {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun setupFullScreen(activity: AppCompatActivity) {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        activity.supportActionBar?.hide()
    }

    fun moveActivity(context: Context, target: Class<*>, data: Game? = null, optionsBundle: Bundle? = null) {
        val moveIntent = Intent(context, target)
        data.let { moveIntent.putExtra("data", data) }
        if (optionsBundle != null) {
            context.startActivity(moveIntent, optionsBundle)
        } else {
            context.startActivity(moveIntent)
        }
    }

    fun moveActivityNoHistory(context: Context, target: Class<*>, data: String? = null, optionsBundle: Bundle? = null) {
        val moveIntent = Intent(context, target)
        moveIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        data.let { moveIntent.putExtra("data", data) }
        if (optionsBundle != null) {
            context.startActivity(moveIntent, optionsBundle)
        } else {
            context.startActivity(moveIntent)
        }
    }

    fun formatGameDownloads(downloads: Int): String {
        return when {
            downloads >= 50000000 -> "50M+"
            downloads >= 10000000 -> "10M+"
            downloads >= 5000000 -> "5M+"
            downloads >= 1000000 -> "1M+"
            else -> downloads.toString()
        }
    }

    fun formatGameDownloadsDetail(downloads: Int): String {
        val formattedDownloads = String.format("%,d", downloads)
        return "$formattedDownloads+ downloads"
    }

}