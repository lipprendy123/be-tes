package com.example.gudangapp.services;

import org.springframework.http.ResponseEntity;

import com.example.gudangapp.model.pojo.Kantor;
import com.example.gudangapp.utils.response.DataResponse;
import com.example.gudangapp.utils.response.DefaultResponse;
import com.example.gudangapp.utils.response.ListResponse;

public interface KantorService {
    ResponseEntity<DataResponse<Kantor>> createKantor(Kantor req);
    ResponseEntity<DataResponse<Kantor>> updateKantor(Long id, Kantor req);
    ResponseEntity<DefaultResponse> deleteKantor(Long id);
    ResponseEntity<DataResponse<Kantor>> getKantor(Long id);
    ResponseEntity<ListResponse<Kantor>> getAll();
}


