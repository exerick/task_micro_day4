package com.day4.spring.security.task1.bukuLog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity(name = "buku")
public class BukuModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    private String judul;

    @NotBlank(message = "Penulis tidak boleh kosong")
    private String penulis;

    @Positive(message = "Tahun terbit harus berupa angka positif")
    private Integer tahunTerbit;
}
