package com.example.catviewer.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.catviewer.MainActivity
import com.example.catviewer.MainActivityViewModel
import com.example.catviewer.R
import com.example.catviewer.databinding.FragmentListBinding

/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        viewModel = (requireActivity() as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CatEntityListAdapter()
        binding.list.adapter = adapter
        viewModel.list.observe(requireActivity()) {
            adapter.items = it
        }

        binding.fab.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_listFragment_to_insertionFragment)
        }
    }
}