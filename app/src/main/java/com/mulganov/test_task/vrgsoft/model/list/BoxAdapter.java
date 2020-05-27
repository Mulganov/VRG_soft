package com.mulganov.test_task.vrgsoft.model.list;

import android.content.Context;
import android.graphics.pdf.PdfRenderer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mulganov.test_task.vrgsoft.R;
import com.mulganov.test_task.vrgsoft.model.rest.Element;
import com.mulganov.test_task.vrgsoft.present.Present;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BoxAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Element> objects;
    Present present;

    public BoxAdapter(Context context, ArrayList<Element> elements, Present present) {
        this.present = present;
        ctx = context;
        objects = elements;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.adapter_main, parent, false);
        }

        Element p = getProduct(position);

//        if (p.getBitmap() != null) {
            ((ImageView) view.findViewById(R.id.image_main)).setImageBitmap(p.getBitmap());
//        }

        view.findViewById(R.id.image_main).setOnClickListener(present.onClick::openPopup);

        ((TextView) view.findViewById(R.id.text_name)).setText("Author_fullname: " + p.getAuthor_fullname());

        Calendar dateEl = new GregorianCalendar();
        dateEl.setTimeInMillis(p.getCreated());

        Calendar date = new GregorianCalendar();

        float hour = date.get(Calendar.HOUR) - dateEl.get(Calendar.HOUR);

        ((TextView) view.findViewById(R.id.text_date)).setText("Date: " + hour + " hours ago");

        ((TextView) view.findViewById(R.id.text_number)).setText("Количество коментов: " + p.getNum_comments());


        return view;
    }

    // товар по позиции
    Element getProduct(int position) {
        return ((Element) getItem(position));
    }

}