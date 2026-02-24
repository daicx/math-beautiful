package com.skuu.protection;

/**
 * @author dcx
 * @description
 * @create 2025-12-01 18:39
 **/
public class DataResponse {
    private String data;
    private String source;

    public DataResponse() {}

    public DataResponse(String data, String source) {
        this.data = data;
        this.source = source;
    }

    // getters & setters
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
