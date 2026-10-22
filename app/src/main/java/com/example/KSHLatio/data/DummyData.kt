@file:Suppress("PackageName")

package com.example.KSHLatio.data

object DummyData {
    data class Aktivitas(
        val judul: String,
        val detail: String,
        val status: String,
        val waktu: String
    )

    val aktivitasTerbaru = listOf(
        Aktivitas("Pengajuan B.A. 4", "Blok C · Panen selesai", "Diajukan", "08.42"),
        Aktivitas("Pengajuan B.A. 6", "Blok A · Menunggu verifikasi", "Diajukan", "08.16"),
        Aktivitas("Pengajuan B.A. 1", "Blok D · Laporan baru", "Diajukan", "Kemarin")
    )

    val panenBulanan = listOf(
        "Jan" to 820f, "Feb" to 960f, "Mar" to 1100f,
        "Apr" to 1240f, "Mei" to 1320f, "Jun" to 1477f
    )

    val bulan = arrayOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )

    val periode = arrayOf("3 bulan", "6 bulan", "12 bulan")
}