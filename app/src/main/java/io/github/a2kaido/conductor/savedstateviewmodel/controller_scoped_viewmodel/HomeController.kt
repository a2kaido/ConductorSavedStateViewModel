package io.github.a2kaido.conductor.savedstateviewmodel.controller_scoped_viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bluelinelabs.conductor.RouterTransaction
import io.github.a2kaido.conductor.savedstateviewmodel.R
import io.github.a2kaido.conductor.savedstateviewmodel.SavedStateViewModelController

class HomeController : SavedStateViewModelController() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)

        view.findViewById<Button>(R.id.home_next_button).setOnClickListener {
            router.pushController(RouterTransaction.with(SavedController()))
        }

        return view
    }
}
