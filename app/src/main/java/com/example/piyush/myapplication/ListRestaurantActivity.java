package com.example.piyush.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListRestaurantActivity extends ActionBarActivity
{
    private ListView listview;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurant);
        getSupportActionBar().setTitle(getIntent().getExtras().getCharSequence("title"));
        this.context=this;
        String list[]= new String[5];
        listview=(ListView)findViewById(R.id.listView);
        MyAdapter adapter= new MyAdapter(this,R.layout.restaurantitemlayout,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View child, int pos, long id)
            {
                Intent intent= new Intent(context,DetailActivity.class);
                intent.putExtra("position",pos);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchmanager=(SearchManager)getSystemService(this.SEARCH_SERVICE);
        SearchView searchview=(SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchview.setSearchableInfo(searchmanager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        public MyAdapter(Context context, int resource, String objects[]) {
            super(context, resource, objects);
            this.context=context;
        }
        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout rowView = (RelativeLayout)inflater.inflate(R.layout.restaurantitemlayout, parent, false);
            TextView textViewname=(TextView)rowView.getChildAt(2);
            textViewname.setText(SharedData.restaurantname[pos]);
            TextView textprice=(TextView)rowView.getChildAt(3);
            textprice.setText(SharedData.price[pos]);
            ImageView imageview=(ImageView)rowView.getChildAt(0);
            //imageview.setImageDrawable(getResources().getDrawable(SharedData.restaurantid[pos]));
            return rowView;
        }
    }
}
