package com.m2dl.bioshare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.widget.ImageView;


public class HomeActivity extends ActionBarActivity {

    private Button btnImport;
    private Button btnCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //On récupère le boutton qui permet de lancer la camera
        btnCamera = (Button)findViewById(R.id.btnCameraId);

        //On récupère le boutton qui permet d'importer une photo de l'album
        btnImport = (Button)findViewById(R.id.btnImportId);

        // Listener OnClick ==> btnCamera
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
                //intent.putExtra("Pseudo", "testPseudo");
                intent.putExtra("SourceType", "Camera");
                startActivity(intent);
            }
        });

        // Listener OnClick ==> btnImport
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
                //intent.putExtra("Pseudo", "testPseudo");
                intent.putExtra("SourceType", "Gallery");
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
}
