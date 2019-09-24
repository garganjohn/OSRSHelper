package org.pursuit.osrshelper.network;

public class GEModel {
    private GEItems item;

    public GEItems getItem() {
        return item;
    }

    public class GEItems {
        private String icon;
        private int id;

        public String getIcon() {
            return icon;
        }

        public int getId() {
            return id;
        }
    }

}
