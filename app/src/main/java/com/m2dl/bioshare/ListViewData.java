package com.m2dl.bioshare;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.m2dl.bioshare.parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListViewData extends ActionBarActivity {
    String listInterest="";
    int profondeur;
    ListView listview;
    TextView textViewInterest;
    Button button;
    private Parser parser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_data);
        parser=new Parser(this.getResources().openRawResource(R.raw.list));

        button = (Button) findViewById(R.id.buttonValiderInterrest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("pointInteret",textViewInterest.getText().toString());
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });
          listview = (ListView) findViewById(R.id.listview);
        textViewInterest = (TextView) findViewById(R.id.textViewInterest);
        textViewInterest.setMovementMethod(new ScrollingMovementMethod());
        final ArrayList<String> list = parser.getInitialElements();
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    int position, long id) {
                 final String item = (String) parent.getItemAtPosition(position);

                parent.animate().setDuration(200).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                modifyContent(item);
                                parent.animate().setDuration(200).alpha(1);

                            }
                        });
            }

        });

    }
int count;
    public void modifyContent(String item){

        profondeur++;
        Log.e("Item",item);
        if(profondeur==1){
            textViewInterest.setText(item);
            listInterest=item;

        }
        else {

            listInterest = listInterest + "\n";
            for(int i=0;i<profondeur;i++) {
                listInterest+="\t\t";
            }
            listInterest+=">>"+item;
            textViewInterest.setText(listInterest);
        }



        final ArrayList<String> list = parser.getElementsByParentName(item);
        final StableArrayAdapter adapter = new StableArrayAdapter(ListViewData.this,
                android.R.layout.simple_list_item_1, list);
        ListViewData.this.listview.setAdapter(adapter);
    }

    public String[] getFirstNode() {
//xml getfirstnode
        return new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS"};
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view_data, menu);
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
