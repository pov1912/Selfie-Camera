/**
 * 12/04/2013.
 * */
package com.pov.hd.selfiecamera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

/**
 * This class contain all functions for process with image.
 * */
public class ImageUtils {

	public static final String FOLDER_IMG = "Romantic_PhotoEditor";

	/**
	 * This method used for create file name from system date time.
	 * 
	 * @return {@link String} The name created.
	 * */
	public static String createFileName() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String s = df.format(new Date(System.currentTimeMillis())) + ".png";
		return s;
	}

	/**
	 * Delete file when not save.
	 * */
	public static void deleteFile(String filePath) {

		// Delete tmp file image.
		if (filePath != null) {
			final File f = new File(filePath);
			if (f.exists()) {
				f.delete();
			}
		}
	}

	public static boolean isExistFile(String sFileName) {
		String pathFolder = Environment.getExternalStorageDirectory()
				+ File.separator + FOLDER_IMG;
		File f = new File(pathFolder + File.separator + sFileName);
		return f.exists();
	}

	public static String createFileName(String sTmp, int type) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");

		String s = "";
		if (type == 0) {
			s = "img_" + df.format(new Date(System.currentTimeMillis())) + "_"
					+ sTmp + ".png";
		} else {
			s = "romantic_page_"
					+ df.format(new Date(System.currentTimeMillis())) + "_z_"
					+ sTmp + ".png";
		}

		return s;
	}

	/**
	 * Tag log.
	 * */
	private static final String TAG = ImageUtils.class.getSimpleName();

	public static int getPxFromDip(float valueDip, Context context) {

		// Get the screen's density scale.
		float scale = context.getResources().getDisplayMetrics().density;

		// Convert the dps to pixels, based on density scale.
		int mGestureThreshold = (int) (valueDip * scale + 0.5f);
		return mGestureThreshold;
	}

	/**
	 * This function used for create bitmap image from view normal.
	 * */
	public static Bitmap createImageFromFrags(View v) {

		Bitmap bmp = null;
		try {
			if (v != null) {
				bmp = Bitmap.createBitmap(v.getMeasuredWidth(),
						v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(bmp);
				v.draw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bmp;
	}

	/**
	 * This function used for create bitmap image from view with hide layout.
	 * */
	public static Bitmap createBitmapFromView(Activity context, View view) {

		Bitmap b = null;
		try {
			if (view != null) {

				DisplayMetrics displayMetrics = new DisplayMetrics();
				context.getWindowManager().getDefaultDisplay()
						.getMetrics(displayMetrics);
				view.measure(MeasureSpec.makeMeasureSpec(
						displayMetrics.widthPixels, MeasureSpec.EXACTLY),
						MeasureSpec.makeMeasureSpec(
								displayMetrics.heightPixels,
								MeasureSpec.EXACTLY));
				view.layout(0, 0, displayMetrics.widthPixels,
						displayMetrics.heightPixels);
				b = Bitmap.createBitmap(view.getMeasuredWidth(),
						view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(b);
				view.draw(canvas);
			}
		} catch (Exception e) {
			Log.e(TAG, "Save file error");
			return null;
		}
		return b;
	}

	public static String saveImageToLocalfile(String fileName, View v) {

		// Create folder if not exist.
		Bitmap bmp = null;
		final String pathFolder = Environment.getExternalStorageDirectory()
				+ File.separator;
		File f = new File(pathFolder);
		if (!f.exists()) {
			f.mkdirs();
		}
		String result = "";
		try {

			// Save output image to file.
			try {
				if (v != null) {
					bmp = Bitmap.createBitmap(v.getMeasuredWidth(),
							v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
					Canvas canvas = new Canvas(bmp);
					v.draw(canvas);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			FileOutputStream out = new FileOutputStream(pathFolder + fileName);
			bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.close();
			result = pathFolder + fileName;
			return result;
		} catch (IOException e) {
			return null;
		} finally {
			bmp.recycle();
			bmp = null;
		}
	}

}
