package com.m2dl.bioshare;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.m2dl.bioshare.mail.Mail;
import com.m2dl.bioshare.mail.MailAsyncTask;


public class PhotoActivity extends ActionBarActivity implements LocationListener{
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

/*
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
           //     makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        String locationProvider = LocationManager.NETWORK_PROVIDER;
// Or use LocationManager.GPS_PROVIDER

        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);*/
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
}
