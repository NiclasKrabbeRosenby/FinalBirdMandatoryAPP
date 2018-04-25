package net.simplifiedlearning.firebaseauth;

import java.io.Serializable;

/**
 * Created by krabb_000 on 18-04-2018.
 */

public class Birds implements Serializable {


    private int BirdID;
    private String Comment;
    private String Created;
    private int Id;
    private double Latitude;
    private double Longitude;
    private String Placename;
    private int Population;
    private String UserId;
    private String NameDanish;
    private String NameEnglish;

    public Birds(Integer birdID, String comment, String created, Integer id, double latitude, double longitude, String placename, Integer population, String userId, String nameDanish, String nameEnglish) {
        BirdID = birdID;
        Comment = comment;
        Created = created;
        Id = id;
        Latitude = latitude;
        Longitude = longitude;
        Placename = placename;
        Population = population;
        UserId = userId;
        NameDanish = nameDanish;
        NameEnglish = nameEnglish;
    }

    public Integer getBirdID() {
        return BirdID;
    }

    public String getComment() {
        return Comment;
    }

    public String getCreated() {
        return Created;
    }

    public Integer getId() {
        return Id;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public String getPlacename() {
        return Placename;
    }

    public Integer getPopulation() {
        return Population;
    }

    public String getUserId() {
        return UserId;
    }

    public String getNameDanish() {
        return NameDanish;
    }

    public String getNameEnglish() {
        return NameEnglish;
    }
}
