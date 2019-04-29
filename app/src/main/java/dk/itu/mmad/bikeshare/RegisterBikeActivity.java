package dk.itu.mmad.bikeshare;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegisterBikeActivity extends AppCompatActivity {
    private File mPhotoFile;
    private Button mRegisterBike;
    private TextView mBikeId;
    private TextView mBikeType;
    private TextView mBikePrice;
    private Button mPhotoButton;
    private ImageView mPhotoView;
    private TextView mBikeName;
    private TextView mMessage;


    private static final int REQUEST_PHOTO = 2;
    private String message = "";

    private BikeVM bikeVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_bike);

        final File fileDir = this.getFilesDir();

        bikeVM = ViewModelProviders.of(this).get(BikeVM.class);
        //Button
        mRegisterBike = (Button) findViewById(R.id.register_bike_button);
        mPhotoButton = (Button) findViewById(R.id.bike_photo_camera);

        //Texts
        mBikeId = (TextView) findViewById(R.id.bike_id);
        mBikeName = (TextView) findViewById(R.id.bike_name);
        mBikeType = (TextView) findViewById(R.id.bike_type);
        mBikePrice = (TextView) findViewById(R.id.bike_price);
        mMessage = (TextView) findViewById(R.id.register_bike_message);

        //View
        mPhotoView = (ImageView)findViewById(R.id.bike_photo);
        updatePhotoView();
        //capture image
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mPhotoFile = new File(fileDir, "IMG_bike_photo.jpg");
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(getPackageManager()) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = FileProvider.getUriForFile(getApplicationContext(),
                        "dk.itu.mmad.bikeshare.fileprovider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                List<ResolveInfo> cameraActivities = getApplicationContext().getPackageManager().queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo activity : cameraActivities) {
                    getApplicationContext().grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(captureImage, REQUEST_PHOTO);

            }

        });
        updatePhotoView();

        //view products click event
        mRegisterBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int bikeId = Integer.parseInt(mBikeId.getText().toString().trim());
                String bikeName = mBikeName.getText().toString().trim();
                String bikeType = mBikeType.getText().toString().trim();
                Double price = 0.0;
                try {
                    price = Double.parseDouble(mBikePrice.getText().toString().trim());
                }catch(NumberFormatException e){

                }


                if(bikeVM.getBike(bikeId)!= null){
                    message = "Such Bike already exists";
                }else{
                    Bike bike = new Bike(bikeId,bikeName,bikeType,price,toBitmap());
                    bikeVM.insert(bike);
                    message = "Successfully added";
                }



                //Reset Text Fields
                mBikePrice.setText("");
                mBikeType.setText("");
                mBikeName.setText("");
                mMessage.setText(message);
                mPhotoFile.delete();
            }

        });
    }
    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), this);
            mPhotoView.setImageBitmap(bitmap);
        }
    }
    private Bitmap toBitmap() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        }

        return PictureUtils.getScaledBitmap(mPhotoFile.getPath(), this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            Uri uri = FileProvider.getUriForFile(this,
                    "dk.itu.mmad.bikeshare.fileprovider",
                    mPhotoFile);
            this.revokeUriPermission(uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }
    }
}
