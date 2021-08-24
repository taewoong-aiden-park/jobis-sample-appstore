package co.jobis.sample.appstore.ui.search.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.jobis.sample.appstore.databinding.FragmentHistoryBinding
import co.jobis.sample.appstore.ui.MainViewModel
import co.jobis.sample.appstore.ui.base.BaseFragment

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy { HistoryListAdapter(viewModel) }

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.historyRecyclerView.layoutManager = layoutManager
        binding.historyRecyclerView.adapter = adapter

    }

    private fun observeViewModel() {
        viewModel.getHistories()
        viewModel.historyList.observe(viewLifecycleOwner) {
            adapter.updateAppDataList(it)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater, container, false)
    }
}