package com.weather.kidouchi.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	public static boolean hasInternetConnenction(final Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

//	public static void checkNetworkHealth(final Intent inten) {
//		ConnectivityManager connectivityManager = (ConnectivityManager)
//				this.getSystemService(Context.CONNECTIVITY_SERVICE);
//		final NetworkRequest networkRequest = new NetworkRequest.Builder()
//				.build();
//
//		connectivityManager.requestNetwork(networkRequest, new PendingIntent(int));
//	}
}
