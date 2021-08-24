package co.jobis.sample.appstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import co.jobis.sample.appstore.R

class DetailPreview(
    context: Context,
    iPhonePreviewUrlList: List<String>,
    iPadPreviewUrlList: List<String>,
) : ConstraintLayout(context) {


    init {
        initView()

        val iPhonePreviewRecyclerView = findViewById<RecyclerView>(R.id.iphonePreviewRecyclerView)
        iPhonePreviewRecyclerView.adapter = IPhonePreviewListAdapter(iPhonePreviewUrlList)
        iPhonePreviewRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val iPhonePreviewSnapHelper = LinearSnapHelper()
        iPhonePreviewSnapHelper.attachToRecyclerView(iPhonePreviewRecyclerView)

        val iPadPreviewRecyclerView = findViewById<RecyclerView>(R.id.ipadPreviewRecyclerView)
        iPadPreviewRecyclerView.adapter = IPadPreviewListAdapter(iPadPreviewUrlList)
        iPadPreviewRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val iPadPreviewSnapHelper = LinearSnapHelper()
        iPadPreviewSnapHelper.attachToRecyclerView(iPadPreviewRecyclerView)

        findViewById<ImageButton>(R.id.previewExpandButton).setOnClickListener {
            it.visibility = View.GONE
            findViewById<ViewGroup>(R.id.ipadPreviewContainer).visibility = View.VISIBLE
            findViewById<TextView>(R.id.iphonePreviewBottomText).text = context.getText(R.string.detail_preview_iphone)
        }
    }

    private fun initView() {
        val inflateService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflateService) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_detail_preview, this, false)
        addView(view)
    }

}