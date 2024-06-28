package com.codegym.service;

import java.util.Optional;

public interface GenerateService<P> {

    Iterable<P> findAll();

    Optional<P> findById(long id);

    void save(P p);

    void remove(Long id);
}
