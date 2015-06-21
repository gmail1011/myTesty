package test.sql.com.myapplication.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {

	private static final boolean DEBUG = true;
	private static final String TAG = "HFY_APP";

	public static void i(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.i(TAG, text);
	}

	public static void d(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.d(TAG, text);
	}

	public static void w(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.w(TAG, text);
	}

	public static void e(String text) {
		if (DEBUG && !TextUtils.isEmpty(text))
			Log.e(TAG, text);
	}
}
