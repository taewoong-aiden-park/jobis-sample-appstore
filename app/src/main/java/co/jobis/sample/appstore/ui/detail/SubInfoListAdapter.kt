package co.jobis.sample.appstore.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.R
import co.jobis.sample.appstore.databinding.ItemSubInfoProviderBinding
import co.jobis.sample.appstore.databinding.ItemSubInfoRatingBinding
import co.jobis.sample.appstore.databinding.ItemSubInfoTextBinding
import co.jobis.sample.appstore.ui.base.BaseViewHolder
import kotlin.math.roundToInt

class SubInfoListAdapter(
    private val rating: Double,
    private val ratingCount: Int,
    private val minAge: String,
    private val provider: String,
    private val languageCodes: List<String>,
) : RecyclerView.Adapter<BaseViewHolder<Int>>() {

    private var subInfoTypeList: List<Int> = arrayListOf(
        TYPE_RATING, TYPE_AGE, TYPE_PROVIDER, TYPE_LANGUAGE
    )

    companion object {
        private const val TYPE_RATING = 1
        private const val TYPE_PROVIDER = 2
        private const val TYPE_AGE = 3
        private const val TYPE_LANGUAGE = 4
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Int> {
        return when(viewType) {
            TYPE_RATING -> RatingViewHolder(
                ItemSubInfoRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_PROVIDER -> ProviderViewHolder(
                ItemSubInfoProviderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            TYPE_AGE -> TextViewHolder(
                ItemSubInfoTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TYPE_LANGUAGE -> TextViewHolder(
                ItemSubInfoTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalAccessException("No Type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Int>, position: Int) {
        holder.bind(subInfoTypeList[position])
    }

    override fun getItemCount(): Int = subInfoTypeList.size

    override fun getItemViewType(position: Int): Int = subInfoTypeList[position]

    inner class RatingViewHolder(
        private val binding: ItemSubInfoRatingBinding
    ) : BaseViewHolder<Int>(binding.root) {
        override fun bind(item: Int) {
            val ratingCountString = if (ratingCount < 1000) {
                ratingCount.toString()
            } else if (ratingCount < 10000) {
                "${(ratingCount / 100f).roundToInt() / 10f}천"
            } else {
                "${(ratingCount / 1000f).roundToInt() / 10f}만"
            }
            binding.topText.text = "${ratingCountString}개의 평가"
            val rating = (rating.toFloat()*10f).roundToInt()/10f
            binding.middleText.text = rating.toString()
            binding.bottomRating.rating = rating
        }
    }

    inner class ProviderViewHolder(
        private val binding: ItemSubInfoProviderBinding
    ) : BaseViewHolder<Int>(binding.root) {
        override fun bind(item: Int) {
            binding.topText.text = binding.root.context.getText(R.string.detail_info_developer)
            binding.bottomText.text = provider
            binding.middleImage.setImageResource(R.drawable.baseline_account_circle_white_48)
        }
    }

    inner class TextViewHolder(
        private val binding: ItemSubInfoTextBinding
    ) : BaseViewHolder<Int>(binding.root) {
        override fun bind(item: Int) {
            if (item == TYPE_AGE) {
                binding.topText.text = binding.root.context.getText(R.string.detail_info_age_shorten)
                binding.middleText.text = minAge
                binding.bottomText.text = "세"
            } else {
                binding.topText.text = binding.root.context.getText(R.string.detail_info_language)
                binding.middleText.text = languageCodes[0]
                binding.bottomText.text = "+ ${languageCodes.size-1}개 언어"
            }
        }
    }

}