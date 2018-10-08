package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCast;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCrew;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.GlideApp;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolerType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.BASE_URL_CAST_CREW_IMAGE;

public class MovieCrewRecyclerViewHolder extends MyRecyclerViewViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card_profile_imageView)
    ImageView imageViewProfile;
    @BindView(R.id.item_card_profile_textView_1)
    TextView textViewJob;
    @BindView(R.id.item_card_profile_textView_2)
    TextView textViewName;


    private ArrayList<MovieCrew> movieCrews;
    private Activity activity;

    public MovieCrewRecyclerViewHolder(@NonNull View itemView, ArrayList<MovieCrew> movieCrews, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.movieCrews = movieCrews;
        this.activity = activity;
    }

    @Override
    public void setData(int position) {
        super.setData(position);
        MovieCrew movieCrew = movieCrews.get(position);

        GlideApp.with(activity)
                .load(BASE_URL_CAST_CREW_IMAGE + movieCrew.getProfilePath())
                .into(imageViewProfile);
        textViewName.setText(movieCrew.getName());
        textViewJob.setTypeface(null, Typeface.BOLD);
        textViewJob.setText(movieCrew.getJob());

    }

    @Override
    public void onClick(View v) {

    }
}
