package com.example.springwebsocket.DTO;

public class ResponceMessage {

    private String content;

    public ResponceMessage(){

}

    public ResponceMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
