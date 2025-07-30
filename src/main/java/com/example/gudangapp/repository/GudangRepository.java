package com.example.gudangapp.repository;

import com.example.gudangapp.model.pojo.Gudang;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GudangRepository extends JpaRepository<Gudang, Long> {
    boolean existsByKodeGudang(String kodeGudang);
    List<Gudang> findByKodeKantor(String kodeKantor);
    boolean existsByKodeGudangAndKodeKantor(String kodeGudang, String kodeKantor);
}