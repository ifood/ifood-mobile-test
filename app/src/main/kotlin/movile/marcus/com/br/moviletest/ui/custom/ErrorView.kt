package movile.marcus.com.br.moviletest.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.error_view.view.*
import movile.marcus.com.br.moviletest.R

class ErrorView : ConstraintLayout {

    private lateinit var errorListener: ErrorListener
    private lateinit var retryBtn: Button
    private lateinit var text: TextView
    private lateinit var image: ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.error_view, this)
        text = view.findViewById(R.id.errorViewText)
        image = view.findViewById(R.id.errorViewImage)
        retryBtn = view.findViewById(R.id.errorViewRetryBtn)
    }

    fun noInternetError(errorListener: ErrorListener) {
        this.errorListener = errorListener
        errorViewText.text = resources.getString(R.string.text_no_internet)
        image.background = ContextCompat.getDrawable(context, R.drawable.vector_wifi_off)
        retryBtn.visibility = View.VISIBLE
        retryBtn.setOnClickListener {
            errorListener.retry()
        }
    }

    fun notFoundError() {
        errorViewText.text = resources.getString(R.string.text_not_found)
        image.background = ContextCompat.getDrawable(context, R.drawable.vector_not_found)
        retryBtn.visibility = View.GONE
    }

    interface ErrorListener {
        fun retry()
    }
}