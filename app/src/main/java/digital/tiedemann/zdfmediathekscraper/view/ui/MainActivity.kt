package digital.tiedemann.zdfmediathekscraper.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import digital.tiedemann.zdfmediathekscraper.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(
                R.id.container_fragment,
                ZdfStartPageFragment.newInstance()
            )
        }
    }
}