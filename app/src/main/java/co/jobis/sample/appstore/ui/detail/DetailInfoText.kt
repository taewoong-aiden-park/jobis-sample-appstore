package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R

class DetailInfoText(
    context: Context,
    leftText: String,
    rightText: String,
    extraText: String? = null,
) : ConstraintLayout(context) {


    init {
        initView()

        findViewById<TextView>(R.id.leftText).text = leftText
        val rightTextView = findViewById<TextView>(R.id.rightText)
        rightTextView.text = rightText

        val expandButton = findViewById<ImageButton>(R.id.expandButton)

        if (!extraText.isNullOrBlank()) {
            val extraTextView = findViewById<TextView>(R.id.extraText)
            extraTextView.text = extraText
            expandButton.setOnClickListener {
                it.visibility = View.GONE
                rightTextView.visibility = View.GONE
                extraTextView.visibility = View.VISIBLE
            }
        } else {
            expandButton.visibility = View.GONE
        }
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_info_text, this, false)
        addView(view)
    }

}