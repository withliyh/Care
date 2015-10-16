package com.nd.care.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/10/16.
 */
public class MeiZiTu {
    public String name;
    public String type;
    public Url url;
    public Link link;
    public List<Data> data;

    public MeiZiTu() {

    }

    public static class Url {
        public String hot;
        public String newest;
    }

    public static class Link {
        public String prev;
        public String self;
        public String next;
    }

    public static class Data {
        public String dlview;
        public String detail;
        public Image image;
        public Counts counts;

        public static class Image {
            public String small;
            public String big;
            public String original;
            public String diy;
        }

        public static class Counts {
            public String loved;
            public String share;
            public String down;
            public String puzzle;
        }
    }
}
