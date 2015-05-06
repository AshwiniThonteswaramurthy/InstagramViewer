package com.android.ashwini.instagramviewer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotoViewerActivity extends ActionBarActivity {

    private ArrayList<Photo> photoFeed;
    private ListView lvInstagramPhotos;
    private InstagramClient client;
    private InstagramPhotoAdapter instagramPhotoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
        //Get Data
        photoFeed = new ArrayList<>();
        lvInstagramPhotos = (ListView) findViewById(R.id.lvInstagramPhotos);
        instagramPhotoAdapter = new InstagramPhotoAdapter(this, photoFeed);
        lvInstagramPhotos.setAdapter(instagramPhotoAdapter);
        getPopularPhotos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getPopularPhotos() {
        client = new InstagramClient();
        client.getInstagramPhotoViewer(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    instagramPhotoAdapter.addAll(Photo.processAllImages(response.getJSONArray("data")));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
