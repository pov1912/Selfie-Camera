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


}
