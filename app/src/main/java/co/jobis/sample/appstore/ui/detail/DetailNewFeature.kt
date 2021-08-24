package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R

class DetailNewFeature(
    context: Context,
    version: String,
    newFeature: String,
) : ConstraintLayout(context) {


    init {
        initView()
        findViewById<TextView>(R.id.versionText).text = version
        findViewById<TextView>(R.id.newFeatureText).text = newFeature
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_new_feature, this, false)
        addView(view)
    }

}