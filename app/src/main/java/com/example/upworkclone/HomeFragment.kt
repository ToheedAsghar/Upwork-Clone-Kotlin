package com.example.upworkclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upworkclone.databinding.FragmentHomeBinding
import com.example.upworkclone.ui.home.adapters.JobsAdapter
import com.example.upworkclone.ui.home.models.Job

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var jobsAdapter: JobsAdapter
    private lateinit var featuredJobsAdapter: JobsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupRecyclerViews() {
        // Setup Recent Jobs RecyclerView
        jobsAdapter = JobsAdapter()
        binding.recentJobsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jobsAdapter
        }

        // Setup Featured Jobs RecyclerView
        featuredJobsAdapter = JobsAdapter(true)
        binding.featuredJobsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = featuredJobsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.recentJobs.observe(viewLifecycleOwner) { jobs ->
            jobsAdapter.submitList(jobs)
        }

        viewModel.featuredJobs.observe(viewLifecycleOwner) { jobs ->
            featuredJobsAdapter.submitList(jobs)
        }

        viewModel.userStats.observe(viewLifecycleOwner) { stats ->
            binding.apply {
                profileViewsText.text = stats.profileViews.toString()
                proposalsSentText.text = stats.proposalsSent.toString()
                earningsText.text = "$${stats.earnings}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}