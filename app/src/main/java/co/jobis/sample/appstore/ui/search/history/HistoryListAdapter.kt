package co.jobis.sample.appstore.ui.search.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.databinding.ItemHistoryBinding
import co.jobis.sample.appstore.domain.entity.History
import co.jobis.sample.appstore.ui.base.BaseViewHolder

class HistoryListAdapter(
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<BaseViewHolder<History>>() {

    private var historyList: MutableList<History> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<History> {
        return HistoryViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<History>, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int = historyList.size

    inner class HistoryViewHolder(
        private val binding: ItemHistoryBinding
    ) : BaseViewHolder<History>(binding.root) {
        override fun bind(item: History) {
            binding.historyItem.text = item.terms
            binding.historyItem.setOnClickListener {
                onClickListener.onClickHistory(item.terms)
            }
        }
    }

    fun updateAppDataList(items: List<History>) {
        historyList.clear()
        historyList.addAll(items)
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClickHistory(item: String)
    }
}