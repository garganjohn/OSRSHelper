package org.pursuit.osrshelper.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GEModels {
    public int total;
    public List<GEItems> items;

    @SerializedName("item")
    public GEItems item;

    public class GEItems {
        public Current current;
        public Today today;

        public String icon;
        public String icon_large;
        public int id;
        public String type;
        public String typeIcon;
        public String name;
        public String description;


        public class Current {
            public String trend;
            public String price;
        }

        public class Today {
            public String trend;
            public String price;
        }

        public boolean members;

    }
}
