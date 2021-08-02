package com.pinterest.lab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.pinterest.lab.databinding.FragmentFirstBinding
import com.pinterest.lab.model.Movie
import io.reactivex.disposables.Disposable

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var callDisposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // UI Setup
        binding.btnMovieList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.btnProgramList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }

        // Make the API Call to fetch a movie
        connect()
    }

    private fun connect() {
        // TODO("Not yet implemented")
    }

    private fun populateUi(movie: Movie) {
        val ids = intArrayOf(
            R.id.txtTitle, R.id.txtReleaseDate, R.id.txtPoster,
            R.id.txtVote, R.id.txtOverview
        )
        val values = arrayOf(
            movie.title,
            movie.releaseDate,
            movie.posterPath,
            movie.voteAverage.toString(),
            movie.overview
        )

        for (i in ids.indices) {
            val tv = binding.root.findViewById<TextView>(ids[i])
            tv.text = values[i]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        callDisposable?.dispose()
    }
}