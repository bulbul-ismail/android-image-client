package com.ismail.android.image_client.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.content.Context;
import android.util.Base64;
import android.widget.ImageView;

import com.ismail.android.image_client.MainActivity;
import com.ismail.android.image_client.util.HttpHandler;

import java.io.UnsupportedEncodingException;

/**
 * Created by ismail on 14.09.2018.
 */

public class GetImageTask extends AsyncTask {

    private Context context;
    private String imageName;
    private byte[] imageData=null;

    public GetImageTask(Context context, String imageName) {
        this.context = context;
        this.imageName = imageName;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        HttpHandler handler = new HttpHandler(context);
        String encodedImage = handler.makeServiceCall("http://192.168.1.100:8390/api/images/"+this.imageName);

        if(null != encodedImage){
            imageData = Base64.decode(encodedImage,Base64.NO_WRAP);
        }



        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if(null != imageData) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

            ((MainActivity) context).setImageViewField(bitmap);
        }
    }
}
