package com.connite;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityItemData implements Parcelable {
    private String placeId;
    private String name;
    private String description;
    private String namedLocation;
    private double latitude;
    private double longitude;
    private String photoReference;
    private int costLevel;

    public ActivityItemData(JSONObject object) throws JSONException {
        this.name = object.getString("title");
        this.placeId = object.getString("id");
        this.description = object.getString("description");
        this.namedLocation = object.getString("adrText");
        this.photoReference = object.getString("photo_reference");
        JSONObject innerObject = object.getJSONObject("adrCoordinate");
        this.latitude = innerObject.getDouble("lat");
        this.longitude = innerObject.getDouble("lng");
    }

    public ActivityItemData(String name, String description, String namedLocation, double latitude, double longitude, String imageUrl, String placeId) {
        this.name = name;
        this.description = description;
        this.namedLocation = namedLocation;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.imageUrl = imageUrl;
        this.placeId = placeId;
    }

    public ActivityItemData(String name, String description, String namedLocation, double latitude, double longitude, String imageUrl, String placeId, int costLevel) {
        this.name = name;
        this.description = description;
        this.namedLocation = namedLocation;
        this.latitude = latitude;
        this.longitude = longitude;
//        this.imageUrl = imageUrl;
        this.placeId = placeId;
        this.costLevel = costLevel;
    }

    protected ActivityItemData(Parcel in) {
        placeId = in.readString();
        name = in.readString();
        description = in.readString();
        namedLocation = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        costLevel = in.readInt();
    }

    public static final Creator<ActivityItemData> CREATOR = new Creator<ActivityItemData>() {
        @Override
        public ActivityItemData createFromParcel(Parcel in) {
            return new ActivityItemData(in);
        }

        @Override
        public ActivityItemData[] newArray(int size) {
            return new ActivityItemData[size];
        }
    };

    public String getPlaceId() {return placeId;}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNamedLocation() {
        return namedLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageUrl() {
        return "https://maps.googleapis.com/maps/api/place/photo?" + "photoReference=" + photoReference +  "&key=AIzaSyBqAcEs-fZ1sDsVuw0bZSJoyYkPzECDDWA";
    }

    public int getCostLevel() {
        return costLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(placeId);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(namedLocation);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(photoReference);
        parcel.writeInt(costLevel);
    }
}
