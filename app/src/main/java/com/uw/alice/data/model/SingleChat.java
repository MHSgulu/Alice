package com.uw.alice.data.model;

public class SingleChat {

    private String Content;
    private int sign;

    public SingleChat(String content, int sign) {
        Content = content;
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
