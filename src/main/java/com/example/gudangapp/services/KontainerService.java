package com.example.gudangapp.services;

import com.example.gudangapp.model.pojo.Kontainer;
import com.example.gudangapp.utils.response.*;
import org.springframework.http.ResponseEntity;

public interface KontainerService {
    ResponseEntity<DataResponse<Kontainer>> create(Kontainer req);
    ResponseEntity<DataResponse<Kontainer>> update(Long id, Kontainer req);
    ResponseEntity<DefaultResponse> delete(Long id);
    ResponseEntity<DataResponse<Kontainer>> getById(Long id);
    ResponseEntity<ListResponse<Kontainer>> getAll();
    ResponseEntity<ListResponse<Kontainer>> findByFilters(String kodeGudang, String kodeKantor, String nomorDokumen);
}