package com.example.translate

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById<ImageView>(R.id.imageView)
        image.setOnClickListener() { translate() }
    }

    private fun translate() {
        val container = image.parent as ViewGroup
        val containerH = container.height
        val imageH = image.height
        val animator = ObjectAnimator.ofFloat(image, View.TRANSLATION_Y, (containerH - imageH).toFloat())
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 1000
        animator.disableView(image)
        animator.start()
    }

    private fun ObjectAnimator.disableView(view: View) {
        addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) { view.isEnabled = false }
            override fun onAnimationEnd(animation: Animator?) { view.isEnabled = true}
        })
    }
}