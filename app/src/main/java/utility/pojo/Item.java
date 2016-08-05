package utility.pojo;

public class Item {
    private String title;
    private String description;
    private int colour;
    private int id;
    private String date;
    private long timestamp;
    private String itemcode;
    private String dataid;
    private boolean online;
    private boolean deletedLocal;

    public Item(String title, String description, int colour, String date, long timestamp, String code, String dataid){
        this.title = title;
        this.description = description;
        this.colour = colour;
        this.date = date;
        this.timestamp = timestamp;
        this.itemcode = code;
        this.dataid = dataid;

        online = false;
        deletedLocal = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getColour() {
        return colour;
    }

    public void setId(int id){this.id = id;}

    public String getDate(){return date;}

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isOnline() {
        return online;
    }

    public String getCode() {
        return itemcode;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isDeletedLocal() {
        return deletedLocal;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public void setDeletedLocal(boolean deletedLocal) {
        this.deletedLocal = deletedLocal;
    }
}
