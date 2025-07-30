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
@Table(name = "GUDANG")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "kode_gudang", unique = true)
    private String kodeGudang;

    @NotNull
    @Size(max = 10)
    @Column(name = "kode_kantor")
    private String kodeKantor;

    private String alamat;
    private BigDecimal latitude;
    private BigDecimal longitude;

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
