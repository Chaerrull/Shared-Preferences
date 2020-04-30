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

public class RegisterActivity extends AppCompatActivity {

    private TextView txtMasuk;
    private EditText edtUserName;
    private EditText edtPassWord;
    private EditText edtRePassWord;
    private EditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        declareView();
        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiRegister();
            }
        });

    }

    private void declareView() {
        txtMasuk = findViewById(R.id.txt_reg_masuk);
        edtUserName = findViewById(R.id.edt_reg_username);
        edtPassWord = findViewById(R.id.edt_reg_password);
        edtRePassWord = findViewById(R.id.edt_reg_password_confirm);
        edtPhoneNumber = findViewById(R.id.edt_reg_phone);
    }

    private void validasiRegister(){

        // Mereset semua Error dan fokus menjadi default
        edtUserName.setError(null);
        edtPassWord.setError(null);
        edtRePassWord.setError(null);



        String userName = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        String rePassword = edtRePassWord.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();


            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            userModel.setPhone(phoneNumber);
            // Simpan data ke shared preferences
            Preferences.setUserPreferences(getBaseContext(),userModel);
            Preferences.setLoggedInStatus(getBaseContext(),true);
            //Pindah Halaman ke home
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    }
