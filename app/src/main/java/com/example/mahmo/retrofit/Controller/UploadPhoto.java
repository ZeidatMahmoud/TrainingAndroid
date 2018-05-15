package com.example.mahmo.retrofit.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.example.mahmo.retrofit.Interface.API;
import com.example.mahmo.retrofit.R;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import yuku.ambilwarna.AmbilWarnaDialog;

public class UploadPhoto extends AppCompatActivity {
    private Button browse , upload ,choose ;
    private final static  int PICK_IMAGE = 1 ;
    private Uri currImageUR ;
    private MultipartBody.Part body ;
    private okhttp3.RequestBody name ;
    private RadioButton top,bottom , right ,left ,text ,LOGO ;
    private TextView texttop , textRight ,textBottom ,textLeft ;
    private ImageView image ;
    private EditText logo ;
    private  int mDefualtColor ,nDefualtColor;
    private ImageButton vector ,vector1 ,vector2,vector3,vector4,vector5 ,vector6,vector8,vector9 ;
    private TextView view ,viewText ;
    private Button set ;
    private File filer  ;
    private  byte[] bitmapdata ;
    private LinearLayout layout ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
//        browse = (Button)findViewById(R.id.browse) ;
        upload = (Button)findViewById(R.id.upload) ;
        view = (TextView)findViewById(R.id.viewlogo) ;
        viewText = (TextView)findViewById(R.id.viewtext) ;
        top  =(RadioButton)findViewById(R.id.rediotop) ;
        right = (RadioButton)findViewById(R.id.redioright) ;
        left = (RadioButton)findViewById(R.id.radioleft) ;
        bottom = (RadioButton)findViewById(R.id.radiobottom) ;
//        bottom.setChecked(true); //initialy
        texttop = (TextView)findViewById(R.id.top) ;
        textBottom = (TextView)findViewById(R.id.bottom) ;
        textLeft = (TextView)findViewById(R.id.left) ;
        textRight  =(TextView) findViewById(R.id.right) ;
        image = (ImageView) findViewById(R.id.imageView) ;
        logo = (EditText)findViewById(R.id.logo) ;
        choose = (Button)findViewById(R.id.choose) ;
        vector = (ImageButton) findViewById(R.id.vector);
        vector1 = (ImageButton) findViewById(R.id.vector1);
        vector2 = (ImageButton) findViewById(R.id.vector2);
        vector3 = (ImageButton) findViewById(R.id.vector3);
        vector4 = (ImageButton) findViewById(R.id.vector4);
        vector5 = (ImageButton) findViewById(R.id.vector5);
        vector6 = (ImageButton) findViewById(R.id.vector6);
        vector8 = (ImageButton) findViewById(R.id.vector8);
        vector9 = (ImageButton) findViewById(R.id.vector9);
        text = (RadioButton)findViewById(R.id.text) ;
        LOGO = (RadioButton)findViewById(R.id.radioLOGO) ;
        layout = (LinearLayout)findViewById(R.id.createimage) ;


        vector.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector));

            }
        });
        vector1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector1));
            }
        });

        vector2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                image.setImageDrawable(getDrawable(R.drawable.vector2));
            }
        });
        vector3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector3));
            }
        });
        vector4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector4));
            }
        });
        vector5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector5));
            }
        });
        vector6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector6));
            }
        });
        vector8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                image.setImageDrawable(getDrawable(R.drawable.vector8));
            }
        });
        vector9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
              
                image.setImageDrawable(getDrawable(R.drawable.vector9));
            }
        });

        logo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    if(top.isChecked()){
                        texttop.setText(s);
                        textBottom.setText("");
                        textRight.setText("");
                        textLeft.setText("");
                    }
                     if(bottom.isChecked()){
                        texttop.setText("");
                        textBottom.setText(s);
                        textRight.setText("");
                        textLeft.setText("");
                    }
                    if(right.isChecked()){
                        texttop.setText("");
                        textBottom.setText("");
                        textRight.setText(s);
                        textLeft.setText("");
                    }
                     if(left.isChecked()){
                        texttop.setText("");
                        textBottom.setText("");
                        textRight.setText("");
                        textLeft.setText(s);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.url)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create()) ;


        Retrofit retrofit= builder.build();
// create api instance
        final  API service= retrofit.create(API.class);

//        browse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//
//            }
//        });
        choose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                openColorPicker();

//

//                texttop.setTextColor(mDefualtColor);
//                textLeft.setTextColor(mDefualtColor);
//                textRight.setTextColor(mDefualtColor);
//                textBottom.setTextColor(mDefualtColor);

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String path = getRealPathFromURI(currImageUR) ;// "/mnt/sdcard/FileName.mp3"

              // File file = new File(path) ;

//
//                InputStream Stream = null;
//                try {
//                    Stream = getContentResolver().openInputStream(currImageUR);
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    byte[] inputData = getBytes(Stream);
                layout.setDrawingCacheEnabled(true);

                Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache()) ;
                layout.setDrawingCacheEnabled(false);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                bitmapdata = stream.toByteArray();

                    okhttp3.RequestBody requestB = okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/*"),bitmapdata);
//                    getContentResolver().getType(currImageUR);
                    body = MultipartBody.Part.createFormData("file", "testname.jpeg", requestB);
                    name = okhttp3.RequestBody.create(okhttp3.MediaType.parse("text/plain"), "upload test");
                    Call<okhttp3.ResponseBody> cal = service.uploadPhoto(body, name);
                    cal.enqueue(new Callback<okhttp3.ResponseBody>() {
                        @Override
                        public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                            Toast.makeText(UploadPhoto.this, "Done", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
                            Toast.makeText(UploadPhoto.this, "" + t.toString(), Toast.LENGTH_LONG).show();

                        }
                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }




        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String realPath ;
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data){
            currImageUR = data.getData();
            //  imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            image.setImageURI(currImageUR);





            // Do something with the bitmap


            // At the end remember to close the cursor or you will end with the RuntimeException!


        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker   = new AmbilWarnaDialog(this, mDefualtColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                if(LOGO.isChecked()){
                    mDefualtColor = color ;
                }
                else if(text.isChecked()){
                    nDefualtColor = color ;
                }

                view.setBackgroundColor(mDefualtColor);
                viewText.setBackgroundColor(nDefualtColor);
                image.setColorFilter(mDefualtColor);
                textBottom.setTextColor(nDefualtColor);
                textRight.setTextColor(nDefualtColor);
                texttop.setTextColor(nDefualtColor);
                textLeft.setTextColor(nDefualtColor);

            }
        });
        colorPicker.show();
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

