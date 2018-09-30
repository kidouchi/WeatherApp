package com.weather.kidouchi.weatherapp.db.typeconverter;

public class WeatherConverter {

//	@TypeConverter
//	public static Weather weatherResponseToWeather(final String data) {
//		if (data == null) {
//			return null;
//		}
//
//		final Gson gson = new Gson();
//		final WeatherResponse weatherResponse = gson.fromJson(data, WeatherResponse.class);
//		final List<WeatherCondition> weatherConditions = weatherResponse.getWeatherConditions();
//
//		if (!weatherConditions.isEmpty()) {
//			final WeatherCondition weatherCondition = weatherConditions.get(0);
//			return new Weather(weatherResponse.getName(), weatherCondition.getWeatherCondition(), weatherCondition.getDescription());
//		} else {
//			return null;
//		}
//	}
}
