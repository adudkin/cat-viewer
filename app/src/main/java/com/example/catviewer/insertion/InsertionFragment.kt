package com.example.catviewer.insertion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.catviewer.MainActivity
import com.example.catviewer.MainActivityViewModel
import com.example.catviewer.databinding.FragmentInsertionBinding

/**
 * A simple [Fragment] subclass.
 */
class InsertionFragment : Fragment() {

    lateinit var binding: FragmentInsertionBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertionBinding.inflate(inflater)
        viewModel = (requireActivity() as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.foundCatUrl.observe(requireActivity()) {
            Glide.with(view).load(it).into(binding.imageView2)
        }
        viewModel.refresh()

        binding.addCat.setOnClickListener {
            viewModel.insert()
        }

        binding.refreshCat.setOnClickListener {
            viewModel.refresh()
        }

        binding.back.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }
}