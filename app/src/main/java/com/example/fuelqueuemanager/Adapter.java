package com.example.fuelqueuemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Station;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    LayoutInflater inflater;
    List<Station> stations;
    public CardView layout;


    public Adapter(Context context, List<Station> stations){
        this.inflater = LayoutInflater.from(context);
        this.stations = stations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.station_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.locationName.setText(stations.get(position).getAddress());
        holder.stationName.setText(stations.get(position).getStationName());




    }

    @Override
    public int getItemCount() {

        return stations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView locationName,stationName;
        Button aroowBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            locationName = itemView.findViewById(R.id.locationName);
            stationName = itemView.findViewById(R.id.stationName);






//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(view.getContext(),StationDetails.class);
//                    intent.putExtra("name", stations.get(position).getAddress());
//                    view.getContext().startActivity(intent);
//
//                }
//            });
        }
    }
}
