package com.example.audionativec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.audionativec.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    File file = new File(Environment.getExternalStorageDirectory(), "Download/24.mp3");

    // Used to load the 'audionativec' library on application startup.
    static {
        System.loadLibrary("audionativec");
    }
    private static final int PICK_FILE_REQUEST_CODE = 122;
    private static final int READ_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkPermissionForRead();

        com.example.audionativec.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'audionativec' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
//    public native String audioFromJNI(String filePath);

    // Метод для вызова JNI-метода с именем файла
//    public void processFile(String filePath) {
//        String result = audioFromJNI(filePath);
//        // Обработка результата из JNI
//    }

    public void checkPermissionForRead() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Storage granted already", Toast.LENGTH_LONG).show();

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE);
            Toast.makeText(this, "Ask permission", Toast.LENGTH_LONG).show();
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null))
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(this, "1111", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "000", Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, READ_EXTERNAL_STORAGE);

        }
    }

    public void audio(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");  // Все типы файлов
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage granted onRequest", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "DEnied", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", getPackageName(), null))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
//
//        }
    }
}
