package com.example.catviewer.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.catviewer.R
import com.example.catviewer.databinding.FragmentSplashBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashFragment : Fragment() {
    private val splashDelayMs = 3000
    private val hideHandler = Handler(Looper.myLooper()!!)
    private val hideRunnable = Runnable { hide() }

    private var _binding: FragmentSplashBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        delayedHide()
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun hide() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_splashFragment_to_listFragment)
    }

    private fun delayedHide() {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, splashDelayMs.toLong())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}