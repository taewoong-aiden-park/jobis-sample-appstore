package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R

class DetailRating(
    context: Context,
) : ConstraintLayout(context) {


    init {
        initView()
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_rating, this, false)
        addView(view)
    }

}