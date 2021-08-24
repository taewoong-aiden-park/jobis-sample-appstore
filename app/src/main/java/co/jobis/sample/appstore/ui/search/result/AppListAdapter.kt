package co.jobis.sample.appstore.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.databinding.ItemAppBinding
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.ui.base.BaseViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class AppListAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<BaseViewHolder<AppInfo>>() {

    private var appDataList: MutableList<AppInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<AppInfo> {
        val viewBinding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<AppInfo>, position: Int) {
        holder.bind(appDataList[position])
    }

    override fun getItemCount(): Int = appDataList.size

    fun updateAppDataList(items: List<AppInfo>) {
        appDataList.clear()
        appDataList.addAll(items)
        notifyDataSetChanged()
    }

    inner class AppViewHolder(
        private val binding: ItemAppBinding
    ) : BaseViewHolder<AppInfo>(binding.root) {

        override fun bind(item: AppInfo) {
            binding.appTitleTextView.text = item.trackName
            binding.appSubTitleTextView.text = item.description
            binding.appIconImageView.setImageURI(item.artworkUrl100)
            binding.starRatingBar.rating = item.averageUserRatingForCurrentVersion.toFloat()
            val ratingCount = item.userRatingCount
            val ratingCountString = if (ratingCount < 1000) {
                ratingCount.toString()
            } else if (ratingCount < 10000) {
                "${(ratingCount / 100f).roundToInt() / 10f}천"
            } else {
                "${(ratingCount / 1000f).roundToInt() / 10f}만"
            }
            binding.ratingCountTextView.text = ratingCountString
            GlobalScope.launch(Dispatchers.Main) {
                val screenshotViews =
                    listOf(binding.firstThumbnail, binding.secondThumbnail, binding.thirdThumbnail)
                item.screenshotUrls.asFlow()
                    .zip(screenshotViews.asFlow()) { url, view ->
                        view.setImageURI(url)
                    }.collect()
            }
            binding.appItemContainer.setOnClickListener {
                onClickListener.onClickApp(item)
            }
        }
    }

    interface OnClickListener {
        fun onClickApp(item: AppInfo)
    }
}