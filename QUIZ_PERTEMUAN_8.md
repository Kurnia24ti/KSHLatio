# PERTEMUAN 8 - QUIZ TIF
**Nama Application**: KSHLatio / SawitMON  
**Package Name**: `com.example.KSHLatio`  
**Mata Kuliah**: Pemrograman Mobile / TIF  

---

## 📌 DAFTAR ISI
1. [Ringkasan Soal & Arsitektur Solusi](#-ringkasan-soal--arsitektur-solusi)
2. [Soal 1 - Registration With Gmail](#-soal-1---registration-with-gmail)
   - [Layout 1A: Layout Login (`activity_login.xml`)](#layout-1a-layout-login-activity_loginxml)
   - [Layout 1B: Layout Input Gmail (`activity_gmail_input.xml`)](#layout-1b-layout-input-gmail-activity_gmail_inputxml)
   - [Code 1: Logic Input Gmail & Validasi Domain (`GmailInputActivity.kt`)](#code-1-logic-input-gmail--validasi-domain-gmailinputactivitykt)
3. [Soal 2 - Validasi, Login & SharedPreferences](#-soal-2---validasi-login--sharedpreferences)
   - [Layout 2A: Layout Registrasi (`activity_register.xml`)](#layout-2a-layout-registrasi-activity_registerxml)
   - [Layout 2B: Layout Registrasi Berhasil (`activity_register_success.xml`)](#layout-2b-layout-registrasi-berhasil-activity_register_successxml)
   - [Code 2A: Logic Registrasi & SharedPreferences (`RegisterActivity.kt`)](#code-2a-logic-registrasi--sharedpreferences-registeractivitykt)
   - [Code 2B: Logic Halaman Informasi Registrasi Berhasil (`RegisterSuccessActivity.kt`)](#code-2b-logic-halaman-informasi-registrasi-berhasil-registersuccessactivitykt)
   - [Code 2C: Update Logic Login & Multi-Condition Auth (`LoginActivity.kt`)](#code-2c-update-logic-login--multi-condition-auth-loginactivitykt)
   - [Config: Update `AndroidManifest.xml`](#config-update-androidmanifestxml)
4. [Soal 3 - 10 Error & Penjelasannya (Siap Salin ke tldraw / Laporan)](#-soal-3---10-error--penjelasannya-siap-salin-ke-tldraw--laporan)

---

## 📐 RINGKASAN SOAL & ARSITEKTUR SOLUSI

```
[ Login Screen ] ──────► Tombol "Register With Gmail"
       │                                │
       │                                ▼
       │                    [ Gmail Input Screen ]
       │                    (Input Email @gmail.com)
       │                                │ (Validasi Domain + MaterialAlertDialog)
       │                                ▼
       │                     [ Register Screen ]
       │                (Email Otomatis Filled & Locked)
       │                (Input Nama, Username, Password)
       │                                │ (Validasi No-Space & Min 6 Char)
       │                                ▼
       │                    [ Simpan ke SharedPreferences ]
       │                                │
       │                                ▼
       │                     [ Registrasi Berhasil ]
       │                                │
       ▼                                ▼
[ Proses Login ] ◄──────────────────────┘
  ├─ Kondisi 1: username == password
  └─ Kondisi 2: Match SharedPreferences (savedUser & savedPass)
       │
       ├─► (Berhasil)  ──► [ Home / BaseActivity ]
       └─► (Gagal)     ──► [ MaterialAlertDialog Error ]
```

---

## 📝 SOAL 1 - REGISTRATION WITH GMAIL

### Requirement Soal 1:
1. Tambahkan tombol "Register With Gmail" di halaman Login.
2. Saat ditekan, buka halaman input email (hanya menginputkan email).
3. Validasi:
   - Email tidak boleh kosong.
   - Email harus menggunakan domain `@gmail.com`.
4. Jika valid:
   - Navigasi ke halaman Registrasi.
   - Email otomatis terbawa/terisi di halaman Registrasi.
5. Jika tidak valid:
   - Tampilkan pesan error menggunakan `MaterialAlertDialog`.

---

### Layout 1A: Layout Login (`activity_login.xml`)
> **Tambahkan tombol "Register With Gmail"** pada form login di bagian bawah tombol Google / Registrasi biasa.

```xml
<!-- Tombol Register With Gmail (Soal 1) -->
<com.google.android.material.button.MaterialButton
    android:id="@+id/btnRegisterGmail"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:layout_marginTop="12dp"
    android:text="Register With Gmail"
    android:textColor="@color/white"
    android:textSize="14sp"
    android:textStyle="bold"
    app:backgroundTint="#EA4335"
    app:cornerRadius="12dp"
    app:icon="@drawable/ic_mail"
    app:iconGravity="textStart"
    app:iconTint="@color/white" />
```

---

### Layout 1B: Layout Input Gmail (`activity_gmail_input.xml`)
> File Path: `app/src/main/res/layout/activity_gmail_input.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:background="@color/white">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="24dp">

        <ImageView
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_gravity="center"
            android:layout_marginTop="40dp"
            android:src="@drawable/ic_mail"
            app:tint="@color/green_primary"
            tools:ignore="ContentDescription" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="16dp"
            android:text="Register With Gmail"
            android:textColor="@color/text_dark"
            android:textSize="22sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="8dp"
            android:text="Masukkan alamat email Gmail Anda untuk melanjutkan pendaftaran."
            android:textAlignment="center"
            android:textColor="@color/text_gray"
            android:textSize="13sp" />

        <!-- Input Email Only -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="32dp"
            android:text="Alamat Email Gmail"
            android:textColor="@color/text_dark"
            android:textSize="13sp"
            android:textStyle="bold" />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="52dp"
            android:layout_marginTop="8dp"
            android:background="@drawable/bg_input"
            android:gravity="center_vertical"
            android:orientation="horizontal"
            android:paddingStart="14dp"
            android:paddingEnd="14dp">

            <ImageView
                android:layout_width="20dp"
                android:layout_height="20dp"
                android:src="@drawable/ic_mail"
                app:tint="@color/text_gray"
                tools:ignore="ContentDescription" />

            <EditText
                android:id="@+id/etGmailEmail"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_marginStart="10dp"
                android:autofillHints="emailAddress"
                android:background="@null"
                android:hint="contoh@gmail.com"
                android:inputType="textEmailAddress"
                android:textColor="@color/text_dark"
                android:textColorHint="#9AA8A0"
                android:textSize="14sp" />
        </LinearLayout>

        <com.google.android.material.button.MaterialButton
            android:id="@+id/btnLanjutRegister"
            android:layout_width="match_parent"
            android:layout_height="54dp"
            android:layout_marginTop="24dp"
            android:text="Lanjutkan Registrasi"
            android:textColor="@color/white"
            android:textSize="15sp"
            android:textStyle="bold"
            app:backgroundTint="@color/green_primary"
            app:cornerRadius="14dp" />

    </LinearLayout>
</ScrollView>
```

---

### Code 1: Logic Input Gmail & Validasi Domain (`GmailInputActivity.kt`)
> File Path: `app/src/main/java/com/example/KSHLatio/GmailInputActivity.kt`

```kotlin
package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GmailInputActivity : AppCompatActivity() {

    private lateinit var etGmailEmail: EditText
    private lateinit var btnLanjutRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail_input)

        etGmailEmail = findViewById(R.id.etGmailEmail)
        btnLanjutRegister = findViewById(R.id.btnLanjutRegister)

        btnLanjutRegister.setOnClickListener {
            val emailInput = etGmailEmail.text.toString().trim()

            // Validasi 1: Email tidak boleh kosong
            if (emailInput.isEmpty()) {
                showMaterialErrorDialog(
                    title = "Validasi Gagal",
                    message = "Email tidak boleh kosong. Silakan masukkan email Gmail Anda."
                )
                return@setOnClickListener
            }

            // Validasi 2: Email harus menggunakan domain @gmail.com
            if (!emailInput.lowercase().endsWith("@gmail.com")) {
                showMaterialErrorDialog(
                    title = "Domain Email Tidak Valid",
                    message = "Email harus menggunakan domain @gmail.com (contoh: nama@gmail.com)."
                )
                return@setOnClickListener
            }

            // Jika Valid -> Arahkan ke Halaman Registrasi membawa Extra Email
            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra(RegisterActivity.EXTRA_EMAIL, emailInput)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun showMaterialErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Selesai") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}
```

---

## 📝 SOAL 2 - VALIDASI, LOGIN & SHAREDPREFERENCES

### Requirement Soal 2:
1. **Input Halaman Registrasi**:
   - Nama
   - Email (Otomatis terisi dari Gmail Input & tidak boleh diubah/non-editable)
   - Username
   - Password
2. **Validasi Registrasi**:
   - Semua field wajib diisi.
   - Password minimal 6 karakter.
   - Username tidak boleh mengandung spasi.
3. **Simpan SharedPreferences**:
   - Jika lulus validasi, simpan seluruh data registrasi (Nama, Email, Username, Password) ke `SharedPreferences`.
   - Tampilkan halaman informasi "Registrasi Berhasil".
4. **Login Logic**:
   - User boleh masuk jika memenuhi salah satu kondisi:
     - Kondisi A: `username == password`
     - Kondisi B: `username` dan `password` sesuai data yang tersimpan di `SharedPreferences`.
   - Jika berhasil: Masuk ke Home (`BaseActivity`).
   - Jika gagal: Tampilkan error menggunakan `MaterialAlertDialog`.

---

### Layout 2A: Layout Registrasi (`activity_register.xml`)
> File Path: `app/src/main/res/layout/activity_register.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:background="@color/white">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="24dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="Lengkapi Pendaftaran"
            android:textColor="@color/text_dark"
            android:textSize="24sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="6dp"
            android:text="Isi data akun Anda untuk menyelesaikan pembuatan akun SawitMON."
            android:textColor="@color/text_gray"
            android:textSize="13sp" />

        <!-- Field 1: Nama -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="20dp"
            android:text="Nama Lengkap"
            android:textColor="@color/text_dark"
            android:textSize="13sp"
            android:textStyle="bold" />

        <EditText
            android:id="@+id/etRegisterNama"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginTop="6dp"
            android:background="@drawable/bg_input"
            android:hint="Masukkan Nama Lengkap"
            android:inputType="textPersonName"
            android:paddingStart="14dp"
            android:paddingEnd="14dp"
            android:textColor="@color/text_dark"
            android:textSize="14sp" />

        <!-- Field 2: Email (Non-editable / Locked) -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="Alamat Email (Gmail)"
            android:textColor="@color/text_dark"
            android:textSize="13sp"
            android:textStyle="bold" />

        <EditText
            android:id="@+id/etRegisterEmail"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginTop="6dp"
            android:background="@drawable/bg_input"
            android:enabled="false"
            android:focusable="false"
            android:inputType="none"
            android:paddingStart="14dp"
            android:paddingEnd="14dp"
            android:textColor="#757575"
            android:textSize="14sp" />

        <!-- Field 3: Username -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="Username (Tanpa Spasi)"
            android:textColor="@color/text_dark"
            android:textSize="13sp"
            android:textStyle="bold" />

        <EditText
            android:id="@+id/etRegisterUsername"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginTop="6dp"
            android:background="@drawable/bg_input"
            android:hint="Masukkan Username"
            android:inputType="textNoSuggestions"
            android:paddingStart="14dp"
            android:paddingEnd="14dp"
            android:textColor="@color/text_dark"
            android:textSize="14sp" />

        <!-- Field 4: Password -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="16dp"
            android:text="Password (Min. 6 Karakter)"
            android:textColor="@color/text_dark"
            android:textSize="13sp"
            android:textStyle="bold" />

        <EditText
            android:id="@+id/etRegisterPassword"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginTop="6dp"
            android:background="@drawable/bg_input"
            android:hint="Masukkan Password"
            android:inputType="textPassword"
            android:paddingStart="14dp"
            android:paddingEnd="14dp"
            android:textColor="@color/text_dark"
            android:textSize="14sp" />

        <!-- Tombol Registrasi -->
        <com.google.android.material.button.MaterialButton
            android:id="@+id/btnProsesRegistrasi"
            android:layout_width="match_parent"
            android:layout_height="54dp"
            android:layout_marginTop="28dp"
            android:text="Registrasi Akun"
            android:textColor="@color/white"
            android:textSize="15sp"
            android:textStyle="bold"
            app:backgroundTint="@color/green_primary"
            app:cornerRadius="14dp" />

    </LinearLayout>
</ScrollView>
```

---

### Layout 2B: Layout Registrasi Berhasil (`activity_register_success.xml`)
> File Path: `app/src/main/res/layout/activity_register_success.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="32dp"
    android:background="@color/white">

    <ImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/ic_eco"
        app:tint="@color/green_primary" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="Registrasi Berhasil!"
        android:textColor="@color/text_dark"
        android:textSize="24sp"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/tvInfoRegistrasi"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:textAlignment="center"
        android:textColor="@color/text_gray"
        android:textSize="14sp"
        android:lineSpacingExtra="4dp" />

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btnKeHalamanLogin"
        android:layout_width="match_parent"
        android:layout_height="54dp"
        android:layout_marginTop="36dp"
        android:text="Kembali ke Halaman Login"
        android:textColor="@color/white"
        android:textSize="15sp"
        android:textStyle="bold"
        app:backgroundTint="@color/green_primary"
        app:cornerRadius="14dp" />

</LinearLayout>
```

---

### Code 2A: Logic Registrasi & SharedPreferences (`RegisterActivity.kt`)
> File Path: `app/src/main/java/com/example/KSHLatio/RegisterActivity.kt`

```kotlin
package com.example.KSHLatio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RegisterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EMAIL = "extra_email"
        const val PREF_NAME = "user_pref"
        const val KEY_NAMA = "registered_nama"
        const val KEY_EMAIL = "registered_email"
        const val KEY_USERNAME = "registered_username"
        const val KEY_PASSWORD = "registered_password"
    }

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistrasi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNama = findViewById(R.id.etRegisterNama)
        etEmail = findViewById(R.id.etRegisterEmail)
        etUsername = findViewById(R.id.etRegisterUsername)
        etPassword = findViewById(R.id.etRegisterPassword)
        btnRegistrasi = findViewById(R.id.btnProsesRegistrasi)

        // 1. Terima email dari GmailInputActivity & kunci field email agar tidak bisa diubah
        val emailFromExtra = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        etEmail.setText(emailFromExtra)
        etEmail.isEnabled = false // Tidak bisa diubah

        btnRegistrasi.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            // Validasi A: Semua field wajib diisi
            if (nama.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Semua field (Nama, Email, Username, Password) wajib diisi!")
                return@setOnClickListener
            }

            // Validasi B: Username tidak boleh mengandung spasi
            if (username.contains(" ")) {
                showErrorDialog("Username tidak boleh mengandung karakter spasi!")
                return@setOnClickListener
            }

            // Validasi C: Password minimal 6 karakter
            if (password.length < 6) {
                showErrorDialog("Password minimal harus 6 karakter!")
                return@setOnClickListener
            }

            // Simpan data registrasi ke SharedPreferences jika lulus validasi
            val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(KEY_NAMA, nama)
            editor.putString(KEY_EMAIL, email)
            editor.putString(KEY_USERNAME, username)
            editor.putString(KEY_PASSWORD, password)
            editor.apply()

            // Pindah ke Halaman Informasi "Registrasi Berhasil"
            val intentSuccess = Intent(this, RegisterSuccessActivity::class.java).apply {
                putExtra("USER_NAMA", nama)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intentSuccess)
            finish()
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
```

---

### Code 2B: Logic Halaman Informasi Registrasi Berhasil (`RegisterSuccessActivity.kt`)
> File Path: `app/src/main/java/com/example/KSHLatio/RegisterSuccessActivity.kt`

```kotlin
package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)

        val nama = intent.getStringExtra("USER_NAMA").orEmpty()
        val email = intent.getStringExtra("USER_EMAIL").orEmpty()

        val tvInfo = findViewById<TextView>(R.id.tvInfoRegistrasi)
        val btnKeLogin = findViewById<Button>(R.id.btnKeHalamanLogin)

        tvInfo.text = "Selamat $nama!\nAkun Anda ($email) telah berhasil didaftarkan dan tersimpan."

        btnKeLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
```

---

### Code 2C: Update Logic Login & Multi-Condition Auth (`LoginActivity.kt`)
> Update pada `LoginActivity.kt` untuk memproses tombol Register Gmail dan 2 kondisi Login.

```kotlin
package com.example.KSHLatio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmailOrUser = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegisterGmail = findViewById<Button>(R.id.btnRegisterGmail)

        // Toggle visibility password
        findViewById<ImageView>(R.id.ivToggle)?.setOnClickListener {
            passwordVisible = !passwordVisible
            etPassword?.transformationMethod =
                if (passwordVisible) HideReturnsTransformationMethod.getInstance()
                else PasswordTransformationMethod.getInstance()
            etPassword?.text?.length?.let { len -> etPassword.setSelection(len) }
        }

        // Action SOAL 1: Tombol "Register With Gmail"
        btnRegisterGmail?.setOnClickListener {
            val intent = Intent(this, GmailInputActivity::class.java)
            startActivity(intent)
        }

        // Action SOAL 2: Process Login dengan 2 Kondisi Syarat
        btnLogin?.setOnClickListener {
            val inputUser = etEmailOrUser?.text?.toString().orEmpty().trim()
            val inputPass = etPassword?.text?.toString().orEmpty()

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                showMaterialDialogError("Login Gagal", "Username/Email dan Password tidak boleh kosong.")
                return@setOnClickListener
            }

            // Ambil data SharedPreferences dari hasil registrasi
            val sharedPref = getSharedPreferences(RegisterActivity.PREF_NAME, Context.MODE_PRIVATE)
            val savedUsername = sharedPref.getString(RegisterActivity.KEY_USERNAME, null)
            val savedPassword = sharedPref.getString(RegisterActivity.KEY_PASSWORD, null)

            // Kondisi 1: username == password
            val isConditionOneValid = (inputUser == inputPass)

            // Kondisi 2: username dan password sesuai dengan data tersimpan di SharedPreferences
            val isConditionTwoValid = (savedUsername != null &&
                    savedPassword != null &&
                    inputUser == savedUsername &&
                    inputPass == savedPassword)

            // User boleh masuk jika MEMENUHI SALAH SATU KONDISI (1 ATAU 2)
            if (isConditionOneValid || isConditionTwoValid) {
                // Simpan status session login
                val sessionPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sessionPref.edit().apply {
                    putBoolean("isLoggedIn", true)
                    putString("username", inputUser)
                    apply()
                }

                Log.d("LOGIN_SUCCESS", "Login Berhasil via Kondisi 1: $isConditionOneValid | Kondisi 2: $isConditionTwoValid")

                // Redirect ke Home (BaseActivity)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Jika kedua kondisi GAGAL -> Tampilkan error dengan MaterialAlertDialog
                showMaterialDialogError(
                    title = "Login Gagal",
                    message = "Username atau password salah! Pastikan Anda memasukkan data registrasi yang benar atau username == password."
                )
            }
        }
    }

    private fun showMaterialDialogError(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Coba Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}
```

---

### Config: Update `AndroidManifest.xml`
> Daftarkan Activity baru pada `<application>` di `AndroidManifest.xml`.

```xml
<activity
    android:name=".GmailInputActivity"
    android:exported="false" />
<activity
    android:name=".RegisterActivity"
    android:exported="false" />
<activity
    android:name=".RegisterSuccessActivity"
    android:exported="false" />
```

---

## 📝 SOAL 3 - 10 ERROR & PENJELASANNYA (SIAP SALIN KE TLDRAW / LAPORAN)

Berikut adalah daftar 10 Error yang umum ditemukan selama proses pengerjaan Soal 1 dan Soal 2, lengkap dengan deskripsi, penyebab, serta potongan kode *Before Fix* dan *After Fix*:

---

### 1️⃣ Error 1: `NullPointerException` saat Mengakses View (`findViewById` sebelum `setContentView`)
* **Nama Error**: `java.lang.NullPointerException` / `UninitializedPropertyAccessException`
* **Deskripsi**: Aplikasi crash seketika saat `GmailInputActivity` dibuka.
* **Penyebab**: Fungsi `findViewById()` dipanggil di luar method `onCreate()` atau dipanggil sebelum baris `setContentView(R.layout.activity_gmail_input)`.

#### ❌ Before Fix Code:
```kotlin
class GmailInputActivity : AppCompatActivity() {
    // SALAH: findViewById dipanggil sebelum layout di-inflate di onCreate
    private val etGmailEmail: EditText = findViewById(R.id.etGmailEmail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail_input)
    }
}
```

#### ✅ After Fix Code:
```kotlin
class GmailInputActivity : AppCompatActivity() {
    private lateinit var etGmailEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail_input)
        
        // BENAR: Inisialisasi dilakukan setelah setContentView
        etGmailEmail = findViewById(R.id.etGmailEmail)
    }
}
```

---

### 2️⃣ Error 2: Validasi Email Lolos untuk Domain Selain Gmail (`contains` vs `endsWith`)
* **Nama Error**: `Logic Flaw - Invalid Email Domain Accepted`
* **Deskripsi**: Email seperti `user@gmail.com.id` atau `gmail.com@yahoo.com` diterima oleh sistem.
* **Penyebab**: Menggunakan fungsi `.contains("@gmail.com")` yang hanya mengecek keberadaan teks, bukan posisi domain di akhir string.

#### ❌ Before Fix Code:
```kotlin
// SALAH: Email "bebas@gmail.com.org" akan lolos validasi
if (!emailInput.contains("@gmail.com")) {
    showErrorDialog("Email harus @gmail.com")
}
```

#### ✅ After Fix Code:
```kotlin
// BENAR: Memastikan string diakhiri persis dengan "@gmail.com"
if (!emailInput.lowercase().endsWith("@gmail.com")) {
    showErrorDialog("Email harus menggunakan domain @gmail.com")
}
```

---

### 3️⃣ Error 3: Email Null di Halaman Registrasi (`Intent Extra Key Mismatch`)
* **Nama Error**: `NullPointerException / Empty Extra String`
* **Deskripsi**: Email di halaman Registrasi bernilai kosong/null meskipun user sudah mengisi email di halaman Gmail Input.
* **Penyebab**: Nama kunci (Key) `Intent Extra` pada pengirim (`GmailInputActivity`) berbeda dengan penerima (`RegisterActivity`).

#### ❌ Before Fix Code:
```kotlin
// Di GmailInputActivity (Pengirim):
intent.putExtra("EXTRA_EMAIL_GMAIL", emailInput)

// Di RegisterActivity (Penerima):
val email = intent.getStringExtra("email") // SALAH: Key "email" != "EXTRA_EMAIL_GMAIL"
```

#### ✅ After Fix Code:
```kotlin
// Di RegisterActivity (Gunakan Companion Object Key yang Konsisten):
companion object {
    const val EXTRA_EMAIL = "extra_email"
}

// Di GmailInputActivity (Pengirim):
intent.putExtra(RegisterActivity.EXTRA_EMAIL, emailInput)

// Di RegisterActivity (Penerima):
val email = intent.getStringExtra(EXTRA_EMAIL).orEmpty() // BENAR
```

---

### 4️⃣ Error 4: Email Masih Bisa Diubah di Halaman Registrasi (`Editable Field`)
* **Nama Error**: `UI Requirement Violation - Email Field Editable`
* **Deskripsi**: Field Email pada halaman Registrasi dapat diketik/diubah oleh user padahal soal mewajibkan email tidak boleh diubah.
* **Penyebab**: Lupa menonaktifkan atribut `android:enabled="false"` atau properti Kotlin `isEnabled = false`.

#### ❌ Before Fix Code:
```xml
<!-- SALAH: EditText tetap dapat diedit oleh user -->
<EditText
    android:id="@+id/etRegisterEmail"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

#### ✅ After Fix Code:
```xml
<!-- BENAR: Nonaktifkan via XML dan dikunci via Kotlin -->
<EditText
    android:id="@+id/etRegisterEmail"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:enabled="false"
    android:focusable="false" />
```
```kotlin
// Di RegisterActivity.kt:
etEmail.isEnabled = false
```

---

### 5️⃣ Error 5: Data Registrasi Hilang / Tidak Tersimpan di SharedPreferences (`Forgot apply/commit`)
* **Nama Error**: `Data Persistence Failure - SharedPreferences Not Written`
* **Deskripsi**: Saat mencoba Login dengan akun yang baru didaftarkan, data tidak ditemukan di `SharedPreferences`.
* **Penyebab**: Lupa memanggil `.apply()` atau `.commit()` setelah memasukkan data ke `SharedPreferences.Editor`.

#### ❌ Before Fix Code:
```kotlin
val sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
val editor = sharedPreferences.edit()
editor.putString("registered_username", username)
editor.putString("registered_password", password)
// SALAH: Lupa memanggil editor.apply()
```

#### ✅ After Fix Code:
```kotlin
val sharedPreferences = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
val editor = sharedPreferences.edit()
editor.putString("registered_username", username)
editor.putString("registered_password", password)
editor.apply() // BENAR: Data disimpan secara asinkron
```

---

### 6️⃣ Error 6: Username Mengandung Spasi Tetap Lolos Validasi
* **Nama Error**: `Logic Error - Space Validation Bypass`
* **Deskripsi**: Username seperti `"user name"` lolos registrasi padahal syarat soal "Username tidak boleh mengandung spasi".
* **Penyebab**: Hanya mengecek `isEmpty()` atau menggunakan `.trim()` tanpa memeriksa karakter spasi di dalam string.

#### ❌ Before Fix Code:
```kotlin
// SALAH: .trim() hanya menghapus spasi di awal & akhir string
if (username.trim().isEmpty()) {
    showErrorDialog("Username kosong")
}
```

#### ✅ After Fix Code:
```kotlin
// BENAR: Pengecekan spesifik apakah terdapat spasi di manapun dalam username
if (username.contains(" ")) {
    showErrorDialog("Username tidak boleh mengandung spasi!")
}
```

---

### 7️⃣ Error 7: Parameter Operator Panjang Password Terbalik (`<= 6` vs `< 6`)
* **Nama Error**: `Validation Edge Case Failure`
* **Deskripsi**: User memasukkan password tepat 6 karakter, namun sistem tetap menampilkan error "Password minimal 6 karakter".
* **Penyebab**: Menggunakan operator `<=` 6 yang menyebabkan panjang 6 tetap dianggap error.

#### ❌ Before Fix Code:
```kotlin
// SALAH: Password 6 karakter ikut ditolak
if (password.length <= 6) {
    showErrorDialog("Password minimal 6 karakter")
}
```

#### ✅ After Fix Code:
```kotlin
// BENAR: Password di bawah 6 karakter (0-5) yang ditolak
if (password.length < 6) {
    showErrorDialog("Password minimal 6 karakter")
}
```

---

### 8️⃣ Error 8: Logika Kondisi Login Salah Menggunakan Operator `&&` alih-alih `||`
* **Nama Error**: `Authentication Logic Error`
* **Deskripsi**: User tidak bisa login dengan SharedPreferences kecuali password-nya sama persis dengan username-nya.
* **Penyebab**: Menggunakan operator `&&` (AND) antara Kondisi 1 (`username == password`) dan Kondisi 2 (`SharedPreferences`), padahal soal menyatakan "memenuhi SALAH SATU kondisi".

#### ❌ Before Fix Code:
```kotlin
val cond1 = (inputUser == inputPass)
val cond2 = (inputUser == savedUser && inputPass == savedPass)

// SALAH: User dipaksa memenuhi KEDUA kondisi sekaligus
if (cond1 && cond2) {
    // Login Success
}
```

#### ✅ After Fix Code:
```kotlin
val cond1 = (inputUser == inputPass)
val cond2 = (savedUser != null && savedPass != null && inputUser == savedUser && inputPass == savedPass)

// BENAR: Memenuhi SALAH SATU kondisi (OR)
if (cond1 || cond2) {
    // Login Success
}
```

---

### 9️⃣ Error 9: Activity Crash Saat Membuka MaterialAlertDialog (`IllegalStateException Theme`)
* **Nama Error**: `java.lang.IllegalStateException: You need to use a Theme.MaterialComponents theme`
* **Deskripsi**: Aplikasi crash ketika menampilkan alert dialog error.
* **Penyebab**: Context Activity belum menerapkan tema Material Design Components.

#### ❌ Before Fix Code:
```kotlin
// SALAH: Jika Activity menggunakan tema AppCompat biasa tanpa Material components
MaterialAlertDialogBuilder(this)
    .setTitle("Error")
    .setMessage("Email tidak valid")
    .show()
```

#### ✅ After Fix Code:
```kotlin
// BENAR: Menggunakan ContextThemeWrapper atau memastikan style parent di styles.xml menggunakan MaterialComponents
val themedContext = ContextThemeWrapper(this, com.google.android.material.R.style.Theme_MaterialComponents_DayNight_Dialog)
MaterialAlertDialogBuilder(themedContext)
    .setTitle("Error")
    .setMessage("Email tidak valid")
    .setPositiveButton("OK", null)
    .show()
```

---

### 🔟 Error 10: `ActivityNotFoundException` Saat Klik Tombol Register Gmail
* **Nama Error**: `android.content.ActivityNotFoundException: Unable to find explicit activity class`
* **Deskripsi**: Aplikasi crash saat tombol "Register With Gmail" ditekan.
* **Penyebab**: `GmailInputActivity` atau `RegisterActivity` belum didaftarkan pada file `AndroidManifest.xml`.

#### ❌ Before Fix Code:
```xml
<!-- SALAH: AndroidManifest.xml belum mendaftarkan activity baru -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application ...>
        <activity android:name=".LoginActivity" />
    </application>
</manifest>
```

#### ✅ After Fix Code:
```xml
<!-- BENAR: Daftarkan semua Activity baru di AndroidManifest.xml -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application ...>
        <activity android:name=".LoginActivity" />
        <activity android:name=".GmailInputActivity" android:exported="false" />
        <activity android:name=".RegisterActivity" android:exported="false" />
        <activity android:name=".RegisterSuccessActivity" android:exported="false" />
    </application>
</manifest>
```

---
*Dokumen ini dibuat secara otomatis dan siap digunakan sebagai laporan Quiz Pertemuan 8 Pemrograman Mobile.*
