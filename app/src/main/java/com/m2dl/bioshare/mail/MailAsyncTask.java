package com.m2dl.bioshare.mail;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by mehdi on 17/01/2015.
 */
public class MailAsyncTask  extends AsyncTask<Void, Void, Void> {



   public MailAsyncTask()    {

        }

        // Executed on the UI thread before the
        // time taking task begins
        @Override
        public void onPreExecute() {
            super.onPreExecute();

        }

        // Executed on a special thread and all your
        // time taking tasks should be inside this method
        @Override
        public Void doInBackground(Void... arg0) {

/*
            try {
                Mail sender = new Mail("biodiversityshare@gmail.com", "masterdl01");
                sender.sendMail("This is Subject",
                        "This is Body",
                        "biodiversityshare@gmail.com",
                        "mohammedabbes@gmail.com");
                Log.e("SendMail","test");
            } catch (Exception e) {
                e.printStackTrace();


            }


*/
            return null;
        }

        // Executed on the UI thread after the
        // time taking process is completed
        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

