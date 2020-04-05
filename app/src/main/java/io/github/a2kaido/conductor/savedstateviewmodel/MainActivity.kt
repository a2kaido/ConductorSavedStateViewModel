package io.github.a2kaido.conductor.savedstateviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.github.a2kaido.conductor.savedstateviewmodel.controller_scoped_viewmodel.ControllerScopedViewModelActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_button).setOnClickListener {
            startActivity(Intent(this, ControllerScopedViewModelActivity::class.java))
        }
    }
}
