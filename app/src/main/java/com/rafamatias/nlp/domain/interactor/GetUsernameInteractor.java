package com.rafamatias.nlp.domain.interactor;

import android.arch.lifecycle.MutableLiveData;

import com.rafamatias.nlp.data.repository.UserDataRepository;
import com.rafamatias.nlp.domain.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class GetUsernameInteractor extends MutableLiveData<String> {

    private UserRepository userRepository;

    public GetUsernameInteractor() {
        this.userRepository = new UserDataRepository();

        init();
    }

    private void init() {
        List<String> usernames = userRepository.getUsernames();

        Collections.shuffle(usernames);

        setValue(usernames.get(0));
    }

}
