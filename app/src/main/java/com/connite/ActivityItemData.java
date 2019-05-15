package com.connite;

import java.net.URI;

public class ActivityItemData {
    private String name;
    private String description;
    private String namedLocation;
    private long latitude;
    private long longitude;
    private URI imageURI;

    public ActivityItemData(String name, String description, String namedLocation, long latitude, long longitude, URI imageURI) {
        this.name = name;
        this.description = description;
        this.namedLocation = namedLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageURI = imageURI;
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

    public URI getImageURI() {
        return imageURI;
    }
}
