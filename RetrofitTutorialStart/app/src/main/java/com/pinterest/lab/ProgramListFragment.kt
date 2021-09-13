package com.pinterest.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinterest.lab.databinding.FragmentProgramListBinding
import com.pinterest.lab.ui.ProgramListAdapter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProgramListFragment : Fragment() {

    private var _binding: FragmentProgramListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stubData = getData()
        binding.rvPrograms.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ProgramListFragment.context)
            adapter = ProgramListAdapter(stubData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(): List<String> = listOf( "Computer Science — Ph.D.",
        "Computer Science — M.S.",
        "Informatics — Ph.D.",
        "Informatics — M.S.",
        "Networked Systems — Ph.D.",
        "Networked Systems — Ph.D.",
        "Software Engineering — Ph.D.",
        "Software Engineering — M.S.",
        "Statistics — Ph.D.",
        "Statistics — M.S.",
        "Master of Computer Science (MCS)",
        "Master of Software Engineering (MSWE)",
        "Master of Human-Computer Interaction and Design (MHCID)",
        "Master of Embedded & Cyber-physical Systems (MECPS)")
}
