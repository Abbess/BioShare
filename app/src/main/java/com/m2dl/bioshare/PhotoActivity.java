package com.m2dl.bioshare;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.m2dl.bioshare.mail.Mail;
import com.m2dl.bioshare.mail.MailAsyncTask;

import java.io.File;


public class PhotoActivity extends ActionBarActivity implements LocationListener{
    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int IMAGE_FROM_GALLERY = 1024;
    private String pseudo;
    private TextView pseudotext;

    private Button addInterestPointButton;
    private Button sendPhotoButton;

    private LocationManager locationManager;
    @Override
    public void onLocationChanged(Location location) {
        String str = "Latitude: "+location.getLatitude()+" \nLongitude: "+location.getLongitude();
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
        Log.e("dd",str);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("dd","lat"+" long");
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
        Log.e("dd","gps");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        if(getSourceType()==1){
            //setPseudoText();
            takePhoto();
        }else{
            //setPseudoText();
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, IMAGE_FROM_GALLERY);
        }

        addInterestPointButton=(Button)findViewById(R.id.addInterestPointButton);
        addInterestPointButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        getLocation();
                    }
                }
        );
        sendPhotoButton=(Button)findViewById(R.id.sendPhotoButon);
        sendPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Mail sender = new Mail("biodiversityshare@gmail.com", "masterdl01");
                            try {
                                sender.sendMail("This is Subject",
                                        "This is Body",
                                        "biodiversityshare@gmail.com",
                                        "mohammedabbes@gmail.com");
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                            }
                            Log.e("SendMail","test");
                        }
                    });
                    t.start();




            }
        });




        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		/*
		  Parameters :
		     First(provider)    :  the name of the provider with which to register
		     Second(minTime)    :  the minimum time interval for notifications, in milliseconds. This field is only used as a hint to conserve power, and actual time between location updates may be greater or lesser than this value.
		     Third(minDistance) :  the minimum distance interval for notifications, in meters
		     Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location) method will be called for each location update
        */

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,   // 3 sec
                10, this);

    }


    private int getSourceType(){
        Intent intent = getIntent();
        String type = intent.getStringExtra("SourceType");

        if(type.equals("Camera"))
            return 1;
        else if(type.equals("Gallery"))
            return 2;
        else
            return -1;
    }

    private void setPseudoText(){
        Intent intent = getIntent();
        pseudo = intent.getStringExtra("Pseudo");
        pseudotext= (TextView)findViewById(R.id.pseudo);
        pseudotext.setText("Pseudo: "+pseudo);
    }

    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
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

    public void getLocation(){


        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!= null){
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.e("dd","lat"+longitude+" long"+latitude);
    }
    else
            Log.e("nukk","nulll");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //Si l'activité était une prise de photo
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) findViewById(R.id.imageViewPhoto);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap=null;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }

                }
                break;
            case IMAGE_FROM_GALLERY:
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);

                    ImageView imageView = (ImageView) findViewById(R.id.imageViewPhoto);

                    imageView.setImageBitmap(yourSelectedImage);

                    imageView.setOnTouchListener(new View.OnTouchListener(){
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            Log.i("X",String.valueOf(event.getX())+"");
                            Log.i("y",String.valueOf(event.getY())+"");
                            return true;
                        }
                    });

                }
                break;
        }
    }

}
