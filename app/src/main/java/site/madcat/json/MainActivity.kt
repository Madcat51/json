package site.madcat.json

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.google.firebase.inappmessaging.model.Button
import site.madcat.json.databinding.ActivityMainBinding
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.URL

import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val urlPath: String=
        "https://api.themoviedb.org/3/movie/top_rated?api_key=b46aa2f69329d4b3b5e8d2e1ea6b7886&language=ru-RU&page=1"

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Thread {
                val url=URL(urlPath)
                var urlConnection=url.openConnection() as HttpsURLConnection
                try {

                    urlConnection.requestMethod="GET"
                    urlConnection.readTimeout=5000
                    val reader=BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                    val result=reader.readLines().toString()

                    runOnUiThread { binding.textView.text=result }


                } finally {

                    urlConnection?.disconnect()
                }
            }.start()
        }

        /*   @RequiresApi(Build.VERSION_CODES.N)
           private fun getLines(reader: BufferedReader): String {
               return reader.lines().collect(Collectors.joining("\n"))
           }*/

    }
}
/*
val uri=URL(binding.editText.text.toString())
val handler=Handler()
Thread {
    var urlConnection: HttpsURLConnection?=null


    urlConnection=uri.openConnection() as HttpsURLConnection
    urlConnection.requestMethod=
        "GET" // установка метода получения данных -- GET
    urlConnection.readTimeout=10000 // установка таймаута -- 10 000 миллисекунд
    val reader=
        BufferedReader(InputStreamReader(urlConnection.getInputStream())) // читаем  данные в поток
    val result=getLines(reader)
    handler.post {
        //  binding.webView.loadDataWithBaseURL(null, result, "text/html; charset=utf-8", "utf-8", null)
    }


    urlConnection?.disconnect()

}.start()


}*/