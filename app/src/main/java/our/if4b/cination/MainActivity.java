package our.if4b.cination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import our.if4b.cination.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Post> data;
    private PostViewAdapter postViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!Utility.checkValue(this,"xUsername")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        postViewAdapter = new PostViewAdapter();
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPost.setAdapter(postViewAdapter);
    }


    private void getAllPost() {
        binding.progressBar.setVisibility(View.VISIBLE);
        ApiService api = Utility.getRetrofit().create(ApiService.class);
        Call<ValueData<List<Post>>> call = api.getPost();

        call.enqueue(new Callback<ValueData<List<Post>>>() {
            @Override
            public void onResponse(Call<ValueData<List<Post>>> call, Response<ValueData<List<Post>>> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        data = response.body().getData();
                        postViewAdapter.setData(data);
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<List<Post>>> call, Throwable t) {
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_logut){
            Utility.clearUser(this);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        getAllPost();
    }
}
