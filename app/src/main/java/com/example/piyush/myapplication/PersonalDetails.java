package com.example.piyush.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PersonalDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        Bundle data=getIntent().getExtras();
        Toast.makeText(this,String.valueOf(data.getInt("position")),Toast.LENGTH_SHORT).show();
        TextView texthotel=(TextView)findViewById(R.id.textrestaurant);
        TextView textdatetime=(TextView)findViewById(R.id.textdatetime);
        texthotel.setText(SharedData.restaurantname[data.getInt("position")]);
        textdatetime.setText(data.getCharSequence("date")+" "+data.getCharSequence("time"));
        data.getCharSequence("");

    }
}
