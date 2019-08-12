package vdt.thousandimages;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Future;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<String> mDataset;
    private Activity activity;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        ImageView imageView;
        TextView txtIndex;
        MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            txtIndex = v.findViewById(R.id.txtIndex);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    MyAdapter(ArrayList<String> myDataset, Activity activity) {
        this.mDataset = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.txtIndex.setText(String.format(Locale.getDefault(),"Image %d", position));

        Glide.with(activity)
                .load(mDataset.get(position))
                //.thumbnail(0.5f)
                .apply(new RequestOptions().override(1080, 720)) //TODO
                .into(holder.imageView);
//
//        Observable.create(new Observable.OnSubscribe<Bitmap>(){
//            @Override
//            public void call(final Subscriber<? super Bitmap> subscriber){
//                Glide.with(activity).asBitmap().
//                        load(mDataset.get(position))
//                        .apply(new RequestOptions().override(1080, 720))
//                        .listener(new RequestListener<Bitmap>() {
//                            @Override
//                            public boolean onLoadFailed(@Nullable GlideException e, Object model,
//                                                        Target<Bitmap> target, boolean isFirstResource) {
//                                return false;
//                            }
//                            @Override
//                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
//                                                           DataSource dataSource, boolean isFirstResource) {
//                                // resource is your loaded Bitmap
//                                subscriber.onNext(resource);
//                                subscriber.onCompleted();
//                                return true;
//                            }
//                        }).submit();
//
//
//
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Action1<Bitmap>() {
//            @Override
//            public void call(Bitmap bitmap) {
//
//                holder.imageView.setImageBitmap(bitmap);
//            }
//        });

//        Observable.just(getBitmap(mDataset.get(position)))
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap s) {
//                        holder.imageView.setImageBitmap(s);
//                    }
//                });

    }

    @Override
    public int getItemCount() {
        Log.d("size", ""+ mDataset.size());
        return mDataset.size();
    }

//    private Bitmap getBitmap(String photoPath){
////        BitmapFactory.Options options = new BitmapFactory.Options();
////        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
////        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
////        return bitmap;
////    }
}


