package com.example.android.oaklandupdown;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceDetailsFragment extends Fragment {
    private int placeId;
    OaklandSQLiteOpenHelper mHelper;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {

            TextView title = (TextView) view.findViewById(R.id.location_name_textView);
            TextView description = (TextView) view.findViewById(R.id.description_textView);

            mHelper = OaklandSQLiteOpenHelper.getInstance(getActivity());

            Place place = mHelper.getPlace(placeId);
            title.setText(place.getName());
            description.setText(place.getDescription());
        }
    }

    public void setPlace(int id) {
        this.placeId = id;
    }

}
