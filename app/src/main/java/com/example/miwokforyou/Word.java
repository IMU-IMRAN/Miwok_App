package com.example.miwokforyou;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudioResourceID;


    public Word(String DefaultTranslation, String MiwokTranslation, int AudioResource) {
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceID = AudioResource;

    }

    public Word(String MiwokTranslation, String DefaultTranslation, int ImageResourceId,int AudioResource) {
        this.mMiwokTranslation = MiwokTranslation;
        this.mDefaultTranslation = DefaultTranslation;
        this.mImageResourceId = ImageResourceId;
        this.mAudioResourceID = AudioResource;

    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getmAudioResourceID() {
        return mAudioResourceID;
    }
}

