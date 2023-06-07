package our.if4b.cination;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import our.if4b.cination.databinding.ActivityAddPostBinding;

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
        
    }
}