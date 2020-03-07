package emresimsek.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    int score;
    ImageView kenny1;
    ImageView kenny2;
    ImageView kenny3;
    ImageView kenny4;
    ImageView kenny5;
    ImageView kenny6;
    ImageView kenny7;
    ImageView kenny8;
    ImageView kenny9;
    ImageView [] imgArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time : "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image:imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();

         timeText=(TextView) findViewById(R.id.timeText);
         scoreText=(TextView) findViewById(R.id.scoreText);
         score=0;
         kenny1=findViewById(R.id.kenny1);
         kenny2=findViewById(R.id.kenny2);
         kenny3=findViewById(R.id.kenny3);
         kenny4=findViewById(R.id.kenny4);
         kenny5=findViewById(R.id.kenny5);
         kenny6=findViewById(R.id.kenny6);
         kenny7=findViewById(R.id.kenny7);
         kenny8=findViewById(R.id.kenny8);
         kenny9=findViewById(R.id.kenny9);
         imgArray=new ImageView[]{kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8,kenny9};
         hideImages();

    }
    public void increaseScore (View view)
    {
        score++;

        scoreText.setText("Score : " + score);

    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random =new Random();
                int i=random.nextInt(9);
                imgArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,700);
            }
        };

      handler.post(runnable);
    }
}
