package com.mpsi.laurent.randomselect;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_select);
        select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_select, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        select();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nombre) {
            Intent coefficients = new Intent(this, Nombre.class);
            startActivity(coefficients);
        }
        return super.onOptionsItemSelected(item);
    }

    private void select() {
        List<String> list = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eleves)));
        Collections.shuffle(list);
        SharedPreferences nombrepref = PreferenceManager.getDefaultSharedPreferences(this);
        Integer nombre = Integer.parseInt(nombrepref.getString("nombre", "5"));
        Log.i("toto", nombre.toString());
        List<String> subList = new ArrayList<>(list.subList(0, nombre));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subList);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }
}
