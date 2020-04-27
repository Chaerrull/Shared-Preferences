package latihansharedpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.app.chaerrull.latihansharedpreferences.model.UserModel;
import latihansharedpreference.HomeActivity;
import latihansharedpreference.R;
import latihansharedpreferences.utils.Preferences;

/*
Mohammad Chaerrull Febriansyah
10117171 - IF5

26 April 2020 : Membuat Tampilan Aplikasi dan Mengimplementasi source code Shared Preferences
27 April 2020 : Membetulkan error-error yang muncul

 */

public class LoginActivity extends AppCompatActivity {

    private TextView txtMasuk;
    private TextView txtRegister;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declareView();

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), latihansharedpreferences.RegisterActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    }


    private void declareView() {

        txtRegister = findViewById(R.id.txt_login_register);
        txtMasuk = findViewById(R.id.txt_login_masuk);
        edtUsername = findViewById(R.id.edt_login_username);
        edtPassword = findViewById(R.id.edt_login_password);

    }

    private void validasiLogin() {

        edtUsername.setError(null);
        edtPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();


       if (TextUtils.isEmpty(userName)) {
            edtUsername.setError("Harus diisi");
            fokus = edtUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            edtUsername.setError("Username Tidak Ditemukan");
            fokus = edtUsername;
            cancel = true;
        }

       if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Harus Diisi");
            fokus = edtPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            edtPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = edtPassword;
            cancel = true;
        }


        if (cancel) {
            fokus.requestFocus();
        } else {

            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);

            Preferences.setUserPreferences(getBaseContext(), userModel);
            Preferences.setLoggedInStatus(getBaseContext(), true);

            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }

    }

    // True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    // True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }


}
