package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCast;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.GlideApp;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.BASE_URL_CAST_CREW_IMAGE;

public class MovieCastRecyclerViewHolder extends MyRecyclerViewViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card_profile_imageView)
    ImageView imageViewProfile;
    @BindView(R.id.item_card_profile_textView_1)
    TextView textViewCharacter;
    @BindView(R.id.item_card_profile_textView_2)
    TextView textViewName;


    private ArrayList<MovieCast> movieCasts;
    private Activity activity;

    public MovieCastRecyclerViewHolder(@NonNull View itemView, ArrayList<MovieCast> movieCasts, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.movieCasts = movieCasts;
        this.activity = activity;
    }

    @Override
    public void setData(int position) {
        super.setData(position);
        MovieCast movieCast =  movieCasts.get(position);

        GlideApp.with(activity)
                .load(BASE_URL_CAST_CREW_IMAGE + movieCast.getProfilePath())
                .into(imageViewProfile);
        textViewName.setTypeface(null, Typeface.BOLD);
        textViewName.setText(movieCast.getName());

        textViewCharacter.setText(movieCast.getCharacter());

    }

    @Override
    public void onClick(View v) {

    }
}
