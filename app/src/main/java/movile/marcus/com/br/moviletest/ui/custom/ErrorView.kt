package movile.marcus.com.br.moviletest.ui.custom

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import movile.marcus.com.br.moviletest.R

class ErrorView : ConstraintLayout {

    private lateinit var errorListener: ErrorListener
    private lateinit var retryBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        val view = View.inflate(context, R.layout.error_view, this)
        retryBtn = view.findViewById(R.id.errorViewRetryBtn)
    }

    fun setListeners(errorListener: ErrorListener) {
        this.errorListener = errorListener
        retryBtn.setOnClickListener {
            errorListener.retry()
        }
    }

    interface ErrorListener {
        fun retry()
    }
}