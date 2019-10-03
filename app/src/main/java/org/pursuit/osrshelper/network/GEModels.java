package org.pursuit.osrshelper.network;

import java.util.List;

public class GEModels {
    public int total;
    public List<GEItems> items;

    public class GEItems {
        public Current current;
        public String icon_large;
        public int id;
        public String name;

        public class Current {
            public String trend;
            public String price;
        }

    }
}
