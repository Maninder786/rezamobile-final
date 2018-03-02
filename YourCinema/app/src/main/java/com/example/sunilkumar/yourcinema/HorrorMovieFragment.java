package com.example.sunilkumar.yourcinema;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorrorMovieFragment extends Fragment {


    public HorrorMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_horror_movie, container, false);ListView lv=(ListView)view.findViewById(R.id.listview);

        horrorcusomadapater cs=new horrorcusomadapater(getContext());
        lv.setAdapter(cs);
               return view;

    }

}
