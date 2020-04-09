package com.uw.alice.data.model;

import java.io.Serializable;

public class Chat implements Serializable {

    private static final long serialVersionUID = -8145407082431406680L;


    /**
     * result : 0
     * content : 不知道是谁
     */

    private int result;
    private String content;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
