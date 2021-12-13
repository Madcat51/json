package site.madcat.json

//import Results
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.constraintlayout.utils.widget.MockView
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import site.madcat.json.databinding.ActivityMainBinding

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //   val urlPath: String="https://api.github.com/users/kshalnov/repos"

    val urlPath: String=
        "https://api.themoviedb.org/3/discover/movie?&sort_by=popularity.desc&api_key=b46aa2f69329d4b3b5e8d2e1ea6b7886"
    private val gson by lazy { Gson() }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            Thread {
                val url=URL(urlPath)
                val urlConnection=url.openConnection() as HttpsURLConnection
                try {
                    urlConnection.requestMethod="GET"
                    urlConnection.readTimeout=5000
                    val reader=
                        BufferedReader(InputStreamReader(urlConnection.getInputStream()))//только один раз
                    val result=reader.readLines().toString()
                    val sBuilder=StringBuilder()

                    var listResult=gson.fromJson(result, Array<MovieEntity>::class.java)
                    listResult.forEach {
                        var listMovie=it.results
                        listMovie.forEach { sBuilder.appendLine(it.original_title + " " + it.overview) }
                    }
                    runOnUiThread { binding.textView.text=sBuilder }

                } finally {
                    urlConnection?.disconnect()
                }

            }.start()
        }


    }
}

