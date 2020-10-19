package models;

public class ObjectConfiguration {
    private String objectAPIname;
    private String type;
    private String readOnly;
    private int row;

    public ObjectConfiguration() {
    }

    public ObjectConfiguration(String objectAPIname, String type) {
        this.objectAPIname = objectAPIname;
        this.type = type;
    }

    public ObjectConfiguration(String objectAPIname, String type, String readOnly) {
        this.objectAPIname = objectAPIname;
        this.type = type;
        this.readOnly = readOnly;
    }

    public ObjectConfiguration(String objectAPIname, String type, String readOnly, int row) {
        this.objectAPIname = objectAPIname;
        this.type = type;
        this.readOnly = readOnly;
        this.row = row;
    }

    public String getObjectAPIname() {
        return objectAPIname;
    }

    public void setObjectAPIname(String objectAPIname) {
        this.objectAPIname = objectAPIname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}
