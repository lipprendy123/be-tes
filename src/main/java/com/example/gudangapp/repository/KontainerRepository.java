package com.example.gudangapp.repository;

import com.example.gudangapp.model.pojo.Kontainer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontainerRepository extends JpaRepository<Kontainer, Long> {
    boolean existsByNomorKontainer(String nomorKontainer);
    List<Kontainer> findByKodeGudangAndKodeKantorAndNomorDokumen(String kodeGudang, String kodeKantor, String nomorDokumen);
}