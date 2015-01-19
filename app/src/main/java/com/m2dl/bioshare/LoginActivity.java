package com.m2dl.bioshare;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.m2dl.bioshare.persistance.GlobalClass;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText username = null;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.usernametext);
        login = (Button) findViewById(R.id.loginbutton);
        login.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {
        if(!username.getText().toString().trim().isEmpty()) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("Pseudo", username.getText().toString());
            // Calling Application class (see application tag in AndroidManifest.xml)
            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //Set name and email in global/application context
            globalVariable.setPseudo(username.getText().toString());

            startActivity(intent);
        }else{
            Toast.makeText(getBaseContext(), "Entrez votre Pseudo", Toast.LENGTH_SHORT).show();
        }

    }
}
