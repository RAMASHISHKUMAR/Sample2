package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sample.model.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    Image imageData;
    Context context;

    public ImageAdapter(Context context, Image imageData) {
        this.context = context;
        this.imageData = imageData;
    }

    @NonNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyleeritem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageAdapter.MyViewHolder holder, int position) {
        final String root = Environment.getExternalStorageDirectory().toString();


      try {
          Picasso.with(context)
                  .load(imageData.getImages().get(holder.getAdapterPosition()).getXtImage())
                  .into(holder.img);

      }catch (Exception e){
          e.printStackTrace();
      }


        try {
           Picasso.with(context)
                   .load(imageData.getImages().get(holder.getAdapterPosition()).getXtImage())
                   .into(new Target() {
                             @Override
                             public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                 try {
                                     File myDir = new File(root + "/demo");

                                     if (!myDir.exists()) {
                                         myDir.mkdirs();
                                     }

                                     final String name = new Date().toString() + ".jpg";
                                     myDir = new File(myDir, name);
                                     FileOutputStream out = new FileOutputStream(myDir);
                                     bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                                     out.flush();
                                     out.close();

                                     holder.img.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent(context,ImageDetailActivity.class);
                                             intent.putExtra("image",imageData.getImages().get(holder.getAdapterPosition()).getXtImage());
                                             intent.putExtra("imagePath",root + "/demo/"+name);
                                             context.startActivity(intent);
                                         }
                                     });
                                 } catch (Exception e) {
                                     // some action
                                 }
                             }

                             @Override
                             public void onBitmapFailed(Drawable errorDrawable) {
                             }

                             @Override
                             public void onPrepareLoad(Drawable placeHolderDrawable) {
                             }
                         }
                   );
       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public int getItemCount() {
        return imageData.getImages().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image_view);

        }
    }
}
