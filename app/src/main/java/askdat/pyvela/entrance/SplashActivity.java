package askdat.pyvela.entrance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.widget.Toast;

import askdat.pyvela.R;


public class SplashActivity extends AppCompatActivity {
    private int SPLASH_DISPLAY_LENGTH = 1000;
    private Context context;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        sharedPref=getPreferences(MODE_PRIVATE);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (sharedPref.getBoolean("bool",true)) {
                    Intent intent = new Intent(SplashActivity.this, EntranceActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
                else
                    Toast.makeText(SplashActivity.this,"Cooper,Cooper, two Coopers",Toast.LENGTH_SHORT).show();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    public void saveText(){
        Editor ed = sharedPref.edit();
        ed.putBoolean("bool",true);
        ed.commit();
    }
}
