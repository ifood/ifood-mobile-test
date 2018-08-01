package com.rafamatias.nlp.data.repository;

import com.rafamatias.nlp.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

// TODO: UserDataRepository Can it be from search history?
public class UserDataRepository implements UserRepository {

    private final ArrayList<String> names;

    public UserDataRepository() {
        names = new ArrayList<>();
        names.add("RomarioOnze");
        names.add("neymarlIjr");
    }

    @Override
    public List<String> getUsernames() {
        return this.names;
    }
}
