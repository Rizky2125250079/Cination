package our.if4b.cination;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import our.if4b.cination.databinding.ActivityUpdatePostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity extends AppCompatActivity {

    private ActivityUpdatePostBinding binding;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdatePostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        post = getIntent().getParcelableExtra("EXTRA DATA");
        String id = post.getId();

        binding.etNegara.setText(post.getNegara());
        binding.etKota.setText(post.getIbukota());

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String negara = binding.etNegara.getText().toString();
                String ibukota = binding.etKota.getText().toString();
                boolean bolehUpdatePost = true;

                if(TextUtils.isEmpty(negara)){
                    bolehUpdatePost = false;
                    binding.etNegara.setError("Negara tidak boleh kosong!");
                }

                if(TextUtils.isEmpty(ibukota)){
                    bolehUpdatePost = false;
                    binding.etKota.setError("Ibu Kota tidak boleh kosong!");
                }

                if(bolehUpdatePost){
                    updatePost(id, negara,ibukota);
                }

            }
        });
    }

    private void updatePost(String id, String negara, String ibukota) {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiService api = Utility.getRetrofit().create(ApiService.class);
        Call<ValueNoData> call = api.updatePost(id,negara,ibukota);

        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if(response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if(success == 1){
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(UpdatePostActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Eror : " + t.getMessage());
                Toast.makeText(UpdatePostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}