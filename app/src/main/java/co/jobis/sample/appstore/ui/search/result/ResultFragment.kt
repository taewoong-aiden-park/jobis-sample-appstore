package co.jobis.sample.appstore.ui.search.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import co.jobis.sample.appstore.R
import co.jobis.sample.appstore.databinding.FragmentResultBinding
import co.jobis.sample.appstore.ui.MainViewModel
import co.jobis.sample.appstore.ui.base.BaseFragment


class ResultFragment : BaseFragment<FragmentResultBinding>() {

    private val args by navArgs<ResultFragmentArgs>()

    private val viewModel by activityViewModels<MainViewModel>()

    private val adapter by lazy { AppListAdapter(viewModel) }

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d("WoongTest", "args ${args.input}")
//        viewModel.getApps(args.input.trim().replace(" ", "+"))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.resultRecyclerView.layoutManager = layoutManager
        binding.resultRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.typedTerms.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) {
                viewModel.getApps(it)
            }
        }
        viewModel.appInfoList.observe(viewLifecycleOwner) {
            adapter.updateAppDataList(it)
        }
        viewModel.selectedAppInfo.observe(viewLifecycleOwner) {
            if (it != null) {
                val navController = Navigation.findNavController(requireActivity(), R.id.mainNavHostFragment)
                navController.navigate(R.id.action_searchFragment_to_appDetailFragment)
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResultBinding {
        return FragmentResultBinding.inflate(inflater, container, false)
    }
}