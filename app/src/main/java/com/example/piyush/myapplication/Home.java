package com.example.piyush.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Home extends ActionBarActivity
{
    String headcategory[] = {"Luxury Dining","Chinese","Continental","Italian", "Cafe","North Indian"};
    int images[]={R.drawable.luxury,R.drawable.chinese,R.drawable.continental,R.drawable.italian,R.drawable.cafe,R.drawable.northindian};
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GridView grid=(GridView)findViewById(R.id.gridLayout);
        grid.setAdapter(new CustomAdapter(this, R.id.homegridviewitem, headcategory));
         this.context=this;
        setClickListener(grid);//custom function
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
    public class CustomAdapter extends ArrayAdapter<String>
    {
        String Values[];
        Context context;
        public CustomAdapter(Context context, int resource,String [] object)
        {
            super(context, resource, object);
            Values=object;
            this.context=context;
        }
        @Override
        public View getView(int pos, View view, ViewGroup parent)
        {
            DisplayMetrics metrics= new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout rowView=(RelativeLayout)inflater.inflate(R.layout.homegridviewitem, parent, false);
            TextView textView=(TextView)rowView.getChildAt(1);
            textView.setText(Values[pos]);
            ImageView imageView=(ImageView)rowView.getChildAt(0);
            imageView.setImageDrawable(getResources().getDrawable(images[pos]));
            RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)imageView.getLayoutParams();
            params.height=(int)(metrics.widthPixels*.48);
            if(pos%2!=0)
            {
                if(pos<=1)
                    params.setMargins(0, (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01));
                else
                     params.setMargins(0, 0, (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01));
                params.width=(int)(metrics.widthPixels*.49);
            }
            else
            {
                if(pos<=1)
                    params.setMargins((int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01));
                else
                    params.setMargins((int) (metrics.widthPixels * .01), 0, (int) (metrics.widthPixels * .01), (int) (metrics.widthPixels * .01));
                params.width=(int)(metrics.widthPixels*.48);
            }
            imageView.setLayoutParams(params);
            return rowView;
        }
    }
    protected  void setClickListener(GridView grid)
    {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
            {
                /*Shrink animation

                ImageView imageView=(ImageView)((RelativeLayout) view).getChildAt(0);
                view.setPadding(5,5,5,5);
                RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)imageView.getLayoutParams();
                params.width=imageView.getWidth()-10;
                params.height=imageView.getHeight()-10;
                imageView.setLayoutParams(params);*/

                TextView textView=(TextView)((RelativeLayout) view).getChildAt(1);
                Intent intent = new Intent(context,ListRestaurantActivity.class);
                intent.putExtra("title",textView.getText());
                startActivity(intent);
            }
        });
    }

}
