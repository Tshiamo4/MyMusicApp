package vcmsa.ci.mymusicapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        class SecondActivity : AppCompatActivity() {

            private lateinit var displaySongsButton: Button
            private lateinit var returnButton: Button
            private lateinit var songListTextView: TextView

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_second)

                displaySongsButton = findViewById(R.id.displaySongsButton)
                returnButton = findViewById(R.id.returnButton)
                songListTextView = findViewById(R.id.songListTextView)

                displaySongsButton.setOnClickListener { displaySongs() }
                returnButton.setOnClickListener { finish() }
            }

            private fun displaySongs() {
                val songList = intent.getStringArrayListExtra("songList") ?: return
                val songCountMap = songList.groupingBy { it }.eachCount()
                val displayText = songCountMap.filter { it.value >= 2 }
                    .map { "${it.key}: ${it.value}" }
                    .joinToString("\n")

                songListTextView.text = displayText.ifEmpty { "No songs with 2 or more quantities" }
                Log.d("SecondActivity", "Displayed songs: $displayText")
            }
        }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}