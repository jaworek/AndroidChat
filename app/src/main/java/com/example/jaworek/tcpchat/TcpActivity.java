package com.example.jaworek.tcpchat;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class TcpActivity extends Chat {
    public Socket sender;
    public BufferedReader br;
    public PrintStream bw;

    EditText e;
    Button b;
    TextView t;

    int portNumber;
    String ipAddress;

    class SocketListener implements Runnable {
        String str;

        public void run() {
            try {
                sender = new Socket(ipAddress, portNumber);
                br = new BufferedReader(new InputStreamReader(sender.getInputStream()));
                bw = new PrintStream(sender.getOutputStream());
                bw.println("Connected");

                while (true) {
                    String s = br.readLine();
                    CharSequence cs = t.getText();
                    str = cs + "\r\n" + s;

                    t.post(new Runnable() {
                               public void run() {
                                   t.setText(str);
                               }
                           }
                    );
                }
            } catch (IOException e) {
                Log.e(getClass().getName(), e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        t = findViewById(R.id.textView);
        t.setMovementMethod(new ScrollingMovementMethod());
        e = findViewById(R.id.editText);

        portNumber = Globals.port;
        ipAddress = Globals.ip;
        // Change this to the IP address of your computer OR “10.0.2.2”(Gateway to 127.0.0.1 of host)


        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        String s = e.getText().toString();
                        bw.println(Globals.username + ": " + s);
                        e.setText("");
                    }
                }.start();
            }
        });

        Thread t = new Thread(new SocketListener());
        t.start();
    }
}
