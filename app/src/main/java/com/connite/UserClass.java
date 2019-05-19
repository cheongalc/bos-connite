package com.connite;

public class UserClass {

    String name;
    String subText;
    String profileUrl;

    public UserClass (String name, String subText, String profieUrl) {
        this.name = name;
        this.subText = subText;
        this.profileUrl = profieUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getSubText() {
        return subText;
    }

}
