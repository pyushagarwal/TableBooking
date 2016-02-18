package com.example.piyush.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pos=getIntent().getExtras().getInt("position");
        ImageView imageView=(ImageView)findViewById(R.id.TopImage);
        imageView.setImageDrawable(getResources().getDrawable(SharedData.restaurantid[pos]));
        getSupportActionBar().setTitle(SharedData.restaurantname[pos]);
        TextView textView=(TextView)findViewById(R.id.textView9);
        textView.setText(SharedData.price[pos]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
    public void Reserve(View v)
    {
        Intent intent=new Intent(this,SeatSelection.class);
        intent.putExtra("position",pos);
        startActivity(intent);
    }
}
