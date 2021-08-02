package com.pinterest.lab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.pinterest.lab.databinding.FragmentFirstBinding
import com.pinterest.lab.model.Movie
import com.pinterest.lab.rest.MovieApiService
import com.pinterest.lab.util.getApiKey
import com.pinterest.lab.util.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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

        // Make the API Call
        connect()
    }

    private fun connect() {
        callDisposable = getRetrofit().create(MovieApiService::class.java)
            .getMovie(603, getApiKey())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               populateUi(it)
            }, {
                Log.e(FirstFragment::class.java.canonicalName, it.stackTraceToString())
            })
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