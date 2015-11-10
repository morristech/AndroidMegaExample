package custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import jr.timesaverexample.R;
import utils.Util;

public class CircleArrowBitmapGenerator {
    public static Bitmap generateBitmap(Context context, int degree, int colorCircle, int colorArrow) {
        int size = (int) Util.convertDpToPixel(50, context);

        // create a space to draw on: canvas
        Bitmap b = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.TRANSPARENT);
        paint.setAntiAlias(true);
//        canvas.drawPaint(paint);

        // draw circle
        paint.setColor(colorCircle);

        canvas.drawCircle(size / 2, size / 2, size / 2, paint);
//        ColorFilter filter = new LightingColorFilter(color, 1);
//        p.setColorFilter(filter);
        Bitmap arrowBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notif_arrow_up_white);
        int padding = (int) Util.convertDpToPixel(10, context);

        Matrix matrix = new Matrix();
        matrix.setRotate(degree, arrowBitmap.getWidth() / 2, arrowBitmap.getHeight() / 2);
        Bitmap arrowBitmapScaled = Bitmap.createBitmap(arrowBitmap, 0, 0, arrowBitmap.getWidth(), arrowBitmap.getHeight(), matrix, false);

        // This is because when we rotate by 45, the diagonal length is longer
        int adjustX = arrowBitmap.getWidth() / 2 - arrowBitmapScaled.getWidth() / 2;
        int adjustY = arrowBitmap.getHeight() / 2 - arrowBitmapScaled.getHeight() / 2;

        // change arrow color
        ColorFilter filter = new LightingColorFilter(colorArrow, 1);
        paint.setColorFilter(filter);

        canvas.drawBitmap(arrowBitmapScaled, padding + adjustX, padding + adjustY, paint);
        return b;
    }
}
