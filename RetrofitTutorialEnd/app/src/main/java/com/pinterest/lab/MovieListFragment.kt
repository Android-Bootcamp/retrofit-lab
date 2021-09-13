package com.pinterest.lab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinterest.lab.databinding.FragmentMovieListBinding
import com.pinterest.lab.model.Movie
import com.pinterest.lab.rest.MovieApiService
import com.pinterest.lab.ui.MovieListAdapter
import com.pinterest.lab.ui.ProgramListAdapter
import com.pinterest.lab.util.getApiKey
import com.pinterest.lab.util.getRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: MovieListAdapter
    private var callDisposable: Disposable? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        callDisposable = getRetrofit()
            .create(MovieApiService::class.java)
            .getTopRatedMovies(getApiKey(), 1) // We can optionally paginate the list with this second parameter
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.let{ results ->
                    adapter.setData(results)
                    adapter.notifyDataSetChanged() // BAD but it works for a simple change.
                }
            }, {
                Log.e(FirstFragment::class.java.canonicalName, it.stackTraceToString())
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        callDisposable?.dispose()
    }
}