package com.kdomain.kkruups.hellogridview;

<<<<<<< HEAD


=======
import android.animation.AnimatorSet;
>>>>>>> origin/master
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

=======
>>>>>>> origin/master
/**
 * Created by 22711973 on 8/31/2015.
 */
public class MovieBundle implements Parcelable {
    String LOG_TAG = "MovieBundle";
<<<<<<< HEAD
    Log log = new Log();
=======
    Log log;
>>>>>>> origin/master

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
<<<<<<< HEAD
    private String jsonMovieDataString;

    public MovieBundle(){

    }
=======
>>>>>>> origin/master

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

<<<<<<< HEAD
    public void setImageUrl(final String base_path, final String image_size, String poster_path ) {
=======
    public void setImageUrl(String base_path, String image_size, String poster_path ) {
>>>>>>> origin/master

        Uri.Builder urlBuilder = new Uri.Builder();
        this.image_url =  urlBuilder.scheme(URL_BUILDER_SCHEME).authority(base_path).appendPath(image_size).appendPath(poster_path).toString();
        log.d(LOG_TAG, "Setter-Image_URL: " + this.image_url);

<<<<<<< HEAD
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

    //AsynTask to handle JSON and Packaging Parcel
    public class FetchMovieDataTask extends AsyncTask<Void,Void,Void> {


//START *********************Weather Site JSON Data Scraping**********************

            private final String LOG_TAG = FetchMovieDataTask.class.getSimpleName();
            private final String MOVIE_RESULTS="results";
            private final String MOVIE_ID="id";
            private final String MOVIE_TITLE="original_title";
            private final String MOVIE_DESCRIPTION="overview";
            private final String MOVIE_RATING="vote_average";
            private final String MOVIE_RELEASE_DATE="release_date";
            private final String MOVIE_BASE_PATH="image.tmdb.org/t/p";
            private final String MOVIE_IMAGE_SIZE="w185";
            private final String MOVIE_IMAGE_PATH="poster_path";
            private       MovieBundle movieBundle;

        public FetchMovieDataTask(MovieBundle mBundle){
            this.movieBundle = mBundle;
        }

        @Override
            protected Void doInBackground(Void... params) {
                // These two need to be declared outside the try/catch
                // so that they can be closed in the finally block.


                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

                // Will contain the raw JSON response as a string.
                String movieDataJsonStr = null;
                final String MOVIE_URL="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=7e9340e27812b0e3dfdecd4c19f14a65";
                try

                {
                    // Construct the URL for the OpenWeatherMap query
                    // Possible parameters are avaiable at OWM's forecast API page, at
                    // http://openweathermap.org/API#forecast
                    URL url = new URL(MOVIE_URL);

                    // Create the request to OpenWeatherMap, and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    // Read the input stream into a String
                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        // Nothing to do.
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                    }

                    if (buffer.length() == 0) {
                        // Stream was empty.  No point in parsing.
                        return null;
                    }
                    String movieDataRawJsonStr = buffer.toString();
                    Log.d(LOG_TAG, movieDataRawJsonStr);


                    JSONObject jsonRootObject = new JSONObject(movieDataRawJsonStr);
                    JSONArray jsonMovieDataArray = jsonRootObject.optJSONArray(MOVIE_RESULTS);
                    int i;
                    for (i = 0; i < jsonMovieDataArray.length(); i++){

                      JSONObject jsonMovieDataElement = jsonMovieDataArray.getJSONObject(i);
                      movieBundle.setId(Integer.parseInt(jsonMovieDataElement.optString(MOVIE_ID)));
                      movieBundle.setTitle(jsonMovieDataElement.optString(MOVIE_TITLE));
                      movieBundle.setReleaseDate(jsonMovieDataElement.optString(MOVIE_RELEASE_DATE));
                      movieBundle.setDescription(jsonMovieDataElement.optString(MOVIE_DESCRIPTION));
                      movieBundle.setImageUrl(MOVIE_BASE_PATH, MOVIE_IMAGE_SIZE, jsonMovieDataElement.optString(MOVIE_IMAGE_PATH));

                    }
                    log.d(LOG_TAG, "MovieData: " + movieBundle.toString());

                }catch(JSONException e){
                    Log.e(LOG_TAG, "Error ", e);

                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error ", e);
                    // If the code didn't successfully get the weather data, there's no point in attemping
                    // to parse it.
                    return null;
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                            Log.e(LOG_TAG, "Error closing stream", e);
                        }
                    }
                }

                return null;

            }
        }
  //END
}


=======
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

public class FetchMovieDataTask extends AsyncTask<Void,Void,Void> {
    public class FetchWeatherTask extends AsyncTask<Void, Void, Void> {
//START *********************Weather Site JSON Data Scraping**********************

        private final String LOG_TAG = FetchMovieDataTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {
// These two need to be declared outside the try/catch
// so that they can be closed in the finally block.


            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String movieDataJsonStr = null;
            final String MOVIE_URL="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=7e9340e27812b0e3dfdecd4c19f14a65";
            try

            {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                URL url = new URL(MOVIE_URL);

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieDataJsonStr = buffer.toString();

                
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            return null;

        }
    }
//END
}



}
>>>>>>> origin/master
