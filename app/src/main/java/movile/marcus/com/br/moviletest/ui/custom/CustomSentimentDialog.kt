package movile.marcus.com.br.moviletest.ui.custom

import android.app.Dialog
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import movile.marcus.com.br.moviletest.R
import movile.marcus.com.br.moviletest.model.data.SentimentalEnum

class CustomSentimentDialog : ConstraintLayout {

    private lateinit var sentimental: SentimentalEnum
    private lateinit var dialog: Dialog
    private lateinit var container: ConstraintLayout
    private lateinit var image: ImageView
    private lateinit var closeBtn: TextView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        dialog = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        dialog.setContentView(R.layout.custom_sentiment)
        container = dialog.findViewById(R.id.customSentimentContainer)
        image = dialog.findViewById(R.id.customSentimentImage)
        closeBtn = dialog.findViewById(R.id.customSentimentCloseBtn)
        initListners()
    }

    private fun initListners() {
        closeBtn.setOnClickListener { dialog.dismiss() }
    }

    fun show() {
        when (sentimental) {
            SentimentalEnum.SAD -> {
                image.background = ContextCompat.getDrawable(context, R.drawable.vector_sad)
                container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBlue))
            }
            SentimentalEnum.NEUTRAL -> {
                image.background = ContextCompat.getDrawable(context, R.drawable.vector_neutral)
                container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGrey))
            }
            else -> {
                image.background = ContextCompat.getDrawable(context, R.drawable.vector_happy)
                container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorYellow))
            }
        }

        dialog.show()
    }

    open class Builder(private val sentimental: SentimentalEnum, private val context: Context) {

        private lateinit var customSentimentDialog: CustomSentimentDialog

        fun build(): CustomSentimentDialog {
            customSentimentDialog = CustomSentimentDialog(context)
            customSentimentDialog.sentimental = sentimental
            return customSentimentDialog
        }
    }
}