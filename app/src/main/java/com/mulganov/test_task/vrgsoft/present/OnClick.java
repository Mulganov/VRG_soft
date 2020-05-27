package com.mulganov.test_task.vrgsoft.present;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class OnClick {
    private Present present;

    public OnClick(Present p){
        present = p;
    }

    public void first(View v){
        present.setNum(0);
    }

    public void last(View v){
        present.setNum(Integer.MAX_VALUE);
    }

    public void left(View v){
        present.minusNum();
    }

    public void right(View v){
        present.addNum();
    }

    public void openPopup(View view) {
        present.openPopup(((ImageView)view).getDrawable());
    }
}
