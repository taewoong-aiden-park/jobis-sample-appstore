package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import co.jobis.sample.appstore.R
import com.facebook.drawee.view.SimpleDraweeView

class DetailInfoGeneral(
    context: Context,
    title: String,
    subTitle: String,
    imageUrl: String,
) : ConstraintLayout(context) {


    init {
        initView()
        findViewById<TextView>(R.id.appTitleTextView).text = title
        findViewById<TextView>(R.id.appSubTitleTextView).text = subTitle
        findViewById<SimpleDraweeView>(R.id.appIconImageView).setImageURI(imageUrl)
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_info_general, this, false)
        addView(view)
    }

}