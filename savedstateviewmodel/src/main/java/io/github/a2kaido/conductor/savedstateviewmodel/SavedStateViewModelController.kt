package io.github.a2kaido.conductor.savedstateviewmodel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.archlifecycle.ControllerLifecycleOwner

abstract class SavedStateViewModelController(
    args: Bundle? = null
) : Controller(args),
    ViewModelStoreOwner,
    HasDefaultViewModelProviderFactory,
    SavedStateRegistryOwner {

    private val viewModelStore: ViewModelStore = ViewModelStore()
    private val lifecycleOwner = ControllerLifecycleOwner(this)
    private val savedStateRegistryController = SavedStateRegistryController.create(this)
    private var restored = false

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        if (lifecycle.currentState == Lifecycle.State.INITIALIZED && restored.not()) {
            savedStateRegistryController.performRestore(null)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        savedStateRegistryController.performSave(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedStateRegistryController.performRestore(savedInstanceState)
        restored = true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }

    override fun getViewModelStore() = viewModelStore

    override fun getLifecycle() = lifecycleOwner.lifecycle

    override fun getSavedStateRegistry() = savedStateRegistryController.savedStateRegistry

    override fun getDefaultViewModelProviderFactory() = SavedStateViewModelFactory(
        activity!!.application,
        this
    )
}
