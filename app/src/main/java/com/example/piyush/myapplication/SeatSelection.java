package com.example.piyush.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/*

    getSeats() function is called from GlobalLayoutListener

 */

public class SeatSelection extends ActionBarActivity
{
    int pos;
    private TextView Seat[]=new TextView[10];
    private TextView selectedseat=null;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        Toast.makeText(this,"Select a Table",Toast.LENGTH_SHORT).show();
        context=this;
        pos=getIntent().getExtras().getInt("position");
        setActionBar();
        setTable();
        setDateObjects();

    }
    protected void setActionBar()
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Select Table");
        /*actionBar.setCustomView(R.layout.actionbarseatselection);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE);*/
    }
    protected void setTable()
    {
        final LinearLayout child=(LinearLayout)findViewById(R.id.mainbookingchild);
        final RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)child.getLayoutParams();
        final RelativeLayout parent=(RelativeLayout)findViewById(R.id.mainbooking);
       // ImageView imageView=(ImageView)findViewById(R.id.ImageBack);
        ViewTreeObserver viewTreeObserver=parent.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int pheight = parent.getHeight();
                int ptopmargin = (int) (pheight * .2);
                //Toast.makeText(this, String.valueOf(ptopmargin), Toast.LENGTH_LONG).show();
                params.setMargins(0, ptopmargin, 0, 0);
                params.height = (int) (pheight * .3);
                child.setLayoutParams(params);
                getSeats();
            }
        });
    }
    public void tableSelect(View v)
    {
        TextView textView=(TextView)v;
        if(selectedseat==null)
        {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.tableselected));
        }
        else
        {
            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.tableselected));
            selectedseat.setBackgroundDrawable(getResources().getDrawable(R.drawable.tableempty));
        }
        if(selectedseat==textView)
            selectedseat=null;
        else
            selectedseat=textView;
    }
    public void setDateObjects()
    {
        LinearLayout datelayout=(LinearLayout)findViewById(R.id.datelayout);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min=c.get(Calendar.MINUTE);
        onDateSet(year,month,day);
        onTimeSet(hour,min);
        ///set time date listeners
        datelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment fragment = new DatePickerFragment();
                fragment.show(getFragmentManager(), "datetime");
            }
        });
        LinearLayout timelayout=(LinearLayout)findViewById(R.id.timelayout);
        timelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment fragment = new TimePickerFragment();
                fragment.show(getFragmentManager(), "datetime");
            }
        });
    }
    public void bookSeat(View v)
    {
        if(selectedseat==null)
        {
            Toast.makeText(this,"No Table Selected",Toast.LENGTH_SHORT).show();
            return;
        }
        int i,seatnumber=-1;
        for(i=0;i<10;i++)
        {
            if(selectedseat==Seat[i])
            {
                seatnumber=i+1;
                break;
            }
        }
        Intent intent = new Intent(this,PersonalDetails.class);
        TextView textdate=(TextView)findViewById(R.id.textdate);
        TextView texttime=(TextView)findViewById(R.id.textime);
        intent.putExtra("position",pos);
        intent.putExtra("date",textdate.getText());
        intent.putExtra("time",texttime.getText());
        intent.putExtra("seatnumber", seatnumber);
        intent.putExtra("NumberOfPeople", selectedseat.getText());
      //  Toast.makeText(this,String.valueOf(seatnumber),Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
    public void getSeats()
    {
        Seat[0]=(TextView)findViewById(R.id.imagetable1);
        Seat[1]=(TextView)findViewById(R.id.imagetable2);
        Seat[2]=(TextView)findViewById(R.id.imagetable3);
        Seat[3]=(TextView)findViewById(R.id.imagetable4);
        Seat[4]=(TextView)findViewById(R.id.imagetable5);
        Seat[5]=(TextView)findViewById(R.id.imagetable6);
        Seat[6]=(TextView)findViewById(R.id.imagetable7);
        Seat[7]=(TextView)findViewById(R.id.imagetable8);
        Seat[8]=(TextView)findViewById(R.id.imagetable9);
        Seat[9]=(TextView)findViewById(R.id.imagetable10);
    }
    public void onDateSet(int year, int monthnumber, int day)
    {
        TextView t=(TextView)this.findViewById(R.id.textdate);
        Calendar c=Calendar.getInstance();
        c.set(day, monthnumber, year);
        int weeknumber=c.get(Calendar.DAY_OF_WEEK);
        String  week[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        String  month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        t.setText(week[weeknumber] + " " + String.valueOf(day) + " " + month[monthnumber]);
    }
    public void onTimeSet(int hour, int min)
    {
        TextView t=(TextView)this.findViewById(R.id.textime);
        t.setText(String.valueOf(hour)+": "+String.valueOf(min+" pm"));
    }
}

