# Gudang & Kontainer Management API

Sistem Backend RESTful API ini dibangun menggunakan **Spring Boot 2.7.18** dan **Java 17** untuk melakukan manajemen data **Kantor**, **Gudang**, dan **Kontainer**, termasuk fitur relasional antar entitas serta pencarian berdasarkan filter tertentu.

## Fitur Utama

- CRUD **KANTOR**
- CRUD **GUDANG** (relasi FK ke KANTOR)
- CRUD **KONTAINER** (relasi FK ke GUDANG + KANTOR)
- Pencarian Kontainer berdasarkan filter: `kodeGudang`, `kodeKantor`, `nomorDokumen`
- Pencarian Gudang berdasarkan `kodeKantor`
- Tanggal dan waktu mengikuti format `dd-MM-yyyy HH:mm:ss`
- `waktuRekam` dan `waktuUpdate` otomatis terisi
- Logging dengan **Log4j2**
- Standardized API Response Wrapper (`DataResponse`, `ListResponse`, dll)
- Swagger (OpenAPI 3) untuk dokumentasi endpoint

## Endpoint

### Gudang

- **Create Gudang**  
  `POST /api/gudang`  
  Input: `kodeGudang`, `kodeKantor` (relasi), `alamat`, `latitude`, `longitude`

- **Get All Gudang**  
  `GET /api/gudang/findAll`  
  Menampilkan seluruh data gudang.

- **Get Gudang by Kode Kantor**  
  `GET /api/gudang/{kodeKantor}`  
  Contoh: `GET /api/gudang/KP001`

- **Update Gudang**  
  `PUT /api/gudang/{id}`  
  Contoh: `PUT /api/gudang/2`

- **Delete Gudang**  
  `DELETE /api/gudang/{id}`  
  Contoh: `DELETE /api/gudang/2`

---

### Kantor

- **Create Kantor**  
  `POST /api/kantor`  
  Input: `namaKantor`, `kodeKantor`, `alamatKantor`

- **Get All Kantor**  
  `GET /api/kantor/findAll`  
  Menampilkan seluruh data kantor.

- **Get Kantor by ID**  
  `GET /api/kantor/{id}`  
  Contoh: `GET /api/kantor/2`

- **Update Kantor**  
  `PUT /api/kantor/{id}`  
  Contoh: `PUT /api/kantor/1`

- **Delete Kantor**  
  `DELETE /api/kantor/{id}`  
  Contoh: `DELETE /api/kantor/1`

---

### Kontainer

- **Create Kontainer**  
  `POST /api/kontainer`  
  Input: `kodeGudang`, `kodeKantor`, `nomorKontainer`, `status`, `tipe`, `tanggalTiba`, `bruto`, `ukuranKontainer`, `nomorDokumen`

- **Get All Kontainer**  
  `GET /api/kontainer/findAll`  
  Menampilkan seluruh data kontainer.

- **Get Kontainer by Filter**  
  `GET /api/kontainer?kodeGudang=GD007&kodeKantor=KP009&nomorDokumen=DO123456`  
  Gunakan query param untuk filter data kontainer.

- **Update Kontainer**  
  `PUT /api/kontainer/{id}`  
  Contoh: `PUT /api/kontainer/2`

- **Delete Kontainer**  
  `DELETE /api/kontainer/{id}`  
  Contoh: `DELETE /api/kontainer/2`

## Tech Stack

- Java 17
- Spring Boot 2.7.18
- Spring Data JPA + Hibernate
- PostgreSQL (via JPA)
- Lombok
- Log4j2
- Swagger (Springdoc OpenAPI)
- Maven

## Format Waktu

- Tanggal: `dd-MM-yyyy`
- Tanggal & Waktu: `dd-MM-yyyy HH:mm:ss`

## Validasi & Aturan

- Semua field **wajib diisi** (tidak boleh `null`)
- `kode_kantor` dan `kode_gudang` harus valid (berelasi)
- `waktuRekam` hanya diisi saat insert
- `waktuUpdate` hanya diubah saat update

## Cara Menjalankan Project

1. Clone repository ini:
   bash
   git clone <repository-url>
   cd nama-folder-project
