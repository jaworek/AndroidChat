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

    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTcp = findViewById(R.id.button);
        buttonUdp = findViewById(R.id.button2);

        ipAddress = findViewById(R.id.editText);
        portNumber = findViewById(R.id.editText2);
        username = findViewById(R.id.editText3);

        error = findViewById(R.id.errorView);

        buttonTcp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (startChat()) {
                    Intent i = new Intent(MainActivity.this, TcpActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        buttonUdp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (startChat()) {
                    Intent i = new Intent(MainActivity.this, UdpActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    public boolean startChat() {
        if (ipAddress.getText().toString().matches("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})")
                && portNumber.getText().toString().matches("\\d*")
                && !username.getText().toString().equals("")) {
            Globals.ip = ipAddress.getText().toString();
            Globals.port = Integer.parseInt(portNumber.getText().toString());
            Globals.username = username.getText().toString();

            return true;
        } else {
            error.setText("Error");
        }

        return false;
    }
}
