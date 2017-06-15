package com.example.ahmed.freshpotato;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed on 11/18/2016.
 */

public class Aflam implements Parcelable{
    private String poster;
    private String tilte;
    private String photo;
    private String nobza;
    private String voteAvg;
    private String release_date;
    private String id;
public Aflam(){super();}

    protected Aflam(Parcel in) {
        poster = in.readString();
        tilte = in.readString();
        photo = in.readString();
        nobza = in.readString();
        voteAvg = in.readString();
        release_date=in.readString();
        id=in.readString();
    }
public String getId(){return this.id;}
    public void setId(String id){this.id=id;}
    public static final Creator<Aflam> CREATOR = new Creator<Aflam>() {
        @Override
        public Aflam createFromParcel(Parcel in) {
            return new Aflam(in);
        }

        @Override
        public Aflam[] newArray(int size) {
            return new Aflam[size];
        }
    };
public String getRelease_date(){return release_date;}
    public void setRelease_date(String release_date){this.release_date=release_date;}
    public String getNobza() {
        return nobza;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPoster() {
        return poster;
    }

    public String getTilte() {
        return tilte;
    }

    public String getVoteAvg() {
        return voteAvg;
    }

    public void setNobza(String nobza) {
        this.nobza = nobza;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public void setVoteAvg(String voteAvg) {
        this.voteAvg = voteAvg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPoster());
        dest.writeString(getTilte());
        dest.writeString(getPhoto());
        dest.writeString(getNobza());
        dest.writeString(getVoteAvg());
        dest.writeString(getRelease_date());
        dest.writeString(getId());
    }
}
