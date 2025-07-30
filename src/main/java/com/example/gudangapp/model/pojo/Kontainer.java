package com.example.gudangapp.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "KONTAINER")
public class Kontainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Size(max = 10)
    private String kodeGudang;

    @NotNull
    @Size(max = 10)
    private String kodeKantor;

    @NotNull
    @Size(max = 20)
    private String nomorKontainer;

    @NotNull
    @Size(max = 10)
    private String ukuranKontainer;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime tanggalTiba;

    private BigDecimal bruto;

    private String nomorDokumen;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime waktuRekam;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime waktuUpdate;

    @PrePersist
    public void onCreate() {
        waktuRekam = LocalDateTime.now();
        waktuUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        waktuUpdate = LocalDateTime.now();
    }
}
