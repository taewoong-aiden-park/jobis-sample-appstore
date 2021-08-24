package co.jobis.sample.appstore.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import co.jobis.sample.appstore.R
import co.jobis.sample.appstore.databinding.FragmentSearchBinding
import co.jobis.sample.appstore.hideKeyboard
import co.jobis.sample.appstore.ui.MainViewModel
import co.jobis.sample.appstore.ui.base.BaseFragment
import co.jobis.sample.appstore.ui.search.history.HistoryFragmentDirections
import co.jobis.sample.appstore.ui.search.result.ResultFragmentDirections

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.searchEditText.setOnEditorActionListener { view, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.searchEditText.text.toString().isNullOrBlank()) {
                    false
                } else {
                    hideKeyboard()
                    searchApps(binding.searchEditText.text.toString())
                    true
                }
            }
            false
        }
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("WoongTest", "beforeTextChanged ${p0} ${p1} ${p2} ${p3}")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, before: Int, after: Int) {
//                Log.d("WoongTest", "onTextChanged ${p0} ${p1} ${p2} ${p3}")
                if (after > 0) {
                    binding.removeTextButton.visibility = View.VISIBLE
                } else {
                    binding.removeTextButton.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("WoongTest", "afterTextChanged ${p0}")
            }

        })
        binding.removeTextButton.setOnClickListener {
            binding.searchEditText.text = null
        }
        binding.searchCancelButton.setOnClickListener {
            binding.searchEditText.text = null
            hideKeyboard()
            val navController = Navigation.findNavController(requireActivity(), R.id.searchNavHostFragment)
            if (navController.currentDestination?.label == "ResultFragment") {
                val action = ResultFragmentDirections.actionResultFragmentToHistoryFragment()
                navController.navigate(action)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.selectedHistory.observe(viewLifecycleOwner) {
            hideKeyboard()
            searchApps(it)
        }
    }

    private fun searchApps(input: String) {
        val newTerms = input.trim().replace(" ", "+")
        if (viewModel.typedTerms.value != newTerms) {
            viewModel.addHistory(input)
            viewModel.typedTerms.value = input.trim().replace(" ", "+")
        }
        val navController = Navigation.findNavController(requireActivity(), R.id.searchNavHostFragment)
        if (navController.currentDestination?.label == "HistoryFragment") {
            val action = HistoryFragmentDirections.actionHistoryFragmentToResultFragment(input)
            navController.navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }
}