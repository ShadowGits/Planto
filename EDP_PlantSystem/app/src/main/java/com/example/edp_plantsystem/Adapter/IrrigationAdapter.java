package com.example.edp_plantsystem.Adapter;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.edp_plantsystem.IrrigationClass;
import com.example.edp_plantsystem.R;

import java.util.ArrayList;

public class IrrigationAdapter extends ArrayAdapter<IrrigationClass> {

    public IrrigationAdapter(Context context, ArrayList<IrrigationClass> dummyProjectData) {
        super(context, 0, dummyProjectData);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.irrigation_status_list_item, parent, false);
        }

        //get item is an inbuilt class which will extract the required info from the
        //arraylist at the required position
        IrrigationClass currentProject = getItem(position);

        //extract attributes from the position attribute
        String irrigationStatus = currentProject.getIrrigationStatus();
        String time = currentProject.getTime();
        int imageId = currentProject.getImageResource();

        TextView irrigationStatusView = (TextView) listItemView.findViewById(R.id.irrigation_status);
        irrigationStatusView.setText(irrigationStatus);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(time);

        ImageView emojiView = (ImageView) listItemView.findViewById(R.id.emoji);
        emojiView.setImageResource(imageId);


        return listItemView;


    }
}