package com.mulganov.test_task.vrgsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mulganov.test_task.vrgsoft.model.json.JE;
import com.mulganov.test_task.vrgsoft.model.json.JSONHelper;
import com.mulganov.test_task.vrgsoft.model.list.BoxAdapter;
import com.mulganov.test_task.vrgsoft.model.rest.Element;
import com.mulganov.test_task.vrgsoft.present.Present;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Present present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        present = new Present(this);

        registerOnClicks();

//        Привет, я тут пробывал через MVP делать, даже не знаю, понял я этот паттерн правильно или нет)
//        Сразу пишу что делал очень быстро, заняло это все примерно часа 2-3, по этому может не много криворукий код)
//        Я это... Пишу это все потому что донести лично к Вам что я тут ставил експерименты над кодом в попытках прокачать свой скилл.
//        Если что то в этом коде Вас смущает то я научусь писать лучше, просто я всегда кодил в соло, по этому могу не
//        понимать не которые моменты в архитектуре проги(в командной работе))
    }

    public void reloadList(ArrayList<Element> arrayList){
        final BoxAdapter adapter = new BoxAdapter(this, arrayList, present);

        findViewById(R.id.list_main_content).post(new Runnable() {
            @Override
            public void run() {
                ((ListView)findViewById(R.id.list_main_content)).setAdapter(adapter);

                findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
            }
        });
    }

    private void registerOnClicks() {
        findViewById(R.id.button_first).setOnClickListener(present.onClick::first);
        findViewById(R.id.button_last).setOnClickListener(present.onClick::last);
        findViewById(R.id.button_left).setOnClickListener(present.onClick::left);
        findViewById(R.id.button_right).setOnClickListener(present.onClick::right);
    }

    public void reloadNum(final int num){
        findViewById(R.id.text_number).post(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.text_number)).setText(num + "");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        File file = new File(getCacheDir() + "/temp/num.json");

        JE je = new JE();
        je.setNum(present.getNum());

        JSONHelper.Export(je, file);
    }

    @Override
    protected void onStop() {
        super.onStop();

        File file = new File(getCacheDir() + "/temp/num.json");

        JE je = new JE();
        je.setNum(present.getNum());

        JSONHelper.Export(je, file);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        File file = new File(getCacheDir() + "/temp/num.json");

        JE je = new JE();
        je.setNum(present.getNum());

        JSONHelper.Export(je, file);
    }
}
