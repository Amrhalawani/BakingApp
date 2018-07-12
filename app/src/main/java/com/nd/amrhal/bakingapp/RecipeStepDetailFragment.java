package com.nd.amrhal.bakingapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.nd.amrhal.bakingapp.Models.RecipeModel;
import com.nd.amrhal.bakingapp.Models.StepModel;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment {


    SimpleExoPlayer exoPlayer;
    //String videoURL = "http://blueappsoftware.in/layout_design_android_blog.mp4";
    String videoURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4";
    private ImageView placeholder;
    SimpleExoPlayerView exoPlayerView;
    ImageView playerPlaceholder;
    boolean playState = true;
    private static long playerPosition = C.TIME_UNSET;
    TextView discriptionTV;
    MediaSource mediaSource;

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        discriptionTV = view.findViewById(R.id.textview_StepDetailFragment);
        playerPlaceholder = view.findViewById(R.id.placeholder_of_player);

        exoMediaSetup(view, savedInstanceState, container);

        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // outState.putString("videourl", videoURL);
        outState.putBoolean("playstate", playState);
        outState.putLong("position", playerPosition);

    }

    @Override
    public void onPause() {

        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer.stop();
            playerPosition = exoPlayer.getCurrentPosition();
            playState = false;
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer.stop();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        exoPlayer.seekTo(playerPosition);
        exoPlayer.setPlayWhenReady(playState);
       // intializeExoPlayer();
    }

    private void exoMediaSetup(View rootView, Bundle savedInstanceState, ViewGroup container) {

        try {
            exoPlayerView = rootView.findViewById(R.id.exo_player_view);
//------------------------------------------------------------------------------------------------------------------
//tablet
            if (Util.getPhoneOrTablet(getActivity()) == Util.TABLET) {
                RecipeModel recipeModel = getActivity().getIntent().getExtras().getParcelable(RecipesActivity.RECIPE_PARC_KEY);
                StepModel stepModel = recipeModel.getSteps().get(Util.getPositionfortabletonly(getActivity()));
                String URL = stepModel.getVideoURL();

                if (stepModel.getVideoURL().isEmpty()) {
                    handleTHumbnail(stepModel);
                } else {
                    handleVideo(stepModel);
                }
                discriptionTV.setText(stepModel.getDescription());
            }

//phone
            else if (Util.getPhoneOrTablet(getActivity()) == Util.PHONE) {
                StepModel stepModel = getActivity().getIntent().getExtras().getParcelable(RecipeDetailFragment.STEP_RECIPE_PARC_KEY);
                if (stepModel != null) {
                    if (stepModel.getVideoURL().isEmpty()) {
                        handleTHumbnail(stepModel);

                    } else {
                        handleVideo(stepModel);
                    }
                }
                discriptionTV.setText(stepModel.getDescription());
            }

//--------------------------------------------------------------------------------------------------------------
            if (savedInstanceState != null) {
                playerPosition = savedInstanceState.getLong("position");
                playState = savedInstanceState.getBoolean("playstate");

            }

            intializeExoPlayer();


        } catch (Exception e) {
            Log.e("tag", " exoplayer error " + e.toString());
        }
    }


    private void intializeExoPlayer() {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        Uri videoURI = Uri.parse(videoURL);
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
        exoPlayerView.setPlayer(exoPlayer);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(playState);
        if (playerPosition != C.TIME_UNSET) {
            exoPlayer.seekTo(playerPosition);
        }

    }

    private void handleTHumbnail(StepModel stepModel) {
        String thumbnailURL = stepModel.getThumbnailURL();
        if (!thumbnailURL.isEmpty()) {
            boolean checkEx = thumbnailURL.substring(thumbnailURL.length() - 3, thumbnailURL.length()).equals("jpg");

            if (!thumbnailURL.isEmpty() && checkEx) {
                playerPlaceholder.setVisibility(View.VISIBLE);
                Picasso.get()
                        .load(stepModel.getThumbnailURL())
                        .placeholder(R.drawable.glovhand)
                        .into(playerPlaceholder);
            }

        } else {
            Picasso.get()
                    .load(R.drawable.glovhand)
                    .into(playerPlaceholder);

        }


    }

    private void handleVideo(StepModel stepModel) {
        String URL = stepModel.getVideoURL();
        boolean b = URL.substring(URL.length() - 3, URL.length()).equals("mp4");
        if (b) {
            Toast.makeText(getActivity(), "mp4==mp4", Toast.LENGTH_SHORT).show();
            videoURL = stepModel.getVideoURL();
            playerPlaceholder.setVisibility(View.GONE);
        } else {

            playerPlaceholder.setVisibility(View.VISIBLE);
        }
    }


    protected void displayReceivedData(int position) {
        Util.setPositionfortabletonly(getActivity(), position);
        //getActivity().getFragmentManager().beginTransaction().remove().commit();
    }

}
