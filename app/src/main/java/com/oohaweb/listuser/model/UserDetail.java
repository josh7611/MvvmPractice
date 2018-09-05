package com.oohaweb.listuser.model;

public class UserDetail {
    private final String name;
    private final String bio;
    private final String location;
    private final String blog;
    private final String login;
    private final String avatar_url;
    private final boolean site_admin;

    public UserDetail(String name, String bio, String location, String blog, String login, String avatar_url, boolean site_admin) {
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.blog = blog;
        this.login = login;
        this.avatar_url = avatar_url;
        this.site_admin = site_admin;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getBlog() {
        return blog;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public boolean isSite_admin() {
        return site_admin;
    }
}
