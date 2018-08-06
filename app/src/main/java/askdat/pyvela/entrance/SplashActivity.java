package askdat.pyvela.entrance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.widget.Toast;

import askdat.pyvela.R;
import askdat.pyvela.main.MainActivity;


public class SplashActivity extends AppCompatActivity {
    private int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPreferences sharedPref;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        appPreferences(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (!sharedPref.getBoolean("bool", false)) {
                    Intent intent = new Intent(SplashActivity.this, EntranceActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(SplashActivity.this, "Cooper,Cooper, two Coopers", Toast.LENGTH_SHORT).show();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    public void saveText(boolean status){
        editor = sharedPref.edit();
        editor.putBoolean("bool",status);
        editor.commit();
    }
    public void appPreferences(Context context){
        sharedPref= PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
