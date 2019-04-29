package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RideAdapter extends RecyclerView.Adapter<RideHolder> {

    private List<Ride> mRides;
    private RideVM rideVM;
    private BikeVM bikeVM;

    public RideAdapter(Context context, List<Ride> rides){
        bikeVM = ViewModelProviders.of((FragmentActivity)context).get(BikeVM.class);
        rideVM = ViewModelProviders.of((FragmentActivity) context).get(RideVM.class);
        mRides=rides;
    }


    @Override
    public RideHolder onCreateViewHolder(ViewGroup p , int v){

        LayoutInflater layoutInflater = LayoutInflater.from(p.getContext());
        final RideHolder rideholder = new RideHolder(layoutInflater, p);
        rideholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = rideholder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    Ride ride = mRides.get(adapterPos);
                    int bike_id = ride.getBike_id();
                    rideVM.delete(ride);


                    Bike bike = bikeVM.getBike(bike_id);
                    bike.setMAvailable(true);
                    bikeVM.update(bike);
                    mRides.remove(adapterPos);
                    notifyDataSetChanged();
                }
            }
        });
        return rideholder;
    }

    @Override
    public void onBindViewHolder(RideHolder holder , int i ){
        Ride ride = mRides.get(i);
        holder.bind(ride);
    }

    @Override
    public int getItemCount(){
        return mRides.size();
    }
    public void setRides(List<Ride> rides){
        mRides = rides;
        notifyDataSetChanged();
    }
}
