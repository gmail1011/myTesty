package test.sql.com.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class NetworkUtils {

	public final static int NONE = 0;
	public final static int WIFI = 1;
	public final static int MOBILE = 2;

	public static int getNetworkState(Context context) {
		try {
			ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			// GPRS
			State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
			if (state == State.CONNECTED || state == State.CONNECTING) {
				return MOBILE;
			}
                
			// WIFI
			state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
			if (state == State.CONNECTED || state == State.CONNECTING) {
				return WIFI;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  NONE;
	}

	public static boolean isWiFiState(Context context) {
		return WIFI == getNetworkState(context);
	}

	public static boolean isDisconnected(Context context) {
		return NONE == getNetworkState(context);
	}
}
