package com.skuu.springdesign.adapter;

/**
 * 被适配的“遗留”接口（返回 String）。
 */
public class LegacyService {
    public String fetchData() {
        return "legacy-data";
    }
}
