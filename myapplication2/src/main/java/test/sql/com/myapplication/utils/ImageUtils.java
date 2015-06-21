package test.sql.com.myapplication.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Base64;

/**
 * @description 图像处理工具<br>
 *              <b>提供了缩放，旋转，圆角，压缩等基础功能</b>
 * @author wangpeng
 * @date 2014-12-15
 */
public class ImageUtils {

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap decodeResource(Resources res, int resId, int reqWidth,
			int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static Bitmap decodeStream(InputStream is, int reqWidth,
			int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(is, null, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(is, null, options);
	}

	public static Bitmap decodeByteArray(byte[] data, int offset, int length,
			int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, offset, length, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data, offset, length, options);
	}

	public static Bitmap decodeFile(String pathName, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}

	/**
	 * 缩放为指定大小
	 */
	public static Bitmap decodeToSpecifySize(Bitmap bitmap, int reqWidth,
			int reqHeight) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = (float) reqWidth / w;
		float scaleHeight = (float) reqHeight / h;
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	/**
	 * 缩放为指定比例
	 */
	public static Bitmap decodeToSpecifyRatio(Bitmap bitmap, int reqWidth,
			int reqHeight) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = (float) reqWidth / w;
		float scaleHeight = (float) reqHeight / h;
		float scaleSize = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
		matrix.postScale(scaleSize, scaleSize);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	/**
	 * 转换成圆角图片
	 */
	public static Bitmap roundedCornerImage(Bitmap resource, float roundSize) {
		final int color = 0xff424242;
		Bitmap output = Bitmap.createBitmap(resource.getWidth(),
				resource.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, resource.getWidth(),
				resource.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundSize, roundSize, paint);

		paint.setXfermode(new android.graphics.PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(resource, 0, 0, paint);
		return output;
	}

	/**
	 * 转换成圆形图片
	 */
	public static Bitmap roundedImage(Bitmap resource) {
		final int color = 0xff424242;
		Bitmap output = Bitmap.createBitmap(resource.getWidth(),
				resource.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, resource.getWidth(),
				resource.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawOval(rectF, paint);

		paint.setXfermode(new android.graphics.PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(resource, 0, 0, paint);
		return output;
	}

	/**
	 * 旋转图片
	 */
	public static Bitmap rotateImage(Bitmap resource, final int rotateDegree) {
		Matrix m = new Matrix();
		m.setRotate(rotateDegree, (float) resource.getWidth() / 2,
				(float) resource.getHeight() / 2);
		try {
			Bitmap bm1 = Bitmap.createBitmap(resource, 0, 0,
					resource.getWidth(), resource.getHeight(), m, true);
			return bm1;
		} catch (OutOfMemoryError ex) {
		}
		return null;
	}

	/**
	 * 质量压缩至100kb以下
	 */
	public static Bitmap compressImage(Bitmap resource) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		resource.compress(CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			options -= 10;
			resource.compress(CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		return BitmapFactory.decodeStream(bais, null, null);
	}

	/**
	 * @param bitmap
	 *            Bitmap
	 * @return String
	 */
	public static String bitmaptoString(Bitmap bitmap) {

		// 将Bitmap转换成字符串

		String string = null;

		ByteArrayOutputStream bStream = new ByteArrayOutputStream();

		bitmap.compress(CompressFormat.PNG, 100, bStream);

		byte[] bytes = bStream.toByteArray();

		string = Base64.encodeToString(bytes, Base64.DEFAULT);

		return string;

	}
	
	public static String bitmapToBase64(String imgPath, Bitmap bitmap) {
		if (imgPath != null && imgPath.length() > 0) {
			bitmap = BitmapFactory.decodeFile(imgPath);
		}
		if (bitmap == null) {
			// bitmap not found!!
		}
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			bitmap.compress(CompressFormat.PNG, 100, out);

			out.flush();
			out.close();

			byte[] imgBytes = out.toByteArray();
			return Base64.encodeToString(imgBytes, Base64.DEFAULT);
		} catch (Exception e) {
			return null;
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				return null;
			}
		}
	}

}
