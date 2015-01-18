package com.m2dl.bioshare;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListViewData extends ActionBarActivity {
    String listInterest="";
    int profondeur;
    String[] values;
    ListView listview;
    TextView textViewInterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_data);
          listview = (ListView) findViewById(R.id.listview);
        textViewInterest = (TextView) findViewById(R.id.textViewInterest);
        textViewInterest.setMovementMethod(new ScrollingMovementMethod());
        values = getFirstNode();
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                                    int position, long id) {
                 final String item = (String) parent.getItemAtPosition(position);

                parent.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                modifyContent(item);
                                parent.animate().setDuration(200).alpha(1);

                            }
                        });
            }

        });
        Button button = (Button) findViewById(R.id.buttonValiderInterrest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        //item xml.getSounodeItem---if ItemHasSousnode
        String value[]={"zeb"+count++,"9lwa"+count++};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < value.length; ++i) {
            list.add(value[i]);
        }
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
