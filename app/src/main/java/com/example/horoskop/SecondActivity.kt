package com.example.horoskop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SecondActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second2)

    val getUrl : String? = intent.getStringExtra("url")
    val url : String = getUrl.toString()
    APIkall(liste, url)
  }
    var requestQueue : RequestQueue? = null
    val dateRange = findViewById<TextView>(R.id.dateRange)
    val currentDate = findViewById<TextView>(R.id.currentDate)
    val description = findViewById<TextView>(R.id.description)
    val compatibility = findViewById<TextView>(R.id.compatibility)
    val mood = findViewById<TextView>(R.id.mood)
    val color = findViewById<TextView>(R.id.color)
    val luckyNumber = findViewById<TextView>(R.id.luckyNumber)
    val luckyTime = findViewById<TextView>(R.id.luckyTime)
    val liste = listOf(dateRange,currentDate,description,compatibility,mood,color,luckyNumber,luckyTime)

  private fun APIkall(view : List<TextView>, url : String) {
    requestQueue = Volley.newRequestQueue(this)
    val request = StringRequest(
      Request.Method.POST,url,
      { response ->
        val attributes = formatResponce(response.toString())
        view[0].text = attributes[0]
        view[1].text = attributes[1]
        view[2].text = attributes[2]
        view[3].text = attributes[3]
        view[4].text = attributes[4]
        view[5].text = attributes[5]
        view[6].text = attributes[6]
        view[7].text = attributes[7]
      },
      { error ->
        findViewById<TextView>(R.id.currentDate).text = ("Kunne ikke laste horoskop")

      }
    )
    request.tag = "horoskop"
    requestQueue?.add(request)

  }
  private fun formatResponce(resp : String) : List<String> {

    val liste = resp.split("\"")
    val dateRange = liste[3]
    val currentDate = liste[7]
    val description = liste[11]
    val compatibility = liste[15]
    val mood = liste[19]
    val color = liste[23]
    val luckyNumber = liste[27]
    val luckyTime = liste[31]

    val list = listOf(
      dateRange, currentDate, description, compatibility, mood,
      color, luckyNumber, luckyTime
    )
    return list
  }

    override fun onStop() {
      super.onStop()
      requestQueue?.cancelAll("horoskop")
}
}