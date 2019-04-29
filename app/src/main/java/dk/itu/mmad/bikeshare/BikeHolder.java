package dk.itu.mmad.bikeshare;


import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class BikeHolder extends RecyclerView.ViewHolder {
    private TextView mBikeNameView;
    private TextView mBikeTypeView;
    private TextView mBikeAvailableView;
    private TextView mBikePriceView;
    private ImageView mBikePhoto;
    private TextView mBikeIdView;
    private TextView mBikeRidesView;

    public BikeHolder(LayoutInflater inflater, ViewGroup parent){
        super(inflater.inflate(R.layout.list_item_bike,parent,false));
        mBikeIdView = itemView.findViewById(R.id.bike_id);
        mBikeNameView = itemView.findViewById(R.id.bike_name);
        mBikePriceView = itemView.findViewById(R.id.bike_price);
        mBikeTypeView = itemView.findViewById(R.id.bike_type);
        mBikePhoto = itemView.findViewById(R.id.bike_photo);
        mBikeAvailableView = itemView.findViewById(R.id.bike_available);
        mBikeRidesView = itemView.findViewById(R.id.bike_all_rides);
    }
    public void bind(Bike bike, List<Ride> rides){

        if(bike.isMAvailable()){
            mBikeAvailableView.setText("Bike is Available");
        }else{
            mBikeAvailableView.setText("Bike is Unavailable");
        }
        mBikeIdView.setText("Bike Code: " + String.valueOf(bike.getId()));
        mBikeNameView.setText("Bike Name: "+ bike.getMBikeName());
        mBikeTypeView.setText("Bike Type: " + bike.getMType());
        mBikePriceView.setText("Bike Price: " + String.valueOf(bike.getMPricePerHr()) + " $/hr");
        String ridesStr = "";
        for(int i = 0; i <rides.size();i++){
            Ride ride = rides.get(i);
            ridesStr += "Ride " + String.valueOf(i+1) + " Started at " + ride.getStartRide() + " and ended at " + ride.getEndRide() + "\n";
            if(!ride.getEndRide().equals("")){
                ridesStr += " Payment: " + ride.getCost()+ "\n";
            }
        }
        mBikeRidesView.setText(ridesStr);
        Bitmap bitmap = bike.getPicture();
        if(bitmap != null){
            mBikePhoto.setImageBitmap(bitmap);
        }

    }

}

