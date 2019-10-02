package org.pursuit.osrshelper.network;
//TODO Update model class to account for more than 1 item being loaded
public class GEModel {
    public GEItems item;
    public PriceToday today;
    public String members;


    public class GEItems {
        public Current current;
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


    }

    public class PriceToday {
        public String trend;
        public float price;
    }

}
