package com.example.administrator.shadowapplication.date;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Author : shadow
 * Desc :
 * Date :2018/4/3/003
 */

public class shadowSpan implements LineBackgroundSpan{
        /**
         * Default radius used
         */
        public static final float DEFAULT_RADIUS = 3;
        private final float radius;
        private final int color;

        /**
         * Create a span to draw a dot using default radius and color
         *
         * @see #shadowSpan(float, int)
         * @see #DEFAULT_RADIUS
         */
        public shadowSpan() {
            this.radius = DEFAULT_RADIUS;
            this.color = 0;
        }

        /**
         * Create a span to draw a dot using a specified color
         *
         * @param color color of the dot
         * @see #shadowSpan(float, int)
         * @see #DEFAULT_RADIUS
         */
        public shadowSpan(int color) {
            this.radius = DEFAULT_RADIUS;
            this.color = color;
        }

        /**
         * Create a span to draw a dot using a specified radius
         *
         * @param radius radius for the dot
         * @see #shadowSpan(float, int)
         */
        public shadowSpan(float radius) {
            this.radius = radius;
            this.color = 0;
        }

        /**
         * Create a span to draw a dot using a specified radius and color
         *
         * @param radius radius for the dot
         * @param color  color of the dot
         */
        public shadowSpan(float radius, int color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void drawBackground(
                Canvas canvas, Paint paint,
                int left, int right, int top, int baseline, int bottom,
                CharSequence charSequence,
                int start, int end, int lineNum
        ) {
            int oldColor = paint.getColor();
            if (color != 0) {
                paint.setColor(color);
            }
           canvas.drawCircle((left + right) / 2, (bottom + top)/2, right-left, paint);

            paint.setColor(oldColor);
        }


}
