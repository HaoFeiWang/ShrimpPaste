package com.sppe.shrimppaste.data.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by WHF on 2017/11/5.
 */
@DatabaseTable(tableName = "tb_gank")
public class GankEntry {

    @DatabaseField(uniqueIndex = true)
    private String _id;
    private String createAt;
    @DatabaseField
    private String desc;
    @DatabaseField
    private String image;
    @DatabaseField
    private String publishedAt;
    @DatabaseField
    private String source;
    @DatabaseField
    private String type;
    @DatabaseField
    private String url;
    @DatabaseField
    private String used;
    @DatabaseField
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "GankEntry{" +
                "_id='" + _id + '\'' +
                ", createAt='" + createAt + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used='" + used + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}