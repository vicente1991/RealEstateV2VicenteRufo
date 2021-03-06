package com.salesianostriana.dam.RealEstate.services.base;

import com.salesianostriana.dam.RealEstate.dto.vivienda.GetViviendaDTO;
import com.salesianostriana.dam.RealEstate.model.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService <T, Long, R extends JpaRepository<T,Long>> {

    @Autowired
    protected R repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public T getById(Long id) { return repository.getById(id); }

    public T save(T t) {
        return repository.save(t);
    }

    public T edit(T t) {
        return save(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public Page<T> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
