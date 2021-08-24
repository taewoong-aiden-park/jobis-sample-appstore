package co.jobis.sample.appstore.ui.event

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.atomic.AtomicBoolean

class EventObservable {
    private val atomicBoolean = AtomicBoolean(false)
    private val liveData = MutableLiveData<Unit?>()

    fun observe(owner: LifecycleOwner, callback: () -> Unit) {
        // Observe the internal MutableLiveData
        liveData.observe(owner, { t ->
            if (atomicBoolean.compareAndSet(true, false)) {
                callback()
            }
        })
    }

    @MainThread
    fun call() {
        atomicBoolean.set(true)
        liveData.value = null
    }
}