package com.weather.kidouchi.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.widget.Toast;

import com.weather.kidouchi.weatherapp.ui.MainActivity;

import java.util.function.Function;

public final class NetworkUtils {

	public static boolean hasInternetConnenction(final Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

	public static void checkNetworkRequest(final Context context, final Runnable onAvailable, final Runnable onLost) {
		final ConnectivityManager connectivityManager =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		final NetworkRequest networkRequest = new NetworkRequest.Builder()
				.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
				.build();

		connectivityManager.requestNetwork(networkRequest,
				new ConnectivityManager.NetworkCallback() {
					@Override
					public void onAvailable(Network network) {
						super.onAvailable(network);
						onAvailable.run();
					}

					@Override
					public void onLost(Network network) {
						super.onLost(network);
						onLost.run();
					}
				});
	}
}
