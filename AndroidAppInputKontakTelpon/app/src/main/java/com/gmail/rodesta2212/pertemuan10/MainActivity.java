package com.gmail.rodesta2212.pertemuan10;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import SimpleArrayAdapter.SimpleArrayAdapter;

public class MainActivity extends AppCompatActivity {
    EditText nama, telepon;
    TextView dataTelepon;
    Button tombolInput;
    SimpleArrayAdapter adapter;
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //penghubung antara objek dalam java dengan layout
        nama = (EditText) findViewById(R.id.editNama);
        telepon = (EditText) findViewById(R.id.editTelepon);
        dataTelepon = (TextView) findViewById(R.id.textDataTelp);
        tombolInput = (Button) findViewById(R.id.buttonInput);
        listview = (ListView) findViewById(R.id.listView);
        //event pada tombol
        tombolInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //menyiapkan buffer
                byte[] bufferNama = new byte[30];
                byte[] bufferTelepon = new byte[15];
                //menyalin data ke buffer
                salinData(bufferNama, nama.getText().toString());
                salinData(bufferTelepon, telepon.getText().toString());
                //proses menyimpan file ke internal memori
                try{
                    //menyiapkan file di memori internal
                    FileOutputStream dataFile = openFileOutput("telepon.txt",
                            MODE_APPEND);
                    DataOutputStream output = new DataOutputStream(dataFile);
                    //menyimpan data
                    output.write(bufferNama);
                    output.write(bufferTelepon);
                    //menutup file
                    dataFile.close();
                    //menampilkan pesan jika data tersimpan
                    Toast.makeText(getBaseContext(), "Data telah disimpan",
                            Toast.LENGTH_LONG).show();
                }
                catch (IOException e){
                    Toast.makeText(getBaseContext(), "Kesalahan: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                tampilkanData();
            }
        });
        tampilkanData();
    }
    public void salinData(byte[] buffer, String data) {
        //mengosongkan buffer
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = 0;
        //menyalin data ke buffer
        for (int i = 0; i < data.length(); i++)
            buffer[i] = (byte) data.charAt(i);
    }

    public void tampilkanData() {
        try {
            // menyiapkan file untuk dibaca
            FileInputStream dataFile = openFileInput("telepon.txt");
            DataInputStream input = new DataInputStream(dataFile);
            //menyiapkan buffer
            byte[] bufNama = new byte[30];
            byte[] bufTelepon = new byte[15];
            String infoData = "Data Telepon:\n";
            //proses membaca data
            while (input.available() > 0) {
                input.read(bufNama);
                input.read(bufTelepon);
                String dataNama = "";
                for (int i = 0; i < bufNama.length; i++)
                    dataNama = dataNama + (char) bufNama[i];
                String dataTelepon = "";
                for (int i = 0; i < bufTelepon.length; i++)
                    dataTelepon = dataTelepon + (char) bufTelepon[i];
                //format menampilkan data
                infoData = infoData + " > " + dataNama + " - " +
                        dataTelepon + "\n";
            }
            //menampilkan data ke teks view
            dataTelepon.setText(infoData);
            dataFile.close();
        }
        catch (IOException e) {
            Toast.makeText(getBaseContext(), "Kesalahan: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
