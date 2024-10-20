import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Set the title of the action bar.
        supportActionBar?.title = "Settings"

        // Check if the frame layout is empty.
        if (findViewById<FrameLayout>(R.id.idFrameLayout) != null) {
            if (savedInstanceState != null) {
                return
            }
            // Inflate the SettingsFragment.
            supportFragmentManager.beginTransaction()
                .add(R.id.idFrameLayout, SettingsFragment())
                .commit()
        }
    }
}
