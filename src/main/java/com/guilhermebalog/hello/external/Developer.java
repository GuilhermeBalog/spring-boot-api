package com.guilhermebalog.hello.external;

import java.io.Serializable;

public class Developer implements Serializable {
    private long id;
    private String login;
    private String bio;
    private int public_repos;

    @Override
    public String toString() {
        return "Developer {" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", bio='" + bio + '\'' +
                ", public_repos=" + public_repos +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }
}
