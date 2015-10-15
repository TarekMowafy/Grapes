package com.test.grapes.Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarek on 10/15/15.
 */
public class Image {

    private double width;
    private double height;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The width
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * The width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *
     * @return
     * The height
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @param height
     * The height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
