package es.usj.masterstsa.mzamorano.session5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.concurrent.ThreadLocalRandom

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPing.setOnClickListener{
            Thread(Runnable {
                ping("8.8.8.8");
            }).start()
        }
    }

    private fun ping(ip: String)
    {
        val process = Runtime.getRuntime().exec("ping -c 8 $ip")
        val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
        val builder = StringBuilder()
        for(line in bufferedReader.lines())
        {
            builder.append(line);
        }

        runOnUiThread {
            tvResult.text = builder.toString()
        }
    }
}
