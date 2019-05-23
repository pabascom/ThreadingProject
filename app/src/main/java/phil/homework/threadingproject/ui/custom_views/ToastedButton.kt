package phil.homework.threadingproject.ui.custom_views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import phil.homework.threadingproject.R
import phil.homework.threadingproject.util.toast

class ToastedButton : AppCompatButton, View.OnClickListener {
    lateinit var listener: OnToastClickListener
    var message = "Default message"

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        setOnClickListener(this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToastedButton)
        message = typedArray.getString(R.styleable.ToastedButton_message)
        typedArray.recycle()
    }

    fun setOnToastClickListener(listener: OnToastClickListener){
        this.listener = listener
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onClick(v: View?) {
        context.toast(message)
        listener.onClick()
    }

    interface OnToastClickListener {
        fun onClick()
    }
}
