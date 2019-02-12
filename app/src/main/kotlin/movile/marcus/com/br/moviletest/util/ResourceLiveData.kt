package movile.marcus.com.br.moviletest.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import movile.marcus.com.br.moviletest.model.Resource
import movile.marcus.com.br.moviletest.model.Status

class ResourceLiveData<T> : MutableLiveData<Resource<T>>() {

    fun observeResource(
        owner: LifecycleOwner,
        onSuccess: (T) -> Unit,
        onError: (Resource<T>) -> Unit
    ) {
        observe(owner, Observer<Resource<T>> {
            it?.let { resource ->
                if (resource.status == Status.SUCCESS) {
                    resource.data?.let { data ->
                        onSuccess.invoke(data)
                    }
                } else {
                    onError.invoke(resource)
                }
            }
        })
    }
}