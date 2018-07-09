package com.nd.amrhal.bakingapp;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.nd.amrhal.bakingapp.Models.StepModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepDetailFragment extends Fragment {


    SimpleExoPlayer exoPlayer;
    private int position;
    private static long PositionPlayer = C.TIME_UNSET;
    //String videoURL = "http://blueappsoftware.in/layout_design_android_blog.mp4";
    String videoURL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9cb_4-press-crumbs-in-pie-plate-creampie/4-press-crumbs-in-pie-plate-creampie.mp4";
    private ImageView placeholder;
    private ImageView playButton;
    @SuppressWarnings("deprecation")
    SimpleExoPlayerView exoPlayerView;

    private static long playerPosition = C.TIME_UNSET;

    TextView textView;
    String texts = "";

    public RecipeStepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_step_detail, container, false);
        textView = view.findViewById(R.id.textview_StepDetailFragment);
//
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();


        exoMediaSetup(view, savedInstanceState, container);

        return view;
    }


    @Override
    public void onPause() {

        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer.stop();
            playerPosition = exoPlayer.getCurrentPosition();
        }


        super.onPause();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("texts", texts);
    }

    @Override
    public void onDestroy() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer.stop();
        }
        super.onDestroy();
    }


    private void exoMediaSetup(View rootView, Bundle savedInstanceState, ViewGroup container) {

        try {


            //tablet
            if (Util.getPhoneOrTablet(getActivity()) == Util.TABLET) {

            }
            //phone
            else if (Util.getPhoneOrTablet(getActivity()) == Util.PHONE) {
                StepModel stepModel = getActivity().getIntent().getExtras().getParcelable(RecipeDetailFragment.STEP_RECIPE_PARC_KEY);
                textView.setText(stepModel.getDescription());
                String s = stepModel.getVideoURL();
                String a = s.substring(s.length() - 3, s.length());
                if (a.equals("mp4")) {
                    Toast.makeText(getActivity(), "mp4==mp4", Toast.LENGTH_SHORT).show();
                    videoURL = stepModel.getVideoURL();
                }

                Log.e("TAG", "exoMediaSetup: " + s.subSequence(s.length() - 3, s.length()));

            }

            exoPlayerView = rootView.findViewById(R.id.exo_player_view);

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
            Uri videoURI = Uri.parse(videoURL);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            if (playerPosition != C.TIME_UNSET) {
                exoPlayer.seekTo(playerPosition);
            }
            if (texts.length() <= 2 && savedInstanceState != null) {

                texts = savedInstanceState.getString("texts");

                textView.setText(texts);
            }
            if (container != null) {
                container.removeAllViews();
            }


        } catch (Exception e) {
            Log.e("tag", " exoplayer error " + e.toString());
        }
    }


    protected void displayReceivedData(String message) {
        textView.setText("Data received: " + message);
        texts = message;
    }

}
