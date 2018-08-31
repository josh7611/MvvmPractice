package com.oohaweb.listuser.model;

public class User {
    private final String login;
    private final String avatar_url;
    private final boolean site_admin;

    public User(String firstName, String avatar_url, boolean site_admin) {
        this.login = firstName;
        this.avatar_url = avatar_url;
        this.site_admin = site_admin;
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
