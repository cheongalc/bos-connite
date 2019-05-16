package com.connite;

import android.net.Uri;

import java.net.URI;

public class ActivityItemData {
    private String name;
    private String description;
    private String namedLocation;
    private long latitude;
    private long longitude;
    private Uri imageUri;

    public ActivityItemData(String name, String description, String namedLocation, long latitude, long longitude, Uri imageUri) {
        this.name = name;
        this.description = description;
        this.namedLocation = namedLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNamedLocation() {
        return namedLocation;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
