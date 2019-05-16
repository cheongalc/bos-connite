package com.connite;

import android.net.Uri;

public class ActivityItemData {
    private String name;
    private String description;
    private String namedLocation;
    private long latitude;
    private long longitude;
    private String imageUrl;

    public ActivityItemData(String name, String description, String namedLocation, long latitude, long longitude, String imageUrl) {
        this.name = name;
        this.description = description;
        this.namedLocation = namedLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }
}
