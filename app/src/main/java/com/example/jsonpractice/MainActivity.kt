package com.example.jsonpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    private lateinit var ed:EditText
    private lateinit var tv:TextView
    private lateinit var btnGet:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ed = findViewById(R.id.ed)
        tv = findViewById(R.id.tv)
        btnGet = findViewById(R.id.btnGet)

        btnGet.setOnClickListener {
            try {
                val input = ed.text.toString().toInt()
                if (input < 1 || input > 13 ) { //CHEACK NUM BTN 0 TO 13
                    Toast.makeText(applicationContext, "Enter Num Btn 1 an 13", Toast.LENGTH_SHORT).show()
                } else {
                    if (apiInterface != null) {
                        apiInterface.getUser()?.enqueue(object : Callback<Array<DataItem>?> {
                            override fun onResponse(
                                call: Call<Array<DataItem>?>,
                                response: Response<Array<DataItem>?>
                            ) {
                                val users = response.body()!!
                                var i = 1
                                for (user in users) {
                                    if (i == ed.text.toString().toInt()) {
                                        tv.text = user.name
                                    }
                                    i++
                                }

                            }
                            override fun onFailure(call: Call<Array<DataItem>?>, th: Throwable) {
                                Toast.makeText(applicationContext, "${th.message}", Toast.LENGTH_SHORT).show()
                            }
                        })

                    }
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Please enter number ", Toast.LENGTH_SHORT)
                    .show()

            }

        }

    }



}