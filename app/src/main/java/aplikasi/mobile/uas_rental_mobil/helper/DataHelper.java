package aplikasi.mobile.uas_rental_mobil.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentalmobil.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table mobil(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references mobil (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into mobil values (" +
                "'Excavator'," +
                "350000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Motor Grader'," +
                "250000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Tandem Vibratory Rollers'," +
                "300000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Bulldozer'," +
                "350000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Rotary Drills'," +
                "350000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Track Asphalt'," +
                "300000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Rotary Bore Drill'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Dump Truck'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Wheel Loaders'," +
                "700000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Crane'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Counterbalanced Pneumatic Tire'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Pemadat'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Carmix Cor'," +
                "1200000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Telescopic Boom Crawler Crane'," +
                "1000000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Cold Milling BM 1000'," +
                "1000000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from mobil";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}