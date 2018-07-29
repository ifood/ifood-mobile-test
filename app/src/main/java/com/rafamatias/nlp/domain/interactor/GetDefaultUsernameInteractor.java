package com.rafamatias.nlp.domain.interactor;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.rafamatias.nlp.data.repository.UserDataRepository;
import com.rafamatias.nlp.domain.Resource;
import com.rafamatias.nlp.domain.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class GetDefaultUsernameInteractor {
    private UserRepository userRepository;

    public GetDefaultUsernameInteractor() {
        this.userRepository = new UserDataRepository();
    }

    public LiveData<String> getDefaultUsername(){
        return Transformations.map(userRepository.getUsernames(), new Function<Resource<List<String>>, String>() {
            @Override
            public String apply(Resource<List<String>> input) {
                List<String> usernames = input.data;

                Collections.shuffle(usernames);

                return usernames.get(0);
            }
        });
    }

}
