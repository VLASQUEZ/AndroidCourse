package com.olimpiait.reconoser.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.olimpiait.reconoser.sharedpreferences.Preferences.Preferences;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private Switch swRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        bindUI();

        prefs = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login(etEmail.getText().toString(),etPassword.getText().toString())){
                    goToHome();
                    savePreferences(etEmail.getText().toString(),etPassword.getText().toString());
                }
            }
        });
        setCredentialsIfExist();
    }

    /**
     * Guarda las preferencias dependiendo del estado del switch
     * @param email email del login
     * @param password password del usuario
     */
    private void savePreferences(String email,String password){
        if(swRemember.isChecked()){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("password",password);
            editor.apply();
        }
    }
    private void bindUI(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        swRemember = (Switch) findViewById(R.id.swRememberUser);
    }


    private void setCredentialsIfExist(){
        String email = Preferences.getUserEmailPrefs(prefs);
        String password = Preferences.getUserPasswordPrefs(prefs);

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            etEmail.setText(email);
            etPassword.setText(password);
        }
    }
    /**
     * Valida si el email se encuentra en un formato correcto
     * @param email email del usuario
     * @return boolean en caso de que sea valido o no.
     */
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password){
        return password.length() > 4;
    }

    private boolean login(String email, String password){
        if(!isValidEmail(email)){
            Toast.makeText(this,"El email no es valido",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!isPasswordValid(password)){
            Toast.makeText(this, "El password no es valido", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }
    private void goToHome(){
        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
