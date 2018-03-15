package com.example.jaworek.tcpchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button buttonTcp;
    Button buttonUdp;

    EditText ipAddress;
    EditText portNumber;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTcp = findViewById(R.id.button);
        buttonUdp = findViewById(R.id.button2);

        ipAddress = findViewById(R.id.editText);
        portNumber = findViewById(R.id.editText2);
        username = findViewById(R.id.editText3);

        buttonTcp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Globals.ip = ipAddress.getText().toString();
                Globals.port = Integer.parseInt(portNumber.getText().toString());
                Globals.username = username.getText().toString();

                Intent i = new Intent(MainActivity.this, TcpActivity.class);
                startActivity(i);

            }
        });

        buttonUdp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Globals.ip = ipAddress.getText().toString();
                Globals.port = Integer.parseInt(portNumber.getText().toString());
                Globals.username = username.getText().toString();

                Intent i = new Intent(MainActivity.this, UdpActivity.class);
                startActivity(i);
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuItem item1 = menu.add(0, 0, Menu.NONE, "Welcome");
//        MenuItem item2 = menu.add(0, 1, Menu.NONE, "Tcp");
//        MenuItem item3 = menu.add(0, 2, Menu.NONE, "Udp");
//
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case 0:
//                Intent i1 = new Intent(this, MainActivity.class);
//                startActivity(i1);
//                return true;
//            case 1:
//                Intent i2 = new Intent(this, TcpActivity.class);
//                startActivity(i2);
//                return true;
//            case 2:
//                Intent i3 = new Intent(this, UdpActivity.class);
//                startActivity(i3);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
