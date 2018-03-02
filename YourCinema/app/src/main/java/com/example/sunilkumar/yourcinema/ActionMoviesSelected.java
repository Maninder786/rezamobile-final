package com.example.sunilkumar.yourcinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ActionMoviesSelected extends AppCompatActivity {



    String actionurl;
    ProgressDialog pd;
TextView tvDirname,tvWriter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_movies_selected);

        Intent intent=this.getIntent();
        Bundle bd=intent.getBundleExtra("minfo");
        String mname=bd.getString("mname");
        String picname=bd.getString("picname");
        String desc=bd.getString("desc");
        float rating=bd.getFloat("rating");
        String rdate=bd.getString("rdate");
        String movieid=bd.getString("id");
         tvDirname=(TextView)findViewById(R.id.textViewDirector);
        tvWriter=(TextView)findViewById(R.id.textViewWriter);


       actionurl=" https://api.themoviedb.org/3/movie/"+movieid+"/credits?api_key=dd958aab0d5a41dd11a54e9791f5c00a";



        ImageView im=(ImageView)findViewById(R.id.imageView7);
        Picasso.with(ActionMoviesSelected.this)
                .load(picname)
                .placeholder(R.drawable.logoo)

                .into(im);

        TextView text1=(TextView)findViewById(R.id.textView17);
text1.setText(mname);

        TextView text2=(TextView)findViewById(R.id.textView25);
        text2.setText(desc);

        TextView text3=(TextView)findViewById(R.id.textView3);
           text3.setText("Release Date:"+movieid);

        RatingBar rat1=(RatingBar)findViewById(R.id.ratingBar);
        rat1.setRating(rating/2);

       new process().execute();


    }


    class process extends AsyncTask<Void,Void,Void>
    {
        String incomingresult;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(ActionMoviesSelected.this);
            pd.setMessage("plz wait---");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            incomingresult=getJson();


            return null;
        }

        public String getJson()
        {
            StringBuilder sb=new StringBuilder();
            try {
                URL url = new URL(actionurl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
                int code=con.getResponseCode();
                if(code==200)
                {

                    InputStream in=con.getInputStream();
                    Scanner obj=new Scanner(in);
                    while(obj.hasNext())
                    {
                        sb.append(obj.nextLine());
                    }

                }
                else
                {
                   // Toast.makeText(getContext(), "Response Code:" + code, Toast.LENGTH_SHORT).show();

                }

            }
            catch(Exception ex){}
            return(sb.toString());
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            try {

                JSONObject mainobj=new JSONObject(incomingresult);
                JSONArray crewsarray=mainobj.getJSONArray("crew");
                JSONObject directorobj=crewsarray.getJSONObject(0);

                String directorname=directorobj.getString("name");

                 JSONObject writerobj1=crewsarray.getJSONObject(1);

                String writername=writerobj1.getString("name");

                 //JSONObject writerobj2=crewsarray.getJSONObject(2);


               tvDirname.setText("Director: "+directorname);
                tvWriter.setText("Writer: "+writername);


            }
            catch(Exception ex){
                String err=ex.toString();
                Log.e("eee",err);

            }

        }
    }

}
