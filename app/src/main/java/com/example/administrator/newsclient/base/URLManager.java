package com.example.administrator.newsclient.base;

/**
 * Created by Administrator on 2017/6/28.
 */

public class URLManager {

    private final String[] channelId = new String[]{
            "T1348647909107",
            "T1348648037603",
            "T1348649580692",
            "T1348648756099",
            "T1348649079062",
            "T1348654060988",
    };

    public static String getUrl(String newsCategoryId) {
        return "http://c.m.163.com/nc/article/headline/" + newsCategoryId + "/0-20.html";
    }

    public static final String VideoUrl = //
            "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/0-20.html";

}
