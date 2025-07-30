package com.example.gudangapp.services.impl;

import com.example.gudangapp.model.pojo.Gudang;
import com.example.gudangapp.repository.GudangRepository;
import com.example.gudangapp.repository.KantorRepository;
import com.example.gudangapp.services.GudangService;
import com.example.gudangapp.utils.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
public class GudangServiceImpl implements GudangService {
    private final GudangRepository gudangRepository;
    private final KantorRepository kantorRepository;

    @Override
    @Transactional
    public ResponseEntity<DataResponse<Gudang>> createGudang(Gudang req) {
        try {
            if (!kantorRepository.existsByKodeKantor(req.getKodeKantor())) {
                throw new RuntimeErrorException(null, "Kode kantor tidak ditemukan");
            }

            Gudang saved = gudangRepository.save(req);
            return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(saved, "Berhasil membuat data gudang", 201));
        } catch (Exception e) {
            log.error("[createGudang] Error", e);
            throw new RuntimeException("Gagal membuat gudang");
        }
    }

    
    @Override
    @Transactional
    public ResponseEntity<DataResponse<Gudang>> updateGudang(Long id, Gudang req) {
        try {
            Gudang gudang = gudangRepository.findById(id).orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
            gudang.setAlamat(req.getAlamat());
            gudang.setLatitude(req.getLatitude());
            gudang.setLongitude(req.getLongitude());
            Gudang updated = gudangRepository.save(gudang);
            return ResponseEntity.ok(new DataResponse<>(updated));
        } catch (Exception e) {
             log.error("[updateGudang] Error", e);
            throw new RuntimeException("Gagal update gudang");
        }
       
    }


    @Override
    @Transactional
    public ResponseEntity<DefaultResponse> deleteGudang(Long id) {
        try {
            gudangRepository.deleteById(id);
            return ResponseEntity.ok(new DefaultResponse("Berhasil dihapus"));
        } catch (Exception e) {
             log.error("[deleteGudang] Error", e);
            throw new RuntimeException("Gagal hapus gudang");
        }
    }


    @Override
    public ResponseEntity<DataResponse<Gudang>> getGudang(Long id) {
       Gudang gudang = gudangRepository.findById(id).orElseThrow(() -> new RuntimeException("Gudang tidak ditemukan"));
       return ResponseEntity.ok(new DataResponse<>(gudang));
    }


    @Override
    public ResponseEntity<ListResponse<Gudang>> getAll() {
       List<Gudang> list = gudangRepository.findAll();
       return ResponseEntity.ok(new ListResponse<>(list));
    }


    @Override
    public ResponseEntity<ListResponse<Gudang>> getGudangByKodeKantor(String kodeKantor) {
        try {
            if (!kantorRepository.existsByKodeKantor(kodeKantor)) {
                throw new RuntimeException("Kode kantor tidak ditemukan");
            }

            List<Gudang> list = gudangRepository.findByKodeKantor(kodeKantor);
            return ResponseEntity.ok(new ListResponse<>(list));
        } catch (Exception e) {
           log.error("[getGudangByKodeKantor] Error", e);
        throw new RuntimeException("Gagal mengambil data gudang");
        }
        
    }
}