package com.example.hp.exercise11_webservice_and_database;

/**
 * Created by hp on 01/11/2017.
 */

public class news {
    private int id;
    private String service;
    private String box;
    private String link;
    private String image;
    private String rotitr;
    private String titr;
    private String lead;
    private int visitcount;
    private int priority;
    private String gdate;
    private String jdate;
    private Long dateticks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRotitr() {
        return rotitr;
    }

    public void setRotitr(String rotitr) {
        this.rotitr = rotitr;
    }

    public String getTitr() {
        return titr;
    }

    public void setTitr(String titr) {
        this.titr = titr;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public int getVisitcount() {
        return visitcount;
    }

    public void setVisitcount(int visitcount) {
        this.visitcount = visitcount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getGdate() {
        return gdate;
    }

    public void setGdate(String gdate) {
        this.gdate = gdate;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public Long getDateticks() {
        return dateticks;
    }

    public void setDateticks(Long dateticks) {
        this.dateticks = dateticks;
    }
}
