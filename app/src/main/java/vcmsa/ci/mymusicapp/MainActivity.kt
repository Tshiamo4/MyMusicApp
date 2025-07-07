package vcmsa.ci.mymusicapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        class MainActivity : AppCompatActivity() {

            private val songList = mutableListOf<String>()
            private lateinit var addButton: Button
            private lateinit var viewSongsButton: Button
            private lateinit var exitButton: Button
            private lateinit var songInput: EditText

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                addButton = findViewById(R.id.addButton)
                viewSongsButton = findViewById(R.id.viewSongsButton)
                exitButton = findViewById(R.id.exitButton)
                songInput = findViewById(R.id.songNameInput)

                addButton.setOnClickListener { addSong() }
                viewSongsButton.setOnClickListener { navigateToSecondScreen() }
                exitButton.setOnClickListener { finish() }
            }

            private fun addSong() {
                val song = songInput.text.toString()
                if (song.isNotEmpty()) {
                    songList.add(song)
                    songInput.text.clear()
                    Log.d("MainActivity", "Added song: $song")
                    Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("MainActivity", "Input error: Song name is empty")
                    Toast.makeText(this, "Please enter a song name", Toast.LENGTH_SHORT).show()
                }
            }

            private fun navigateToSecondScreen() {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putStringArrayListExtra("songList", ArrayList(songList))
                startActivity(intent)
            }
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}