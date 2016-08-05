package data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import utility.pojo.Item;

public class ItemDBHelper extends SQLiteOpenHelper implements ItemDbHelperInterface {

    public static final String DATABASE_NAME = "itemdatabase.db";
    private static final int DATABASE_VERSION = 9;
    public static final String ITEM_TABLE_NAME = "itemtable1";
    public static final String ITEM_ID= "_id";
    public static final String ITEM_TITLE = "title";
    public static final String ITEM_DESC = "desc";
    public static final String ITEM_COLOUR = "colour";
    public static final String ITEM_DATE = "date";
    public static final String ITEM_TIME = "time";
    public static final String ITEM_CODE = "code";
    public static final String ITEM_DATAID = "dataid";
    public static final String ITEM_ONLINEBOOL = "online";
    public static final String ITEM_DELETEDLOCALBOOL = "deletedlocal";



    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + ITEM_TABLE_NAME + "( " +
                ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ITEM_TITLE + " TEXT, " +
                ITEM_DESC + " TEXT, " +
                ITEM_COLOUR + " INTEGER, " +
                ITEM_DATE + " DATE, " +
                ITEM_TIME + " LONG, " +
                ITEM_CODE + " TEXT, " +
                ITEM_DATAID + " TEXT, " +
                ITEM_ONLINEBOOL + " INTEGER, " +
                ITEM_DELETEDLOCALBOOL + " INTEGER " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(db);
    }

    //Retrieves Local data
    //If selection is OnlineBool then list of items that aren't yet online are returned
    //If selection is DeletedLocal then list of items that have been 'deleted' locally are returned
    private List<Item> getData(String selection) {
        SQLiteDatabase db = getWritableDatabase();
        String where = ITEM_DELETEDLOCALBOOL + " = " + 0;

        if(selection == ITEM_ONLINEBOOL){
            where = ITEM_ONLINEBOOL + " = " + 0;
        }else if(selection == ITEM_DELETEDLOCALBOOL){
            where = ITEM_DELETEDLOCALBOOL + " = " + 1;
        }

        Cursor cursor = db.query(ITEM_TABLE_NAME,
                new String[]{ITEM_TITLE, ITEM_DESC, ITEM_COLOUR, ITEM_DATE,
                        ITEM_TIME, ITEM_CODE, ITEM_DATAID, ITEM_ONLINEBOOL, ITEM_DELETEDLOCALBOOL},
                where , null, null, null, null);

        ArrayList<Item> itemList = new ArrayList<>();

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            itemList.add(readCursor(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return itemList;
    }

    public List<Item> getLocalData(){
        return getData(null);
    }

    public List<Item> getToUpload(){
        return getData(ITEM_ONLINEBOOL);
    }

    public List<Item> getDeletedLocal(){
        return getData(ITEM_DELETEDLOCALBOOL);
    }

    @Override
    public List<Item> addItem(Item item , int onlineBool) {
        ContentValues values = new ContentValues();
        values.put(ITEM_TITLE, item.getTitle());
        values.put(ITEM_DESC,item.getDescription());
        values.put(ITEM_COLOUR, item.getColour());
        values.put(ITEM_DATE, item.getDate());
        values.put(ITEM_TIME, item.getTimestamp());
        values.put(ITEM_CODE, item.getCode());
        values.put(ITEM_DATAID, item.getDataid());
        values.put(ITEM_ONLINEBOOL, onlineBool);
        values.put(ITEM_DELETEDLOCALBOOL,0);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(ITEM_TABLE_NAME, null, values);

        return getLocalData();
    }

    @Override
    public void deleteItem(int position) {
        SQLiteDatabase db = getWritableDatabase();
        List<Integer> idList = getIdList();
        int itemId = idList.get(position);
        db.delete(ITEM_TABLE_NAME, ITEM_ID + "=" + itemId, null);
    }

    @Override
    public Item getItem(int position) {
        SQLiteDatabase db = getWritableDatabase();
        List<Integer> idList = getIdList();
        int itemId = idList.get(position);
        Cursor cursor = db.query(ITEM_TABLE_NAME,
                new String[]{ITEM_TITLE, ITEM_DESC, ITEM_COLOUR, ITEM_DATE,
                        ITEM_TIME, ITEM_CODE,ITEM_DATAID, ITEM_ONLINEBOOL, ITEM_DELETEDLOCALBOOL},
                ITEM_ID + "=" + itemId, null, null, null, null);
        cursor.moveToFirst();
        return readCursor(cursor);
    }

    @Override
    public void updateData(List<Item> itemList) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(ITEM_TABLE_NAME,null,null);

        for(Item item : itemList){
            item.setOnline(true);
            addItem(item,1);
        }
    }

    public List<Item> updateItem(String code, String choice) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        if(choice==ITEM_ONLINEBOOL){
            values.put(ITEM_ONLINEBOOL,1);
        }else if(choice==ITEM_DELETEDLOCALBOOL){
            values.put(ITEM_DELETEDLOCALBOOL,1);
        }

        String selection = ITEM_CODE + " = ?";
        String[] selectionArgs = {code};

        db.update(ITEM_TABLE_NAME, values, selection, selectionArgs);
        return getLocalData();
    }

    private Item readCursor(Cursor cursor){
        String title = cursor.getString(cursor.getColumnIndexOrThrow(ITEM_TITLE));
        String desc = cursor.getString(cursor.getColumnIndexOrThrow(ITEM_DESC));
        int colour = cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_COLOUR));
        String date = cursor.getString(cursor.getColumnIndex(ITEM_DATE));
        Long timestamp = cursor.getLong(cursor.getColumnIndexOrThrow(ITEM_TIME));
        String code = cursor.getString(cursor.getColumnIndexOrThrow(ITEM_CODE));
        String dataid = cursor.getString(cursor.getColumnIndexOrThrow(ITEM_DATAID));
        Boolean online = ((cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_ONLINEBOOL))==1));
        Boolean deletedLocal = ((cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_DELETEDLOCALBOOL))==1));

        Item item = new Item(title,desc,colour,date,timestamp,code, dataid);
        item.setOnline(online);
        item.setDeletedLocal(deletedLocal);

        return item;
    }

    private List<Integer> getIdList(){
        SQLiteDatabase db = getWritableDatabase();
        String where = ITEM_DELETEDLOCALBOOL + " = " + 0;

        Cursor cursor = db.query(ITEM_TABLE_NAME,
                new String[]{ITEM_ID},
                where , null, null, null, null);

        ArrayList<Integer> idList = new ArrayList<>();

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ITEM_ID));
            idList.add(id);
            cursor.moveToNext();
        }
        cursor.close();

        return idList;
    }

}