package com.namrata.tflitedetect

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.tensorflow.lite.task.vision.detector.Detection
import java.util.*
import kotlin.math.max

class OverlayView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var results: List<Detection> = LinkedList<Detection>()
    private var boxPaint = Paint()
    private var bounds = Rect()
    private var textBackgroundPaint = Paint()
    private var textPaint = Paint()
    private var scaleFactor: Float = 1f
    private val customPaints = mutableMapOf<String, Int>()
    private val rnd = Random()

    init {
        // Initialize paints for drawing boxes, labels, and text backgrounds.
        initPaints()
    }

    // Clear all paints and invalidate the view to remove previous drawings.
    fun clear() {
        textPaint.reset()
        textBackgroundPaint.reset()
        boxPaint.reset()
        invalidate()
        initPaints()
    }

    // Initialize paint settings for boxes, text, and text backgrounds.
    private fun initPaints() {
        textBackgroundPaint.color = Color.BLACK
        textBackgroundPaint.style = Paint.Style.FILL
        textBackgroundPaint.textSize = 50f

        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 50f

        boxPaint.strokeWidth = 8F
        boxPaint.style = Paint.Style.STROKE
    }

    // Draw bounding boxes and labels on the canvas.
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        for (result in results) {
            val boundingBox = result.boundingBox

            val top = boundingBox.top * scaleFactor
            val bottom = boundingBox.bottom * scaleFactor
            val left = boundingBox.left * scaleFactor
            val right = boundingBox.right * scaleFactor
            val drawableRect = RectF(left, top, right, bottom)

            // Set the box color based on the label or a random color.
            canvas.drawRect(drawableRect,
                boxPaint.apply {
                    if (customPaints.containsKey(result.categories.first().label)) {
                        boxPaint.color = customPaints[result.categories.first().label]!!
                    } else {
                        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                        boxPaint.color = color
                        customPaints[result.categories.first().label] = color
                    }
                }
            )

            val drawableText =
                result.categories[0].label + " " +
                        String.format("%.2f", result.categories[0].score)

            textBackgroundPaint.getTextBounds(drawableText, 0, drawableText.length, bounds)
            val textWidth = bounds.width()
            val textHeight = bounds.height()

            // Draw a background for the label text.
            canvas.drawRect(
                left,
                top,
                left + textWidth + BOUNDING_RECT_TEXT_PADDING,
                top + textHeight + BOUNDING_RECT_TEXT_PADDING,
                textBackgroundPaint
            )

            // Draw the label text.
            canvas.drawText(drawableText, left, top + bounds.height(), textPaint)
        }
    }

    // Set the detection results and calculate the scale factor based on view size and image size.
    fun setResults(detectionResults: MutableList<Detection>, imageHeight: Int, imageWidth: Int) {
        results = detectionResults
        scaleFactor = max(width * 1f / imageWidth, height * 1f / imageHeight)
    }

    companion object {
        private const val BOUNDING_RECT_TEXT_PADDING = 8
    }
}
