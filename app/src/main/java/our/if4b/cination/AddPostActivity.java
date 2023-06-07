package our.if4b.cination;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import our.if4b.cination.databinding.ActivityAddPostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String negara = binding.etNegara.getText().toString();
                String kota = binding.etKota.getText().toString();

                boolean bolehPost = true;

                if(TextUtils.isEmpty(negara)){
                    bolehPost = false;
                    binding.etNegara.setError("Negara tidak boleh kosong!");
                }
                if(TextUtils.isEmpty(kota)){
                    bolehPost = false;
                    binding.etKota.setError("Negara tidak boleh kosong!");
                }
                if(bolehPost){
                    String username = Utility.getValue(AddPostActivity.this,"xUsername");
                    addPost(username, negara, kota);
                }
                
            }
        });
    }

    private void addPost(String username, String negara, String kota) {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiService api = Utility.getRetrofit().create(ApiService.class);
        Call<ValueData<List<Post>>> call = api.getPost();
        call.enqueue(new Callback<ValueData<List<Post>>>() {
            @Override
            public void onResponse(Call<ValueData<List<Post>>> call, Response<ValueData<List<Post>>> response) {
                if(response.code() == 200){
                    int success = response.body().getSuccess();
                    String massage = response.body().getMessage();
                    if(success == 1){
                        Toast.makeText(AddPostActivity.this, massage, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(AddPostActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(AddPostActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<Post>>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(AddPostActivity.this, "Retrofit Error : " + t.getMessage()  , Toast.LENGTH_SHORT).show();
            }
        });
    }
}