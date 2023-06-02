package com.example.tugaspas_zidane;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodModel implements Parcelable {

    private String strSport;
    private String strLeague;
    private String strCurrentSeason;
    private String dateFirstEvent;
    private String strGender;
    private String strCountry;
    private String strDescriptionEN;
    private String strBadge;
    private String strLogo;
    private String strTrophy;

    protected FoodModel(Parcel in) {
        strSport = in.readString();
        strLeague = in.readString();
        strBadge = in.readString();
        strCurrentSeason = in.readString();
        dateFirstEvent = in.readString();
        strGender = in.readString();
        strCountry = in.readString();
        strDescriptionEN = in.readString();
        strLogo = in.readString();
        strTrophy = in.readString();
    }

    FoodModel(){

    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrBadge() {
        return strBadge;
    }

    public void setStrBadge(String strBadge) {
        this.strBadge = strBadge;
    }

    public String getStrCurrentSeason() {
        return strCurrentSeason;
    }

    public void setStrCurrentSeason(String strCurrentSeason) {
        this.strCurrentSeason = strCurrentSeason;
    }

    public String getDateFirstEvent() {
        return dateFirstEvent;
    }

    public void setDateFirstEvent(String dateFirstEvent) {
        this.dateFirstEvent = dateFirstEvent;
    }

    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public void setStrCountry(String strCountry) {
        this.strCountry = strCountry;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrLogo() {
        return strLogo;
    }

    public void setStrLogo(String strLogo) {
        this.strLogo = strLogo;
    }

    public String getStrTrophy() {
        return strTrophy;
    }

    public void setStrTrophy(String strTrophy) {
        this.strTrophy = strTrophy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(strSport);
        parcel.writeString(strLeague);
        parcel.writeString(strBadge);
        parcel.writeString(strCurrentSeason);
        parcel.writeString(dateFirstEvent);
        parcel.writeString(strGender);
        parcel.writeString(strCountry);
        parcel.writeString(strDescriptionEN);
        parcel.writeString(strLogo);
        parcel.writeString(strTrophy);
    }
}
