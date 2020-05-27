package com.mulganov.test_task.vrgsoft.model.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mulganov.test_task.vrgsoft.model.Model;
import com.mulganov.test_task.vrgsoft.present.Present;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

public class Element {
    @SerializedName("author_fullname")
    @Expose
    private String author_fullname;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("num_comments")
    @Expose
    private int num_comments;

    private Bitmap bitmap;

    public void reloadBitmap(final File dir, final Present present){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String file = dir + "/temp/bitmap/" + author_fullname + "/" + created + "bitmap.jpg";

                new File(dir + "/temp/bitmap/" + author_fullname + "/" + created + "/").mkdirs();

                if (new File(file).isFile()){
                    try { // Decode image size
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(new FileInputStream(file), null, o);
                        // The new size we want to scale to
                        int REQUIRED_SIZE = 70;
                        // Find the correct scale value. It should be the power of 2.
                        int scale = 1;
                        while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                                o.outHeight / scale / 2 >= REQUIRED_SIZE
                        ) {
                            scale *= 2;
                        }
                        // Decode with inSampleSize
                        BitmapFactory.Options o2 = new BitmapFactory.Options();
                        o2.inSampleSize = scale;
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, o2);

                        present.reloadView();
                    } catch (FileNotFoundException ex) {
                    }

                    System.out.println(thumbnail + " OK - isFile = true");

                    return;
                }

                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    URL url = new URL(thumbnail);
                    bis = new BufferedInputStream(url.openStream());
                    bos = new BufferedOutputStream(new FileOutputStream(new File(file)));

                    int c;
                    while ((c = bis.read()) != -1) {
                        bos.write(c);
                    }

                    System.out.println(thumbnail + " OK");

                    bos.close();
                    bis.close();

                    try { // Decode image size
                        BitmapFactory.Options o = new BitmapFactory.Options();
                        o.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(new FileInputStream(file), null, o);
                        // The new size we want to scale to
                        int REQUIRED_SIZE = 70;
                        // Find the correct scale value. It should be the power of 2.
                        int scale = 1;
                        while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                                o.outHeight / scale / 2 >= REQUIRED_SIZE
                        ) {
                            scale *= 2;
                        }
                        // Decode with inSampleSize
                        BitmapFactory.Options o2 = new BitmapFactory.Options();
                        o2.inSampleSize = scale;
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, o2);
                    } catch (FileNotFoundException ex) {
                    }


                } catch (Exception e) {
                    System.out.println(e.toString());
                    System.out.println(thumbnail + " NOT");
                }
            }
        }).start();


    }

    public String getAuthor_fullname() {
        return author_fullname;
    }

    public void setAuthor_fullname(String author_fullname) {
        this.author_fullname = author_fullname;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}