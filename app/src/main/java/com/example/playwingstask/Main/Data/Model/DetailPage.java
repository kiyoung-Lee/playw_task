package com.example.playwingstask.Main.Data.Model;

import java.util.List;

/**
 * Created by KiyoungLee on 2017-12-30.
 */

public class DetailPage {

    private String title;
    private List<Document> documents;
    private String info;
    private String imageUrl;
    private int likecount;
    private int commentCount;
    private Company company;
    private String startDate;
    private String endDate;
    private long arrivalDate;
    private long departureDate;
    private int mainPrice;
    private String mainCityName;
    private String cityNames;
    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(long arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public long getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(long departureDate) {
        this.departureDate = departureDate;
    }

    public int getMainPrice() {
        return mainPrice;
    }

    public void setMainPrice(int mainPrice) {
        this.mainPrice = mainPrice;
    }

    public String getMainCityName() {
        return mainCityName;
    }

    public void setMainCityName(String mainCityName) {
        this.mainCityName = mainCityName;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
