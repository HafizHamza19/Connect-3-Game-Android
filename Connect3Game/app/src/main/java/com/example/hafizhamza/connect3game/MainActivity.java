package com.example.hafizhamza.connect3game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
//red=1 & green=0 empty=2
    int ActivePlayer=0;
    int[] Gamestate={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

public void Dropin(View view)
{
    ImageView counter=(ImageView)view;
    int Gettag=Integer.parseInt(counter.getTag().toString());

    if(Gamestate[Gettag]==2 && gameActive)
    {
    Gamestate[Gettag]=ActivePlayer;

   counter.setTranslationY(-1500);


    if (ActivePlayer==0) {
        counter.setImageResource(R.drawable.bat);
        ActivePlayer=1;
    }
    else
    {
        counter.setImageResource(R.drawable.bowl);
        ActivePlayer=0;
    }
counter.animate().translationYBy(1500).rotation(3600).setDuration(200);

    for(int[] winningPosition : winningPositions) {
        if (Gamestate[winningPosition[0]] == Gamestate[winningPosition[1]] && Gamestate[winningPosition[1]] == Gamestate[winningPosition[2]] && Gamestate[winningPosition[0]] != 2) {
            gameActive=false;
            String Winner;
            Toast t;
            if (ActivePlayer == 1) {
                Winner = "Bat";
                t=Toast.makeText(this,"Smashed a Six",Toast.LENGTH_SHORT);
            } else {
                Winner = "Bowl";
                t=Toast.makeText(this,"Clean Bowled",Toast.LENGTH_SHORT);
            }
            t.show();
            TextView WinnerText=(TextView)findViewById(R.id.WiinertextView);
            WinnerText.setText(Winner + " Has Won");
            Button PlayAgain=(Button)findViewById(R.id.PlayAgainbutton);
            WinnerText.setVisibility(View.VISIBLE);
            PlayAgain.setVisibility(View.VISIBLE);

        }
    }
    }

}
    public void PlayA(View view)
    {
        TextView WinnerText=(TextView)findViewById(R.id.WiinertextView);
        Button PlayAgain=(Button)findViewById(R.id.PlayAgainbutton);
        WinnerText.setVisibility(View.INVISIBLE);
        PlayAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
           counter.setImageDrawable(null);
        }
        for(int i=0;i<Gamestate.length;i++)
        {
Gamestate[i]=2;
        }
        ActivePlayer=0;

        gameActive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
