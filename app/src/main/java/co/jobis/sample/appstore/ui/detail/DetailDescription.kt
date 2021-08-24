package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R

class DetailDescription(
    context: Context,
    description: String,
) : ConstraintLayout(context) {


    init {
        initView()
        val descriptionTextView = findViewById<TextView>(R.id.descriptionText)
        descriptionTextView.text = description
        findViewById<TextView>(R.id.descriptionExpandButton).setOnClickListener {
            it.visibility = View.GONE
            descriptionTextView.maxLines = Int.MAX_VALUE
        }
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_description, this, false)
        addView(view)
    }

}