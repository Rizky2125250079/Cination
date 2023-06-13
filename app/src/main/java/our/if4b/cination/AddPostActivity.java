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
                String ibukota = binding.etKota.getText().toString();

                boolean bolehPost = true;

                if(TextUtils.isEmpty(negara)){
                    bolehPost = false;
                    binding.etNegara.setError("Negara tidak boleh kosong!");
                }
                if(TextUtils.isEmpty(ibukota)){
                    bolehPost = false;
                    binding.etKota.setError("Ibu Kota tidak boleh kosong!");
                }
                if(bolehPost){
                    String user_id = Utility.getValue(AddPostActivity.this,"xUsername");
                    addPost(user_id, negara, ibukota);
                }
                
            }
        });
    }

    private void addPost(String user_id, String negara, String ibukota) {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiService api = Utility.getRetrofit().create(ApiService.class);
        Call<ValueNoData> call = api.addPost(user_id,negara,ibukota);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
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
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(AddPostActivity.this, "Retrofit Error : " + t.getMessage()  , Toast.LENGTH_SHORT).show();
            }
        });
    }
}