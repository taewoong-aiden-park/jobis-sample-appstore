package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.R

class DetailInfoGeneralSub(
    context: Context,
    rating: Double,
    ratingCount: Int,
    minAge: String,
    provider: String,
    languageCodes: List<String>,
) : ConstraintLayout(context) {





    init {
        initView()
        val subInfoRecyclerView = findViewById<RecyclerView>(R.id.subInfoRecyclerView)
        subInfoRecyclerView.adapter = SubInfoListAdapter(rating, ratingCount, minAge, provider, languageCodes)
        subInfoRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_info_general_sub, this, false)
        addView(view)
    }

}