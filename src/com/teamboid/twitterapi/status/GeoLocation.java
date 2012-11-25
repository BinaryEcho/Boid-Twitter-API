package com.teamboid.twitterapi.status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Represents a coordinate with longitude and latitude.
 * @author Aidan Follestad
 */
public class GeoLocation implements Serializable {

	private static final long serialVersionUID = -4272081279675704341L;

	public GeoLocation(double _latitude, double _longitude) {
        latitude = _latitude;
        longitude = _longitude;
    }
    public GeoLocation(JSONObject json) throws JSONException {
        JSONArray coordinates = json.getJSONArray("coordinates");
        latitude = coordinates.getDouble(0);
        longitude = coordinates.getDouble(1);
    }

    private double latitude;
    private double longitude;

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getQueryString(char startChar) {
    	return startChar + "lat=" + getLatitude() + "&long=" + getLongitude();
    }
    
    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}
