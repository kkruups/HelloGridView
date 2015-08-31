package com.kdomain.kkruups.hellogridview;

import android.animation.AnimatorSet;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by 22711973 on 8/31/2015.
 */
public class MovieBundle implements Parcelable {
    String LOG_TAG = "MovieBundle";
    Log log;

    //Movie Fields
    private int id;
    private String title;
    private String description;
    private String release_date;
    private String rating;
    private String data_query_url;
    private String image_url;
    private String base_path;
    private String image_size;
    private final String  URL_BUILDER_SCHEME="http";

    public MovieBundle(int id,
                       String title,
                       String description,
                       String release_date,
                       String rating,
                       String data_query_url,
                       String image_url
    ) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.rating = rating;
        this.data_query_url = data_query_url;
        this.image_url = image_url;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return this.release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        log.d(LOG_TAG, "Getter-Image_URL: " + this.image_url);
        return this.image_url;
    }

    public void setImageUrl(String base_path, String image_size, String poster_path ) {

        Uri.Builder urlBuilder = new Uri.Builder();
        this.image_url =  urlBuilder.scheme(URL_BUILDER_SCHEME).authority(base_path).appendPath(image_size).appendPath(poster_path).toString();
        log.d(LOG_TAG, "Setter-Image_URL: " + this.image_url);

     }

        public MovieBundle(Parcel in){
             String [] movieData = new String[6];


            this.id = in.readInt();
            in.readStringArray(movieData);
            this.title = movieData[0];
            this.description=movieData[1];
            this.release_date=movieData[2];
            this.rating=movieData[3];
            this.data_query_url=movieData[4];
            this.image_url=movieData[5];
        }

        @Override
        public int describeContents(){
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags){
            out.writeInt(this.id);
            out.writeStringArray(new String[]{
                                               this.title,
                                               this.description,
                                               this.release_date,
                                               this.rating,
                                               this.data_query_url,
                                               this.image_url
            });
        }

        public static final Parcelable.Creator Creator  = new Parcelable.Creator(){
            public MovieBundle createFromParcel(Parcel in){

                return new MovieBundle(in);
            }

            public MovieBundle [] newArray(int size){

                return new MovieBundle[size];
            }
        };


}
