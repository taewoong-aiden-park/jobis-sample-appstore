package co.jobis.sample.appstore.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.jobis.sample.appstore.R
import co.jobis.sample.appstore.databinding.FragmentAppDetailBinding
import co.jobis.sample.appstore.ui.MainViewModel
import co.jobis.sample.appstore.ui.base.BaseFragment
import kotlin.math.roundToInt

class AppDetailFragment : BaseFragment<FragmentAppDetailBinding>() {

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        super.onStop()
        viewModel.selectedAppInfo.value = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModel()
    }

    private fun initView() {
        val data = viewModel.selectedAppInfo.value!!
        binding.detailContainer.addView(DetailInfoGeneral(requireContext(), data.trackName, data.description, data.artworkUrl512!!))
        binding.detailContainer.addView(DetailInfoGeneralSub(requireContext(), data.averageUserRating, data.userRatingCount, data.contentAdvisoryRating, data.artistName, data.languageCodesISO2A))
        binding.detailContainer.addView(DetailHeader(requireContext(), R.string.detail_header_new_feature))
        binding.detailContainer.addView(DetailNewFeature(requireContext(), data.version, data.releaseNotes))
        binding.detailContainer.addView(DetailHeader(requireContext(), R.string.detail_header_preview))
        binding.detailContainer.addView(DetailPreview(requireContext(), data.screenshotUrls, data.ipadScreenshotUrls))
        binding.detailContainer.addView(DetailDescription(requireContext(), data.description))
        binding.detailContainer.addView(DetailHeader(requireContext(), R.string.detail_header_rating))
        binding.detailContainer.addView(DetailHeader(requireContext(), R.string.detail_header_info))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_provider).toString(), data.artistName))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_size).toString(), "${(data.fileSizeBytes/100000f).roundToInt()/10f}MB"))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_category).toString(), data.genres[0]))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_reliability).toString(), "${data.minimumOsVersion}이상 필요", data.supportedDevices.toString().replace("[", "").replace("]", "")))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_language).toString(), data.languageCodesISO2A[0], data.languageCodesISO2A.toString().replace("[", "").replace("]", "")))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_age).toString(), data.contentAdvisoryRating, data.contentAdvisoryRating))
        binding.detailContainer.addView(DetailInfoText(requireContext(), getText(R.string.detail_info_copyright).toString(), data.artistName))
    }

    private fun observeViewModel() {

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAppDetailBinding {
        return FragmentAppDetailBinding.inflate(inflater, container, false)
    }
}