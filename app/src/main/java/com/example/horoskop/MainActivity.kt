package com.example.horoskop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val spinner: Spinner = findViewById(R.id.spinner)
    ArrayAdapter.createFromResource(
      this,
      R.array.stjernetegn_array,
      android.R.layout.simple_spinner_item
    ).also { adapter ->
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
      spinner.adapter = adapter
    }

    val spinner2: Spinner = findViewById(R.id.spinner2)
    ArrayAdapter.createFromResource(
      this,
      R.array.days_array,
      android.R.layout.simple_spinner_item
    ).also { adapter ->
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
      spinner2.adapter = adapter
    }

    val Submit : Button = findViewById(R.id.Submit)
    Submit.setOnClickListener{
      val selectedSign : String = spinner.selectedItem.toString()
      val selectedDate : String = spinner2.selectedItem.toString()
      val url : String = createURL(selectedSign, selectedDate)
      val intent = Intent(this,SecondActivity::class.java)
      intent.putExtra("url", url)
      startActivity(intent)
    }
  }
  private fun createURL(sign : String, date: String): String {
    val url = "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
    return url
  }
}
