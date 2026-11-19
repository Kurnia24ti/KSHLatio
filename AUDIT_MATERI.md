# 📋 AUDIT MATERI PERKULIAHAN MOBILE PROGRAMMING (P2 - P11)

Dokumen ini berisi hasil audit penerapan seluruh materi perkuliahan dari Pertemuan 2 hingga Pertemuan 11 pada proyek aplikasi **SawitMON** (`com.example.KSHLatio`).

---

## 1. Pemetaan & Hasil Audit per Pertemuan

| Pertemuan | Konsep / Materi Utama | File Kode Terkait | Status | Penjelasan Implementasi |
|---|---|---|---|---|
| **P1** | Setup Android Studio, SDK, Gradle DSL, ViewBinding | `build.gradle.kts`, `AndroidManifest.xml` | **SESUAI** | Proyek terkonfigurasi dengan ViewBinding aktif dan terkompilasi sempurna. |
| **P2** | Kotlin Dasar, Activity, ConstraintLayout, `findViewById` | `home/pertemuan_2/BelajarKotlin.kt`, `SecondActivity.kt`, `activity_second.xml` | **SESUAI** | `BelajarKotlin.kt` memuat contoh fungsi Kotlin. `SecondActivity` mendemonstrasikan `findViewById` (`inputNama`, `btnSubmit`, `textNama`) & Toast/Log. |
| **P3** | ViewBinding, Layout Attributes & Resource, Intent | `home/pertemuan_3/ThirdActivity.kt`, `ThirdResultActivity.kt`, `activity_third.xml` | **SESUAI** | `ActivityThirdBinding` digunakan untuk membaca input `inputNoTujuan` dan mengirim Intent ke `ThirdResultActivity`. |
| **P4** | Intent Extra, Activity Lifecycle, Snackbar, Dialog, CardView | `home/pertemuan_4/FourthActivity.kt`, `activity_fourth.xml`, `BaseActivity.kt` | **SESUAI** | Mendemonstrasikan `putExtra`/`getStringExtra`, Log lifecycle (`onCreate`, `onStart`, `onDestroy`), `Snackbar`, `MaterialAlertDialogBuilder`, dan `MaterialCardView`. |
| **P5** | Toolbar, Option Menu, CoordinatorLayout, WebView, Vector Asset | `home/pertemuan_5/FifthActivity.kt`, `WebActivity.kt`, `activity_fifth.xml`, `main_menu.xml` | **SESUAI** | Toolbar + back indicator, Option Menu (Search, Settings), CoordinatorLayout + AppBarLayout (`scroll|enterAlways`) + NestedScrollView, serta WebViewClient. |
| **P6** | SharedPreferences, SessionManager, Splash Screen, Dark Mode | `data/SessionManager.kt`, `SplashActivity.kt`, `LoginActivity.kt`, `AkunFragment.kt` | **SESUAI** | SessionManager membungkus sesi `isLogin`, `username`, `role`, `dark_mode`. SplashActivity mengecek sesi & tema. Login & Logout berfungsi penuh. |
| **P7** | Fragment Transactions, BaseActivity + BottomNavigationView | `BaseActivity.kt`, `home/pertemuan_6/SixthActivity.kt`, `Satu/Dua/TigaFragment.kt` | **SESUAI** | `BaseActivity` mengelola 4 tab utama (Dashboard, Kebun, Aktivitas, Akun). `SixthActivity` mendemonstrasikan transaksi fragment manual tanpa penumpukan backstack. |
| **P8 & P9** | Material Components (TextInputLayout, ChipGroup, Wave Vector, GridLayout) | `home/pertemuan_9/NinthActivity.kt`, `activity_ninth.xml`, `bg_header_wave.xml` | **SESUAI** | `TextInputLayout` (`OutlinedBox`), `ChipGroup` filter, `GridLayout` 3 kolom, `MaterialButton`, dan `bg_header_wave.xml` vector background. |
| **P10** | ListView (ArrayAdapter & SimpleAdapter), Custom Adapter + Glide | `AkunFragment.kt`, `message/MessageFragment.kt`, `MessageAdapter.kt`, `MessageModel.kt` | **SESUAI** | `AkunFragment` memuat `ListView` (`simple_list_item_2`) via `SimpleAdapter`. `MessageFragment` memuat daftar pesan dengan Custom `MessageAdapter` + Glide image loading. |
| **P11** | TabLayout + ViewPager2, RecyclerView & Onboarding DotsIndicator | `home/pertemuan_10/TenthActivity.kt`, `aktivitas/AktivitasFragment.kt`, `message/tutorial/TutorialMessageActivity.kt` | **SESUAI** | `TenthActivity` memuat `TabLayout` + `ViewPager2` + `RecyclerView` (50 produk + Glide). `AktivitasFragment` memuat `TabLayout` + Badges. Tutorial memuat `ViewPager2` + `DotsIndicator`. |

---

## 2. Ringkasan Kesesuaian

- **Kepatuhan Penamaan**: Seluruh nama package (`pertemuan_2` .. `pertemuan_10`), nama class (`SecondActivity`, `ThirdActivity`, dst.), dan ID komponen (`inputNama`, `btnSubmit`, `inputNoTujuan`, `btnKirim`, `listViewItems`, `listMessageItems`, `chipGroupFilter`, `tab_layout`, `view_pager`, `rvProducts`, `tutorialMessageViewPager`, `dot_indicator`) dibuat 100% persis seperti modul praktikum dosen.
- **Kepatuhan Fitur SawitMON**: Fitur utama SawitMON (Dashboard, Kebun, Aktivitas, Akun, Notifikasi, Detail Snack Latio, Web Help, Dark Mode) dipertahankan dan berjalan sempurna.