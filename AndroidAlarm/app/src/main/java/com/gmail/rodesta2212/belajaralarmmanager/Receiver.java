package com.gmail.rodesta2212.belajaralarmmanager;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast; //baris Toast

public class Receiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context c, Intent arg1) {

        Toast.makeText(c, "Alarm Telah Menyala :D", Toast.LENGTH_LONG).show();
        //Pada tabel di atas saya menggunakan Toast.
        // Bisa dikatakan Toast adalah semacam popup yang menampilkan pesan berupa teks.
        // Instansiasi Toast ada pada baris diatas akan menyebabkan alarm menyala akan menampilkan teks
        // “Alarm Telah Menyala”.
        // Dan untuk menggunakan Toast harus import widget Toast terlebih dulu,
        // seperti pada baris Toast.
        // Karena kelas ini extends BroadcastReceiver dan bukan Acitvity maka tidak menggunakan widget
        // pada kelas ini untuk misalnya membuat Button atau TextView.
    }
}