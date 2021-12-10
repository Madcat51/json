package site.madcat.json

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import site.madcat.json.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


import javax.net.ssl.HttpsURLConnection


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val urlPath: String="https://api.github.com/users/kshalnov/repos"

    // val urlPath: String="https://api.themoviedb.org/3/movie/top_rated?api_key=b46aa2f69329d4b3b5e8d2e1ea6b7886&language=ru-RU"
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


                    val resJson=gson.fromJson(result, Array<Array<MovieEntity>>::class.java)
                    val sBuilder=StringBuilder()

                    resJson.forEach { array ->
                        array.forEach {
                            sBuilder.append(it.toString())
                        }
                    }
                    runOnUiThread { binding.textView.text=result }

                } finally {
                    urlConnection?.disconnect()
                }
            }.start()
        }


    }
}

