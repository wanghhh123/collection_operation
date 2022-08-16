package com.yjxxt.pojo;

import java.util.Date;

public class Note {
    private Integer id;
    private String title;
    private String content;
    private Integer typeId;
    private Date pubTime;
    // 点击量
    private Integer click;


    public Note() {
    }

    public Note(Integer id, String title, String content, Integer typeId, Date pubTime,Integer click) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.typeId = typeId;
        this.pubTime = pubTime;
        this.click=click;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", typeId=" + typeId +
                ", pubTime=" + pubTime +
                ", click=" + click +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }
}
