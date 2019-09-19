
package com.example.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image_ {

    @SerializedName("xt_image")
    @Expose
    private String xtImage;
    @SerializedName("id")
    @Expose
    private String id;

    public String getXtImage() {
        return xtImage;
    }

    public void setXtImage(String xtImage) {
        this.xtImage = xtImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
