package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.Path;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.support.annotation.NonNull;

import static android.R.attr.enabled;
import static android.R.attr.path;


public class PickPhotoActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private int STORAGE_PERMISSION_CODE = 23;
    Bitmap photo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_photo);
        Button buttonLoadImage = (Button) findViewById(R.id.TakePhoto);
        Button finishLoadImage = (Button) findViewById(R.id.Finish);
        buttonLoadImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                if(isReadStorageAllowed()){
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    return;
                }
                else {
                    requestStoragePermission();
                    if (isReadStorageAllowed()) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        return;
                    }
                }
            }
        });

        finishLoadImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                if(photo == null){
                   finish();
                }
                else {
                    Intent intent = new Intent();
                    ByteArrayOutputStream bs;

                    // prevent large size issues
                    double i = 1;
                    Log.d("My message", "Size of image: " + photo.getWidth() + " x " + photo.getHeight());
                    do {
                        bs = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.JPEG, (int)(100.0/i), bs);
                        Log.d("My message", "BS.size: " + bs.size() + ", Quality: " + (int)(100/i));
                        i += .2;
                    } while (bs.size() >= 500000);

                    intent.putExtra("byteArray", bs.toByteArray());
                    setResult(RESULT_OK, intent);

                    finish();
                }
            }
        });
    }

    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int degreeRotation = -1;
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            photo = (Bitmap) data.getExtras().get("data");
            Uri path = data.getData();
            try {
                String[] orientationColumn = {MediaStore.Images.Media.ORIENTATION};
                Cursor cur = managedQuery(path, orientationColumn, null, null, null);
                if (cur != null && cur.moveToFirst()) {
                    degreeRotation = cur.getInt(cur.getColumnIndex(orientationColumn[0]));
                }
            } catch (Exception e) {}

            if (degreeRotation != 0) {
                Matrix matrix = new Matrix();
                matrix.preRotate(degreeRotation);
                photo = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
            }

            imageView.setImageBitmap(photo);
            Helper.img = photo;
            // findViewById(R.id.Finish).setEnabled(true);
        }

    }

}
