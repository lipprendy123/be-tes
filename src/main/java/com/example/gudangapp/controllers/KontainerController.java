package com.example.gudangapp.controllers;

import com.example.gudangapp.model.pojo.Kontainer;
import com.example.gudangapp.services.KontainerService;
import com.example.gudangapp.utils.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/kontainer")
@RequiredArgsConstructor
@Tag(name = "Kontainer", description = "API untuk manajemen kontainer")
public class KontainerController {

    private final KontainerService kontainerService;

    @PostMapping
    @Operation(summary = "Buat kontainer")
    public ResponseEntity<DataResponse<Kontainer>> create(@Valid @RequestBody Kontainer req) {
        return kontainerService.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update kontainer")
    public ResponseEntity<DataResponse<Kontainer>> update(@PathVariable Long id, @Valid @RequestBody Kontainer req) {
        return kontainerService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Hapus kontainer")
    public ResponseEntity<DefaultResponse> delete(@PathVariable Long id) {
        return kontainerService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get kontainer by ID")
    public ResponseEntity<DataResponse<Kontainer>> getById(@PathVariable Long id) {
        return kontainerService.getById(id);
    }

    @GetMapping("/findAll")
    @Operation(summary = "Get semua kontainer")
    public ResponseEntity<ListResponse<Kontainer>> getAll() {
        return kontainerService.getAll();
    }

    @GetMapping
    @Operation(summary = "Get Kontainer by kodeGudang, kodeKantor, nomorDokumen")
    public ResponseEntity<ListResponse<Kontainer>> findByFilters(
            @RequestParam String kodeGudang,
            @RequestParam String kodeKantor,
            @RequestParam String nomorDokumen) {
            return kontainerService.findByFilters(kodeGudang, kodeKantor, nomorDokumen);
    }
}