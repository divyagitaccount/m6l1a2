package com.example.m6l1a2;







import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add permission here which is needed
        //Permission string array to store all permissions that we need to take from user
        String[] PERMISSIONS = new String[]{
                Manifest.permission.SEND_SMS
        };

        //check if permission is given yet.
        if (!hasPermissions(MainActivity.this, PERMISSIONS)) {
            //if not then ask for permission with request code as unique code of permission
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS,1);
        }
    }

    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {
            //For each string in array check permission
            for (String permission: PERMISSIONS){
                // checks if permission is already given to the user
                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //When permission denied/accepted give a toast
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check if request code is granted.
        if (requestCode == 1) {
            //if permission is granted
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "SMS Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}