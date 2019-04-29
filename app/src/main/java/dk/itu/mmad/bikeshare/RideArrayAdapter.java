//package dk.itu.mmad.bikeshare;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class RideArrayAdapter extends ArrayAdapter<Ride> {
//
//    private final Context mContext;
//    private final List<Ride> mValues;
//
//    public RideArrayAdapter(Context context, List<Ride> values){
//        super(context,R.layout.list_item_ride,values);
//        mContext = context;
//        mValues = values;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        final int _position = position;
//
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View rowView = inflater.inflate(R.layout.list_item_ride,parent
//        ,false);
//
//
//        Ride ride = mValues.get(position);
//
//        TextView bikeView = (TextView) rowView.findViewById(R.id.what_bike_ride);
//        bikeView.append(ride.getmBikeName());
//
//        TextView originView = (TextView) rowView.findViewById(R.id.start_ride);
//        originView.append(ride.getmStartRide());
//
//        TextView destinationView = (TextView) rowView.findViewById(R.id.end_ride);
//        destinationView.append(mValues.get(position).getmEndRide());
//
//        rowView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                mValues.remove(_position);
//                notifyDataSetChanged();
//            }
//        });
//
//        return rowView;
//    }
//
//}
