package askdat.pyvela.entrance;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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
import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.main.MainActivity;


public class SplashActivity extends AppCompatActivity {

    private int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPrefsClass sharedPrefsClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        sharedPrefsClass = new SharedPrefsClass();
        sharedPrefsClass.appPrefs(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (!sharedPrefsClass.sharedPrefs.getBoolean("bool", false)) {
                    Intent intent = new Intent(SplashActivity.this, EntranceActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(SplashActivity.this);
                    stackBuilder.addNextIntentWithParentStack(intent);
                    PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                    startActivity(intent);
                    Toast.makeText(SplashActivity.this, "Cooper,Cooper, two Coopers", Toast.LENGTH_SHORT).show();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
