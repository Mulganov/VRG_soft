package com.mulganov.test_task.vrgsoft.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Top {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("children")
        @Expose
        private ArrayList<Children> children;

        public ArrayList<Children> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Children> children) {
            this.children = children;
        }

        public class Children{
            @SerializedName("data")
            @Expose
            private Element data;

            public Element getData() {
                return data;
            }

            public void setData(Element data) {
                this.data = data;
            }
        }
    }
}
