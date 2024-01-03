package aplikasi.mobile.uas_rental_mobil.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import aplikasi.mobile.uas_rental_mobil.R;
import aplikasi.mobile.uas_rental_mobil.helper.DataHelper;

public class SewaMobilActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nama, alamat, no_hp, lama;
    RadioGroup promo;
    RadioButton weekday, weekend;
    Button selesai;

    String sNama, sAlamat, sNo, sMerk, sLama;
    double dPromo;
    int iLama, iPromo, iHarga;
    double dTotal;

    private Spinner spinner;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa);

        dbHelper = new DataHelper(this);

        spinner = findViewById(R.id.spinner);
        selesai = findViewById(R.id.selesaiHitung);
        nama = findViewById(R.id.eTNama);
        alamat = findViewById(R.id.eTAlamat);
        no_hp = findViewById(R.id.eTHP);
        promo = findViewById(R.id.promoGroup);
        weekday = findViewById(R.id.rbWeekDay);
        weekend = findViewById(R.id.rbWeekEnd);
        lama = findViewById(R.id.eTLamaSewa);

        spinner.setOnItemSelectedListener(this);

        loadSpinnerData();

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNama = nama.getText().toString();
                sAlamat = alamat.getText().toString();
                sNo = no_hp.getText().toString();
                sLama = lama.getText().toString();
                if (sNama.isEmpty() || sAlamat.isEmpty() || sNo.isEmpty() || sLama.isEmpty()) {
                    Toast.makeText(SewaMobilActivity.this, "(*) harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (weekday.isChecked()) {
                    dPromo = 0.1;
                } else if (weekend.isChecked()) {
                    dPromo = 0.25;
                }

                if (sMerk.equals("Excavator")) {
                    iHarga = 3520000;
                } else if (sMerk.equals("Bulldozer")) {
                    iHarga = 4000000;
                } else if (sMerk.equals("Rotary Drills")) {
                    iHarga = 12000000;
                } else if (sMerk.equals("Track Asphalt")) {
                    iHarga = 8400000;
                } else if (sMerk.equals("Rotary Bore Drill")) {
                    iHarga = 6000000;
                } else if (sMerk.equals("Dump Truck")) {
                    iHarga = 4000000;
                } else if (sMerk.equals("Motor Grader")) {
                    iHarga = 4250000;
                } else if (sMerk.equals("Wheel Loaders")) {
                    iHarga = 3530000;
                } else if (sMerk.equals("Carmix Cor")) {
                    iHarga = 7500000;
                } else if (sMerk.equals("Tandem Vibratory Rollers")) {
                    iHarga = 3000000;
                } else if (sMerk.equals("Crane")) {
                    iHarga = 15000000;
                } else if (sMerk.equals("Counterbalanced Pneumatic Tire")) {
                    iHarga = 13250000;
                } else if (sMerk.equals("Telescopic Boom Crawler Crane")) {
                    iHarga = 10000000;
                } else if (sMerk.equals("Pemadat")) {
                    iHarga = 300000;
                }else if (sMerk.equals("Cold Milling BM 1000")) {
                    iHarga = 6500000;
                }

                iLama = Integer.parseInt(sLama);
                iPromo = (int) (dPromo * 100);
                dTotal = (iHarga * iLama) - (iHarga * iLama * dPromo);

                SQLiteDatabase dbH = dbHelper.getWritableDatabase();
                dbH.execSQL("INSERT INTO penyewa (nama, alamat, no_hp) VALUES ('" +
                        sNama + "','" +
                        sAlamat + "','" +
                        sNo + "');");
                dbH.execSQL("INSERT INTO sewa (merk, nama, promo, lama, total) VALUES ('" +
                        sMerk + "','" +
                        sNama + "','" +
                        iPromo + "','" +
                        iLama + "','" +
                        dTotal + "');");
                PenyewaActivity.m.RefreshList();
                finish();

            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbSewaMobl);
        toolbar.setTitle("Sewa Alat Berat");
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

    private void loadSpinnerData() {
        DataHelper db = new DataHelper(getApplicationContext());
        List<String> categories = db.getAllCategories();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sMerk = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}