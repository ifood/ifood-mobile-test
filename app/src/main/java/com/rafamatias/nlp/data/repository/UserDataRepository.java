package com.rafamatias.nlp.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserDataRepository implements UserRepository {
    private MutableLiveData<Resource<List<String>>> usernames = new MutableLiveData<>();

    @Override
    public LiveData<Resource<List<String>>> getUsernames() {
        List<String> names = new ArrayList<>();
        names.add("RomarioOnze");
        names.add("neymarlIjr");

        usernames.setValue(Resource.success(names));

        return this.usernames;
    }
}
