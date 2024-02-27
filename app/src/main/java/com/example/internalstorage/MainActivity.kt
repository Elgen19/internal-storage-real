package com.example.internalstorage


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var writeTextEditText: EditText
    private lateinit var retrievedTextTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeTextEditText = findViewById(R.id.writeTextEditText)
        retrievedTextTextView = findViewById(R.id.retrievedTextTextView)

        val saveButton: Button = findViewById(R.id.saveButton)
        val retrieveButton: Button = findViewById(R.id.retrieveButton)

        saveButton.setOnClickListener {
            saveTextToFile(writeTextEditText.text.toString())
        }

        retrieveButton.setOnClickListener {
            retrievedTextTextView.text = readTextFromFile()
        }
    }

    private fun saveTextToFile(text: String) {
        val file = File(filesDir, "myTextFile.txt")
        FileOutputStream(file).use {
            it.write(text.toByteArray())
        }
        Toast.makeText(this, "Text successfully saved!", Toast.LENGTH_LONG).show()
        writeTextEditText.text.clear()
    }

    private fun readTextFromFile(): String {
        val file = File(filesDir, "myTextFile.txt")
        val stringBuilder = StringBuilder()
        FileInputStream(file).use { inputStream ->
            inputStream.bufferedReader().useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                    stringBuilder.append("\n")
                }
            }
        }
        return stringBuilder.toString()
    }
}
