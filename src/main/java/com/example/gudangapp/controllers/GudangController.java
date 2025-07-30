package com.example.gudangapp.controllers;

import com.example.gudangapp.model.pojo.Gudang;
import com.example.gudangapp.services.GudangService;
import com.example.gudangapp.utils.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/gudang")
@RequiredArgsConstructor
@Tag(name = "Gudang", description = "Manajemen data gudang")
public class GudangController {
    private final GudangService gudangService;

    @PostMapping
    @Operation(summary =  "Create Gudang")
    public ResponseEntity<DataResponse<Gudang>> create(@Valid @RequestBody Gudang req) {
        return gudangService.createGudang(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Gudang")
    public ResponseEntity<DataResponse<Gudang>> update(@PathVariable Long id, @Valid @RequestBody Gudang req) {
        return gudangService.updateGudang(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation
    public ResponseEntity<DefaultResponse> delete(@PathVariable Long id) {
        return gudangService.deleteGudang(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Gudang By Id")
    public ResponseEntity<DataResponse<Gudang>> get(@PathVariable Long id) {
        return gudangService.getGudang(id);
    }

    @GetMapping("/findAll")
    @Operation(summary = "List All Gudang")
    public ResponseEntity<ListResponse<Gudang>> getAll() {
        return gudangService.getAll();
    }

    @GetMapping("/by-kantor/{kodeKantor}")
    @Operation(summary = "Get Gudang By Kode Kantor")
    public ResponseEntity<ListResponse<Gudang>> getByKodeKantor(@PathVariable String kodeKantor) {
    return gudangService.getGudangByKodeKantor(kodeKantor);
}
}
