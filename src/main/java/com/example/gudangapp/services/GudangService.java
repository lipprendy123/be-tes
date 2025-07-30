package com.example.gudangapp.services;

import com.example.gudangapp.model.pojo.Gudang;
import com.example.gudangapp.utils.response.*;
import org.springframework.http.ResponseEntity;

public interface GudangService {
    ResponseEntity<DataResponse<Gudang>> createGudang(Gudang req);
    ResponseEntity<DataResponse<Gudang>> updateGudang(Long id, Gudang req);
    ResponseEntity<DefaultResponse> deleteGudang(Long id);
    ResponseEntity<DataResponse<Gudang>> getGudang(Long id);
    ResponseEntity<ListResponse<Gudang>> getAll();
    ResponseEntity<ListResponse<Gudang>> getGudangByKodeKantor(String kodeKantor);
}