package com.example.jaworek.tcpchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

abstract class Chat extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item1 = menu.add(0, 0, Menu.NONE, "Welcome");
        MenuItem item2 = menu.add(0, 1, Menu.NONE, "Tcp");
        MenuItem item3 = menu.add(0, 2, Menu.NONE, "Udp");

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                return true;
            case 1:
                Intent i2 = new Intent(this, TcpActivity.class);
                startActivity(i2);
                return true;
            case 2:
                Intent i3 = new Intent(this, UdpActivity.class);
                startActivity(i3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
