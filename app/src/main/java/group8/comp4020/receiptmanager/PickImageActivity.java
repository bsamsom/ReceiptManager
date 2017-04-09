package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;


public class PickImageActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private int STORAGE_PERMISSION_CODE = 23;
    private Bitmap image;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_image);
        Button buttonLoadImage = (Button) findViewById(R.id.LoadPictures);
        Button finnishLoadImage = (Button) findViewById(R.id.Finish);
        buttonLoadImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                if(isReadStorageAllowed()){
                    Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    return;
                }
                else {
                    requestStoragePermission();
                    if (isReadStorageAllowed()) {
                        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                        return;
                    }
                }
            }
        });

        finnishLoadImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                if(image == null){
                    finish();
                }
                else {
                    Intent intent = new Intent();
                    ByteArrayOutputStream bs;

                    // prevent large size issues

                    double i = 1;
                    Log.d("My message", "Size of image: " + image.getWidth() + " x " + image.getHeight());
                    do {
                        bs = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.JPEG, (int)(50.0/i), bs);
                        Log.d("My message", "BS.size: " + bs.size() + ", Quality: " + (int)(50/i));
                        if (bs.size() > 1000000)
                            i += 1;
                        else
                            i += .3;
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

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            image = BitmapFactory.decodeFile(picturePath);

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(image);
            Helper.img = BitmapFactory.decodeFile(picturePath);



        }

    }
}
