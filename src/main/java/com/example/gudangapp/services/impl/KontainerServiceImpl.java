package com.example.gudangapp.services.impl;

import com.example.gudangapp.model.pojo.Kontainer;
import com.example.gudangapp.repository.KontainerRepository;
import com.example.gudangapp.repository.GudangRepository;
import com.example.gudangapp.repository.KantorRepository;
import com.example.gudangapp.services.KontainerService;
import com.example.gudangapp.utils.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class KontainerServiceImpl implements KontainerService {

    private final KontainerRepository kontainerRepository;
    private final GudangRepository gudangRepository;
    private final KantorRepository kantorRepository;

    @Override
@Transactional
public ResponseEntity<DataResponse<Kontainer>> create(Kontainer req) {
    try {

        if (req.getKodeGudang() == null || req.getKodeKantor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "kodeGudang dan kodeKantor wajib diisi");
        }

        if (!gudangRepository.existsByKodeGudangAndKodeKantor(req.getKodeGudang(), req.getKodeKantor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Gudang tidak ditemukan untuk kode " + req.getKodeGudang() + " dan kantor " + req.getKodeKantor());
        }

        if (!kantorRepository.existsByKodeKantor(req.getKodeKantor())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Kode kantor tidak ditemukan: " + req.getKodeKantor());
        }

        Kontainer saved = kontainerRepository.save(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(saved, "Berhasil membuat data kontainer", 201));

    } catch (ResponseStatusException e) {
        log.error("Terjadi kesalahan validasi: {}", e);
        throw new RuntimeException("Gagal create kontainer");
    }
}


    @Override
    @Transactional
    public ResponseEntity<DataResponse<Kontainer>> update(Long id, Kontainer req) {
        try {
            Kontainer kontainer = kontainerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Kontainer tidak ditemukan"));

            kontainer.setNomorKontainer(req.getNomorKontainer());
            kontainer.setUkuranKontainer(req.getUkuranKontainer());
            kontainer.setTanggalTiba(req.getTanggalTiba());
            kontainer.setBruto(req.getBruto());
            kontainer.setNomorDokumen(req.getNomorDokumen());

            Kontainer updated = kontainerRepository.save(kontainer);
            return ResponseEntity.ok(new DataResponse<>(updated));
        } catch (Exception e) {
            log.error("[update] Kontainer error", e);
            throw new RuntimeException("Gagal update kontainer");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DefaultResponse> delete(Long id) {
        try {
            kontainerRepository.deleteById(id);
            return ResponseEntity.ok(new DefaultResponse("Berhasil dihapus"));
        } catch (Exception e) {
            log.error("[delete] Kontainer error", e);
            throw new RuntimeException("Gagal menghapus kontainer");
        }
    }

    @Override
    public ResponseEntity<DataResponse<Kontainer>> getById(Long id) {
        Kontainer kontainer = kontainerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kontainer tidak ditemukan"));
        return ResponseEntity.ok(new DataResponse<>(kontainer));
    }

    @Override
    public ResponseEntity<ListResponse<Kontainer>> getAll() {
        List<Kontainer> list = kontainerRepository.findAll();
        return ResponseEntity.ok(new ListResponse<>(list));
    }

    @Override
    public ResponseEntity<ListResponse<Kontainer>> findByFilters(String kodeGudang, String kodeKantor, String nomorDokumen) {
        try {
        List<Kontainer> list = kontainerRepository
                .findByKodeGudangAndKodeKantorAndNomorDokumen(kodeGudang, kodeKantor, nomorDokumen);

        if (list.isEmpty()) {
            throw new NotFoundException();
        }

        return ResponseEntity.ok(new ListResponse<>(list));
    } catch (Exception e) {
            log.error("[findByFilters] Error", e);
            throw new RuntimeException("Gagal mengambil data filter kontainer");
        }   
    }
}
