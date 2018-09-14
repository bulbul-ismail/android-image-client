package com.ismail.android.image_client;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ismail.android.image_client.async.GetImageTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.getImagesButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImages();
            }
        });
    }

    public void getImages(){

        EditText textfield = (EditText) findViewById(R.id.imageName);
        GetImageTask getImageTask = new GetImageTask(this, textfield.getText().toString());
        getImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void setImageViewField(Bitmap bitmap) {

        ImageView imageView = (ImageView) findViewById(R.id.imageField);
        imageView.setImageBitmap(bitmap);
    }
}
