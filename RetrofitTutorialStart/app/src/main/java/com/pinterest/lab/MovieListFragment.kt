package com.pinterest.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinterest.lab.databinding.FragmentMovieListBinding
import com.pinterest.lab.ui.MovieListAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieListAdapter(mutableListOf())
        binding.rvMovieList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MovieListFragment.context)
            adapter = this@MovieListFragment.adapter
        }

        makeRequest()
    }

    private fun makeRequest() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}