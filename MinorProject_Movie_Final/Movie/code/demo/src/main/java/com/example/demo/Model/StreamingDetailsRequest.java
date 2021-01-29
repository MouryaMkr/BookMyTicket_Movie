package com.example.demo.Model;

public class StreamingDetailsRequest {


    private String link;

    public StreamingDetailsRequest() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "StreamingDetailsRequest{" +
                "link='" + link + '\'' +
                '}';
    }
}
