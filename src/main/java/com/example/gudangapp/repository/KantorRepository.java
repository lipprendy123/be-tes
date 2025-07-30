package com.example.gudangapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gudangapp.model.pojo.Kantor;

@Repository
public interface KantorRepository extends JpaRepository<Kantor, Long> {
    boolean existsByKodeKantor(String kodeKantor);
    Kantor findByKodeKantor(String kodeKantor);
}
