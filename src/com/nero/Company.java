package com.nero;

/**
 * Created by Nero on 18/7/28.
 */
public class Company {
    private String cname;
    private String location;

    @Override
    public String toString() {
        return "Company{" +
                "cname='" + cname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCname() {

        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}