package com.rafamatias.nlp.domain.repository;

import android.arch.lifecycle.LiveData;

import com.rafamatias.nlp.domain.Resource;

import java.util.List;

public interface UserRepository {
    LiveData<Resource<List<String>>> getUsernames();
}
