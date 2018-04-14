package com.example.jaworek.tcpchat;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpActivity extends Chat {
    DatagramSocket socket;
    TextView t;
    EditText e;
    Button b;

    class SocketListener implements Runnable {
        String str;

        public void run() {
            DatagramPacket packet;
            byte[] buf;

            try {
                while (true) {
                    buf = new byte[256];
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String s = new String(packet.getData());

                    CharSequence cs = t.getText();
                    str = cs + "Client> " + s + "\n";

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

        try {
            socket = new DatagramSocket();
        } catch (SocketException e1) {
        }

        e = findViewById(R.id.editText);

        t = findViewById(R.id.textView);
        t.setMovementMethod(new ScrollingMovementMethod());

        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s = e.getText().toString();
                t.setText(t.getText() + Globals.username + "> " + s + "\r\n");
                e.setText("");
                try {
                    byte[] buf;

                    buf = s.getBytes();
                    // Change this to the IP address of your computer OR “10.0.2.2”(Gateway to 127.0.0.1 of host)
                    InetAddress address = InetAddress.getByName(Globals.ip);
                    final DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Globals.port);

                    new Thread() {
                        public void run() {
                            try {
                                socket.send(packet);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }.start();
                } catch (UnknownHostException e2) {
                }
            }
        });

        Thread t = new Thread(new SocketListener());
        t.start();
    }
}
