package com.example.KSHLatio.home.pertemuan_2

fun main() {
    println("=== PERTEMUAN 2: BELAJAR KOTLIN DASAR ===")

    val namaApp = "SawitMON"
    var versi = 1.0
    val totalPanen: Int = 1477

    println("Nama Aplikasi: $namaApp, Versi: $versi, Total Panen: $totalPanen Kg")

    if (totalPanen > 1000) {
        println("Hasil panen bulan ini sangat memuaskan!")
    } else {
        println("Hasil panen perlu ditingkatkan.")
    }

    val daftarBlok = listOf("Blok A", "Blok B", "Blok C", "Blok D")
    println("Daftar Blok Lahan:")
    for (blok in daftarBlok) {
        println("- $blok")
    }
}