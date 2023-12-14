package carloscatalina.example.retrofit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import carloscatalina.example.retrofit.PhotosActivity;
import carloscatalina.example.retrofit.R;
import carloscatalina.example.retrofit.modelos.Album;

public class AlbumsAdapters extends RecyclerView.Adapter<AlbumsAdapters.AlbumVH> {
    private List<Album> object;
    private int resource;
    private Context context;


    public AlbumsAdapters(List<Album> object, int resource, Context context) {
        this.object = object;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View albumView = LayoutInflater.from(context).inflate(resource, null);
        albumView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new AlbumVH(albumView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        Album a = object.get(position);
        holder.lbTitulo.setText(a.getTitulo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ALBUM_ID", String.valueOf(a.getId()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return object.size();
    }

    public class AlbumVH extends RecyclerView.ViewHolder{
        TextView lbTitulo;


        public AlbumVH(@NonNull View itemView) {
            super(itemView);
            lbTitulo = itemView.findViewById(R.id.lbTituloHolder);
        }
    }

}
