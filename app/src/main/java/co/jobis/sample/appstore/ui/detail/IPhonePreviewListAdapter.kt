package co.jobis.sample.appstore.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.databinding.ItemIphonePreviewBinding
import co.jobis.sample.appstore.ui.base.BaseViewHolder

class IPhonePreviewListAdapter(
    private var urlList: List<String> = arrayListOf()
) : RecyclerView.Adapter<BaseViewHolder<String>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val viewBinding = ItemIphonePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IPhonePreviewViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<String>, position: Int) {
        holder.bind(urlList[position])
    }

    override fun getItemCount(): Int = urlList.size

    inner class IPhonePreviewViewHolder(
        private val binding: ItemIphonePreviewBinding
    ) : BaseViewHolder<String>(binding.root) {
        override fun bind(item: String) {
            binding.iphonePreview.setImageURI(item)
        }
    }

}