package com.example.sample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sample.model.Image;
import com.example.sample.retrofit.RestClient;
import com.example.sample.retrofit.Utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermissions()) {
            getImageData();
        }
        recyclerView = findViewById(R.id.recyclerwebimage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private void getImageData() {

        RequestBody user_id = RequestBody.create(MediaType.parse("plain/text"), "108");
        RequestBody offset = RequestBody.create(MediaType.parse("plain/text"), "0");
        RequestBody type = RequestBody.create(MediaType.parse("plain/text"), "popular");

        Utils.showProgressDialog(MainActivity.this);

        RestClient.commingImage(user_id, offset, type, new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
               Utils.dismissProgressDialog();
                try {
                    ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this, response.body());
                    recyclerView.setAdapter(imageAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                Utils.dismissProgressDialog();

            }
        });
    }




    private boolean checkSelfPermissions() {
        return checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 12);
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        ////Log.e("checkSelfPermission " + permission, " " + requestCode);
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // //Log.e("onPermissionsResult " + requestCode, " " + Arrays.toString(permissions) + " " + Arrays.toString(grantResults));
        switch (requestCode) {
            case 12: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getImageData();
                } else {
                    finish();
                }
                break;
            }
        }
    }

}
