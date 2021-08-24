package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R

class DetailHeader(
    context: Context,
    textId: Int,
) : ConstraintLayout(context) {


    init {
        initView()
        findViewById<TextView>(R.id.headerText).text = context.getText(textId)
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_header, this, false)
        addView(view)
    }

}