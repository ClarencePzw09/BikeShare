package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BikeAdapter extends RecyclerView.Adapter<BikeHolder> {
    private List<Bike> mBikes;
    private List<Ride> mRides;
    private BikeVM bikeVM;
    private RideVM rideVM;

    public BikeAdapter(Context context,List<Bike> bikes){
        bikeVM = ViewModelProviders.of((FragmentActivity)context).get(BikeVM.class);
        rideVM = ViewModelProviders.of((FragmentActivity)context).get(RideVM.class);
        mBikes = bikes;

    }

    @Override
    public BikeHolder onCreateViewHolder(ViewGroup p , int v){

        LayoutInflater layoutInflater = LayoutInflater.from(p.getContext());
        final BikeHolder bikeholder = new BikeHolder(layoutInflater, p);

        return bikeholder;
    }
    @Override
    public void onBindViewHolder(BikeHolder holder , int i ){
        Bike bike = mBikes.get(i);
        mRides = rideVM.getRidesByBikeId(bike.getId());
        holder.bind(bike,mRides);
    }
    @Override
    public int getItemCount(){
        return mBikes.size();
    }
    public void setBikes(List<Bike> bikes){
        mBikes = bikes;
        notifyDataSetChanged();
    }
}
