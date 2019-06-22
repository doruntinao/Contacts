package com.doruntinaosaj.contacts;


import java.io.Serializable;

public class Contacts implements Serializable {
    private String mImageUrl;
    private String mEmri;
    private String mMbiemri;
    private String mEmail;
    private int mId;

    public Contacts(String url, String emri, String mbiemri, String email, int id){
        mImageUrl = url;
        mEmri = emri;
        mMbiemri = mbiemri;
        mEmail = email;
        mId = id;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public String getmEmri(){
        return mEmri;
    }

    public String getmMbiemri(){
        return mMbiemri;
    }

    public String getmEmail(){
        return mEmail;
    }

    public int getmID(){
        return mId;
    }

}
