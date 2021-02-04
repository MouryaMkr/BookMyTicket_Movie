package com.example.Movie.Model;

public class CastRequest
{
    private String name;
    private String role;

    public CastRequest()
    {

    }
    public CastRequest(String name, String role)
    {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CastRequest{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
