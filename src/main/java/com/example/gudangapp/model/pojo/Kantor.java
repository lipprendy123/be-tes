package com.example.gudangapp.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "KANTOR")
public class Kantor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String namaKantor;

    @NotNull
    @Size(max = 10)
    @Column(unique = true)
    private String kodeKantor;

    private String alamatKantor;

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
