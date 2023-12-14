package carloscatalina.example.retrofit;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import carloscatalina.example.retrofit.adapters.AlbumsAdapters;
import carloscatalina.example.retrofit.conexiones.ApiConexiones;
import carloscatalina.example.retrofit.conexiones.RetrofitObject;
import carloscatalina.example.retrofit.databinding.ActivityMainBinding;
import carloscatalina.example.retrofit.modelos.Album;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Album> albums;
    private AlbumsAdapters adapters;
    private RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        albums = new ArrayList<>();
        adapters = new AlbumsAdapters(albums, R.layout.album_view_holder, this);
        lm = new GridLayoutManager(this, 1);

        binding.contentMain.contenedorAlbum.setAdapter(adapters);
        binding.contentMain.contenedorAlbum.setLayoutManager(lm);

        doGetAlbums();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void doGetAlbums() {
        Retrofit retrofit = RetrofitObject.getConexiones();
        ApiConexiones api = retrofit.create(ApiConexiones.class);
        Call<ArrayList<Album>> getAlbums = api.getAlbums();

        getAlbums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK){
                    ArrayList<Album> temp = response.body();
                    if (temp != null) {
                        albums.addAll(temp);
                        adapters.notifyItemRangeInserted(0, temp.size());
                    }
                }else{
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "NO TIENES INTERNET", Toast.LENGTH_SHORT).show();
            }
        });
    }

}