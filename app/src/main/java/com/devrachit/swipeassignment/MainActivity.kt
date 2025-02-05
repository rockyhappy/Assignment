package com.devrachit.swipeassignment

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devrachit.swipeassignment.presentation.screens.homeScreen.HomeScreenViewModel
import com.devrachit.swipeassignment.utility.NotificationUtil
import com.devrachit.swipeassignment.utility.PermissionManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val notificationUtil = NotificationUtil(this)
        notificationUtil.createNotificationChannel()
        val permissionCheckers = PermissionManager()
        permissionCheckers.launchPermission(this, this)
        permissionCheckers.launchNotificationPermission(this, this)

        // show the splash screen exit animation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // goes up
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                ).apply {
                    duration = 600
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.also {
                    it.start()
                }
            }
        }
    }
}