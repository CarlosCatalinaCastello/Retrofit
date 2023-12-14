package carloscatalina.example.retrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import carloscatalina.example.retrofit.R;
import carloscatalina.example.retrofit.modelos.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosVH> {
    private List<Photo> object;
    private int resources;
    private Context context;

    public PhotosAdapter(List<Photo> object, int resources, Context context) {
        this.object = object;
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotosAdapter.PhotosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotosVH(LayoutInflater.from(context).inflate(resources, null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.PhotosVH holder, int position) {
        Photo p = object.get(position);
        holder.lbTitulo.setText(p.getTitle());
        Picasso.get().load(p.getThumbnailUrl()).placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgImagen);
    }

    @Override
    public int getItemCount() {
        return object.size();
    }

    public class PhotosVH extends RecyclerView.ViewHolder {
        ImageView imgImagen;
        TextView lbTitulo;
        public PhotosVH(@NonNull View itemView) {
            super(itemView);
            imgImagen = itemView.findViewById(R.id.imgImagenPhotoCard);
            lbTitulo = itemView.findViewById(R.id.lbTituloPhotoCard);
        }
    }
}
