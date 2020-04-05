package io.github.a2kaido.conductor.savedstateviewmodel.controller_scoped_viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.*
import io.github.a2kaido.conductor.savedstateviewmodel.R
import io.github.a2kaido.conductor.savedstateviewmodel.SavedStateViewModelController

class SavedController : SavedStateViewModelController() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(SavedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_saved, container, false)

        val textView = view.findViewById<TextView>(R.id.saved_text)
        textView.setOnClickListener {
            viewModel.changeState()
        }

        viewModel.state.observe(this, Observer {
            textView.text = it
        })

        return view
    }
}

class SavedViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private enum class SavedKey {
        STATE
    }

    private val _state: MutableLiveData<String> = savedStateHandle.getLiveData(SavedKey.STATE.name)
    val state: LiveData<String>
        get() = _state

    fun changeState() {
        savedStateHandle.set(
            SavedKey.STATE.name,
            "state changed.\nPlease check the behavior for rotation and re-create activity"
        )
    }
}
