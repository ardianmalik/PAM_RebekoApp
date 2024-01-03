package aplikasi.mobile.uas_rental_mobil.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import aplikasi.mobile.uas_rental_mobil.R;

import aplikasi.mobile.uas_rental_mobil.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from mobil where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Excavator")) {
            sGambar = "avanza";
        } else if (sMerk.equals("Bulldozer")) {
            sGambar = "xenia";
        } else if (sMerk.equals("Rotary Drills")) {
            sGambar = "ertiga";
        } else if (sMerk.equals("Track Asphalt")) {
            sGambar = "apv";
        } else if (sMerk.equals("Rotary Bore Drill")) {
            sGambar = "innova_reborn";
        } else if (sMerk.equals("Dump Truck")) {
            sGambar = "xpander";
        } else if (sMerk.equals("Motor Grader")) {
            sGambar = "jazz";
        } else if (sMerk.equals("Wheel Loaders")) {
            sGambar = "elf";
        } else if (sMerk.equals("Carmix Cor")) {
            sGambar = "alphard";
        } else if (sMerk.equals("Tandem Vibratory Rollers")) {
            sGambar = "mobilio";
        } else if (sMerk.equals("Crane")) {
            sGambar = "pajero";
        } else if (sMerk.equals("Counterbalanced Pneumatic Tire")) {
            sGambar = "fortuner";
        } else if (sMerk.equals("Telescopic Boom Crawler Crane")) {
            sGambar = "hiace";
        } else if (sMerk.equals("Pemadat")) {
            sGambar = "rush";
        }else if (sMerk.equals("Cold Milling BM 1000")) {
            sGambar = "innova";
        }

        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailMbl);
        toolbar.setTitle("Daftar Rental Alat Berat");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
