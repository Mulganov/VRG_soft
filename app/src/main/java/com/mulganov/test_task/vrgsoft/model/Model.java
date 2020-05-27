package com.mulganov.test_task.vrgsoft.model;


import com.mulganov.test_task.vrgsoft.model.rest.NetworkService;
import com.mulganov.test_task.vrgsoft.model.rest.Element;
import com.mulganov.test_task.vrgsoft.model.rest.Top;
import com.mulganov.test_task.vrgsoft.present.Present;

import java.io.File;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model {

    public Present present;

    public ArrayList<Element> array = new ArrayList<>();

    public Model(Present p){
        present = p;
        downTopFile(p.activity.getCacheDir());
    }

    private void downTopFile(final File dir) {
        Call<Top> a = NetworkService.getInstance()
                .getJSONApi()
                .getTop();

        a.enqueue(new Callback<Top>() {
            @Override
            public void onResponse(Call<Top> call, Response<Top> response) {
                Top top = response.body();

                for (Top.Data.Children children: top.getData().getChildren()){
                    array.add(children.getData());
                }

                for (Element el: array){
                    el.reloadBitmap(dir, present);
                }

                System.out.println("onResponse");
            }

            @Override
            public void onFailure(Call<Top> call, Throwable t) {

                System.out.println("onFailure");


                System.out.println(t.getLocalizedMessage());
            }
        });
    }

}
