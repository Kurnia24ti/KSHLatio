# 🛠️ LAPORAN PERBAIKAN & IMPLEMENTASI MATERI SAWITMON

Dokumen ini mencatat seluruh tindakan perbaikan, pembuatan file baru, perbaikan error kompilasi, serta hasil pengujian aplikasi **SawitMON** (`com.example.KSHLatio`).

---

## 1. Perubahan & File Baru yang Dibuat

### A. Modul Latihan Perkuliahan (Package `home/`)
1. **Pertemuan 2 (`home/pertemuan_2/`)**:
   - `BelajarKotlin.kt`: Contoh variabel, tipe data, kondisional, perulangan.
   - `SecondActivity.kt` & `activity_second.xml`: Implementasi `findViewById` (`inputNama`, `btnSubmit`, `textNama`).
2. **Pertemuan 3 (`home/pertemuan_3/`)**:
   - `ThirdActivity.kt`, `ThirdResultActivity.kt`, `activity_third.xml`, `activity_third_result.xml`: Implementasi ViewBinding (`inputNoTujuan`, `btnKirim`).
3. **Pertemuan 4 (`home/pertemuan_4/`)**:
   - `FourthActivity.kt` & `activity_fourth.xml`: Intent extra (`name`, `from`, `age`), Log lifecycle, `btnShowSnackbar`, `btnShowAlertDialog`, `MaterialCardView`.
4. **Pertemuan 5 (`home/pertemuan_5/`)**:
   - `FifthActivity.kt` & `activity_fifth.xml`: Toolbar + back, Option Menu (`main_menu.xml`), CoordinatorLayout, `btnWebView` ke `WebActivity`.
5. **Pertemuan 6 (`home/pertemuan_6/`)**:
   - `SixthActivity.kt`, `activity_sixth.xml`, `SatuFragment.kt`, `DuaFragment.kt`, `TigaFragment.kt`: Transaksi Fragment manual (`btnFragment1..3`).
6. **Pertemuan 9 (`home/pertemuan_9/`)**:
   - `NinthActivity.kt`, `activity_ninth.xml`, `bg_header_wave.xml`, `edit_text_background.xml`: `TextInputLayout`, `ChipGroup`, `GridLayout` 3 kolom.
7. **Pertemuan 10 (`home/pertemuan_10/`)**:
   - `TenthActivity.kt`, `activity_tenth.xml`, `TenthTabsAdapter.kt`, `TabAFragment.kt`, `TabBFragment.kt`, `TabCFragment.kt`, `ProductModel.kt`, `ProductAdapter.kt`: `TabLayout` + `ViewPager2` + `RecyclerView` 50 produk dengan Glide.

### B. Manajamen Sesi & Data (`data/`)
- `SessionManager.kt`: Wrapper SharedPreferences `"user_pref"` (`isLogin`, `username`, `role`, `isDarkMode`).

### C. Pusat Notifikasi & Pesan (`message/`)
- `NotifikasiActivity.kt` & `activity_notifikasi.xml`: Activity wadah pengelola notifikasi.
- `MessageFragment.kt` & `fragment_message.xml`: Fragment pesan dengan Toolbar dan Option Menu tutorial.
- `MessageModel.kt` & `MessageAdapter.kt`: Custom Adapter dengan Glide image loading & Snackbar callback.
- `message/tutorial/TutorialMessageActivity.kt`, `TutorialFragmentAdapter.kt`, `TutorialItemFragment.kt`, `activity_tutorial_message.xml`: Onboarding tutorial dengan `ViewPager2` + `DotsIndicator`.

### D. Fitur Utama SawitMON
- `AkunFragment.kt` & `fragment_akun.xml`: Menambahkan `ListView` (`simple_list_item_2`) via `SimpleAdapter` dan Switch Dark Mode.
- `HomeFragment.kt` & `fragment_home.xml`: Menambahkan section **"Lab Praktikum (Modul Pertemuan 2 - 10)"** untuk mengakses seluruh modul latihan praktikum secara cepat.
- `AktivitasFragment.kt` & `fragment_aktivitas.xml`: Integrasi `ChipGroup` filter, `CalendarView` interaktif, serta `TabLayout` + `ViewPager2` + Badges.

---

## 2. Hasil Kompilasi & Pengujian Gradle

- **Gradle Sync**: `Sync finished successfully.` (Dependencies: `androidx.gridlayout`, `com.github.bumptech.glide`, `androidx.recyclerview`, `androidx.viewpager2`, `com.tbuonomo:dotsindicator`).
- **Gradle Assemble Debug**: `Build finished successfully.` (0 Compile Errors).
- **Pengujian Alur Navigasi**:
  1. **SplashActivity** $\rightarrow$ Mengecek sesi login & Dark Mode $\rightarrow$ langsung masuk ke `BaseActivity`.
  2. **Dashboard (`HomeFragment`)** $\rightarrow$ Memuat grafik panen, kartu statistik, dan section **Lab Praktikum** (P2–P10).
  3. **Aktivitas (`AktivitasFragment`)** $\rightarrow$ Memuat Filter Chip, Kalender Interaktif, dan Tab ViewPager2 (Pengajuan, Penanganan, Panen).
  4. **Akun (`AkunFragment`)** $\rightarrow$ Memuat ListView SimpleAdapter, Switch Dark Mode, dan Logout.
  5. **Notifikasi (`NotifikasiActivity`)** $\rightarrow$ Memuat ListView Custom Adapter + Glide, serta menu Tutorial Onboarding DotsIndicator.