package com.example.multiscreen.FileUpload;

public class Upload {
    private String name;
    private String url;

    public Upload() {

    }

    public Upload(String name, String url) {
        if(name.trim().equals("")) {
            name = "No Name";
        }
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
