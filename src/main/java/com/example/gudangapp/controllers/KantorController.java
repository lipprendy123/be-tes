package com.example.gudangapp.controllers;

import com.example.gudangapp.model.pojo.Kantor;
import com.example.gudangapp.services.KantorService;
import com.example.gudangapp.utils.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kantor")
@RequiredArgsConstructor
@Tag(name = "Kantor", description = "Manajemen data kantor")
public class KantorController {

    private final KantorService kantorService;

    @PostMapping
    @Operation(summary = "Create Kantor")
    public ResponseEntity<DataResponse<Kantor>> create(@Validated @RequestBody Kantor req) {
        return kantorService.createKantor(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Kantor")
    public ResponseEntity<DataResponse<Kantor>> update(@PathVariable Long id, @Validated @RequestBody Kantor req) {
        return kantorService.updateKantor(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Kantor")
    public ResponseEntity<DefaultResponse> delete(@PathVariable Long id) {
        return kantorService.deleteKantor(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Kantor By Id")
    public ResponseEntity<DataResponse<Kantor>> get(@PathVariable Long id) {
        return kantorService.getKantor(id);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Get All Kantor")
    public ResponseEntity<ListResponse<Kantor>> getAll() {
        return kantorService.getAll();
    }
}
