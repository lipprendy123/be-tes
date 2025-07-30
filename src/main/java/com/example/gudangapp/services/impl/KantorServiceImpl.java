package com.example.gudangapp.services.impl;

import com.example.gudangapp.model.pojo.Kantor;
import com.example.gudangapp.repository.KantorRepository;
import com.example.gudangapp.services.KantorService;
import com.example.gudangapp.utils.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class KantorServiceImpl implements KantorService {

    private final KantorRepository kantorRepository;

    @Override
    @Transactional
    public ResponseEntity<DataResponse<Kantor>> createKantor(Kantor req) {
        try {
            if (kantorRepository.existsByKodeKantor(req.getKodeKantor())) {
                throw new RuntimeException("Kode kantor sudah ada");
            }
            Kantor saved = kantorRepository.save(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(saved, "Berhasil membuat data kantor", 201));
        } catch (Exception e) {
            log.error("[createKantor] Error", e);
            throw new RuntimeException("Gagal membuat kantor");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DataResponse<Kantor>> updateKantor(Long id, Kantor req) {
        try {
            Kantor kantor = kantorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
            kantor.setNamaKantor(req.getNamaKantor());
            kantor.setAlamatKantor(req.getAlamatKantor());
            Kantor updated = kantorRepository.save(kantor);
            return ResponseEntity.ok(new DataResponse<>(updated));
        } catch (Exception e) {
            log.error("[updateKantor] Error", e);
            throw new RuntimeException("Gagal update kantor");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DefaultResponse> deleteKantor(Long id) {
        try {
            kantorRepository.deleteById(id);
            return ResponseEntity.ok(new DefaultResponse("Berhasil dihapus"));
        } catch (Exception e) {
            log.error("[deleteKantor] Error", e);
            throw new RuntimeException("Gagal hapus kantor");
        }
    }

    @Override
    public ResponseEntity<DataResponse<Kantor>> getKantor(Long id) {
        Kantor kantor = kantorRepository.findById(id).orElseThrow(() -> new RuntimeException("Kantor tidak ditemukan"));
        return ResponseEntity.ok(new DataResponse<>(kantor));
    }

    @Override
    public ResponseEntity<ListResponse<Kantor>> getAll() {
        List<Kantor> list = kantorRepository.findAll();
        return ResponseEntity.ok(new ListResponse<>(list));
    }
}