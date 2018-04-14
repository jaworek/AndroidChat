package com.example.jaworek.tcpchat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

internal abstract class Chat : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        val item1 = menu.add(0, 0, Menu.NONE, "Welcome")
        val item2 = menu.add(0, 1, Menu.NONE, "Tcp")
        val item3 = menu.add(0, 2, Menu.NONE, "Udp")

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
                val i1 = Intent(this, MainActivity::class.java)
                startActivity(i1)
                return true
            }
            1 -> {
                val i2 = Intent(this, TcpActivity::class.java)
                startActivity(i2)
                return true
            }
            2 -> {
                val i3 = Intent(this, UdpActivity::class.java)
                startActivity(i3)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
