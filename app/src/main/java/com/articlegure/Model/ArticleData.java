package com.articlegure.Model;

/**
 * Created by HP on 05/13/18.
 */

public class ArticleData {
    private String imgURL, description, likes,id;
    public ArticleData(String id,String description, String imgURL,String likes){
        this.description=description;
        this.imgURL=imgURL;
        this.likes=likes;
        this.id=id;
    }
    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getLikes() {
        return likes;
    }

    public String getId() {
        return id;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}