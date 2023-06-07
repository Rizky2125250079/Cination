package our.if4b.cination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import our.if4b.cination.databinding.ActivityRegisterBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmPassword = binding.etKonfirmPassword.getText().toString();

                boolean bolehRegister = true;
                if(TextUtils.isEmpty(username))
                {
                    bolehRegister = false;
                    binding.etUsername.setError("Username tidak boleh kosong!!");
                }
                if(TextUtils.isEmpty(password))
                {
                    bolehRegister = false;
                    binding.etPassword.setError("Password tidak boleh kosong!!");
                }
                if(TextUtils.isEmpty(konfirmPassword))
                {
                    bolehRegister = false;
                    binding.etKonfirmPassword.setError("Konfirmasi Password tidak boleh kosong!!");
                }
                if(!password.equals(konfirmPassword)){
                    bolehRegister = false;
                    binding.etPassword.setError("Konfirmasi Password Tidak Cocok!");
                }
                if(password.length() <  6 ){
                    bolehRegister = false;
                    binding.etPassword.setError("Panjang paswword harus lebih dari 6 digit");
                }
                if(bolehRegister){
                    register(username, password);
                }
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register(String username, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiService api = Utility.getRetrofit().create(ApiService.class);
        Call<ValueNoData> call = api.register(username, password);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if(response.code() == 200){
                    int success = response.body().getSuccess();
                    String message =response.body().getMessage();

                    if(success == 1){
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utility.setValue(RegisterActivity.this, "xUsername", username);
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}