package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {

    TextView homeName;
    TextView awayName;

    TextView awayScore;
    TextView homeScore;

    ImageView homeIcon;
    ImageView awayIcon;

    Uri imageUriHome;
    Uri imageUriAway;

    Bitmap bitmapHome;
    Bitmap bitmapAway;

    String homeTeam;
    String awayTeam;

    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //Output Text
        homeName = findViewById(R.id.txt_home);
        awayName = findViewById(R.id.txt_away);
        //Img
        homeIcon = findViewById(R.id.home_logo);
        awayIcon = findViewById(R.id.away_logo);
        //Score
        awayScore = findViewById(R.id.score_away);
        homeScore = findViewById(R.id.score_home);
        //======================================================================
        //dari activity main!
        //======================================================================
        Bundle extras = getIntent().getExtras();
        //Ambil inputan form dari main

        //Edit Tex
        homeTeam = extras.getString("inputHome");
        awayTeam = extras.getString("inputAway");

        //Image

        if (extras != null){

            imageUriHome = Uri.parse(extras.getString("iconHome"));
            imageUriAway = Uri.parse(extras.getString("iconAway"));
            //Attribut image
            bitmapHome = null;
            bitmapAway = null;

            try{
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
            }catch(IOException e){
                e.printStackTrace();
            }

            //Menampung output dari inputan main
            homeName.setText(homeTeam);
            awayName.setText(awayTeam);
            homeIcon.setImageBitmap(bitmapHome);
            awayIcon.setImageBitmap(bitmapAway);

        }

    }
    //======================================================================
    //dari activity main!
    //======================================================================


    //===================================================
    //Button Nambah Score
    public void handleScoreHome(View view) {

        scoreHome = scoreHome + 1;
        homeScore.setText(String.valueOf(scoreHome));

    }

    public void handleScoreAway(View view) {

        scoreAway = scoreAway + 1;
        awayScore.setText(String.valueOf(scoreAway));
    }
    //===================================================

    //Link dan lihat hasil ke result activity
    public void handleResult(View view) {

        Intent intent = new Intent(this, ResultActivity.class);
        //Dari tambah button score
        intent.putExtra("homeScore", scoreHome);
        intent.putExtra("awayScore", scoreAway);
        //Dari output nama team
        intent.putExtra("homeName", homeTeam);
        intent.putExtra("awayName", awayTeam);

        startActivity(intent);

    }
    }
    //TODO
        //1.Menampilkan detail match sesuai data dari main activity

        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan

    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"


