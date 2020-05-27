package com.mulganov.test_task.vrgsoft.present;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.mulganov.test_task.vrgsoft.MainActivity;
import com.mulganov.test_task.vrgsoft.R;
import com.mulganov.test_task.vrgsoft.model.Model;
import com.mulganov.test_task.vrgsoft.model.json.JE;
import com.mulganov.test_task.vrgsoft.model.json.JSONHelper;
import com.mulganov.test_task.vrgsoft.model.rest.Element;

import java.io.File;
import java.util.ArrayList;

public class Present {

    public MainActivity activity;

    public final int number = 10;   // сколько елементов на одной странице
    private int num = 0;            // Текущея страница

    // Classes Presents
    public OnClick onClick;

    // Class model
    Model model;

    public Present(MainActivity mainActivity){
        activity = mainActivity;

        onClick = new OnClick(this);

        File file = new File(activity.getCacheDir() + "/temp/num.json");

        if (file.isFile()){
            JE je = JSONHelper.Import(file + "");
            try {
                System.out.println("Num: " + je.getNum());
                num = je.getNum();
            }catch (Exception ex){

            }
        }

        model = new Model(this);


    }

    public void reloadView(){
        ArrayList<Element> a = new ArrayList<>();

        for (int i = num * number; i < num * number + number; i++){
            try {
                a.add(model.array.get(i));
            }catch (Exception ex){
                break;
            }
        }

        activity.reloadList(a);
        activity.reloadNum(num);
    }

    public void addNum(){
        int max = (int)(model.array.size() / number + 0.5f);

        num++;

        if (num > max)
            num = max;

        reloadView();
    }

    public void minusNum(){
        num--;

        if (num < 0)
            num = 0;

        reloadView();
    }

    public void setNum(int n){
        int max = (int)(model.array.size() / number + 0.5f);

        if (n > max)
            n = max;

        num = n;
        reloadView();
    }

    public int getNum(){
        return num;
    }

    public void openPopup(Drawable bitmap) {
        LayoutInflater inflater = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_img_big, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        ((ImageView) popupView.findViewById(R.id.pImage)).setImageDrawable(bitmap);

        ((Button) popupView.findViewById(R.id.pButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView) popupView.findViewById(R.id.pImage)).setDrawingCacheEnabled(true);
                Bitmap b = ((ImageView) popupView.findViewById(R.id.pImage)).getDrawingCache();
                MediaStore.Images.Media.insertImage(activity.getContentResolver(), b,System.currentTimeMillis() + "", "Фото с редита");
            }
        });

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken

        popupWindow.showAtLocation(activity.findViewById(R.id.main), Gravity.CENTER,0, 0);

        popupWindow.setOnDismissListener(() -> activity.findViewById(R.id.back).setVisibility(View.INVISIBLE));

        activity.findViewById(R.id.back).setVisibility(View.VISIBLE);
    }
}
