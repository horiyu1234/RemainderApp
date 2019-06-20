package com.example.myapplication;


import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import java.util.Calendar;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends FragmentActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlertDialogFragment newFragment = new AlertDialogFragment();
        findViewById(R.id.button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ダイアログを表示する
                newFragment.show(getSupportFragmentManager(), "dialog");
                System.out.println("ダイアログ。");
            }
        });

        findViewById(R.id.schedule_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                System.out.println("スケジュールテスト‐‐");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.SECOND, 10);
                scheduleNotification("10秒後に届く通知です", calendar);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void scheduleNotification(String content, Calendar calendar){
        System.out.println("スケジュールテスト2");
        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_CONTENT, content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}


/*
public class MainActivity extends AppCompatActivity {

    private AlarmManager am;
    private PendingIntent pending;
    private int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = this.findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                // 10sec
                calendar.add(Calendar.SECOND, 10);

                Intent intent = new Intent(getApplicationContext(), AlarmNotification.class);
                intent.putExtra("RequestCode",requestCode);

                pending = PendingIntent.getBroadcast(
                        getApplicationContext(),requestCode, intent, 0);

                // アラームをセットする
                am = (AlarmManager) getSystemService(ALARM_SERVICE);

                if (am != null) {
                    am.setExact(AlarmManager.RTC_WAKEUP,
                            calendar.getTimeInMillis(), pending);

                    // トーストで設定されたことをを表示
                    Toast.makeText(getApplicationContext(),
                            "alarm start", Toast.LENGTH_SHORT).show();

                    Log.d("debug", "start");
                }
            }
        });

        // アラームの取り消し
        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent indent = new Intent(getApplicationContext(), AlarmNotification.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), requestCode, indent, 0);

                // アラームを解除する
                AlarmManager am = (AlarmManager)MainActivity.this.
                        getSystemService(ALARM_SERVICE);
                if (am != null) {
                    am.cancel(pending);
                    Toast.makeText(getApplicationContext(),
                            "alarm cancel", Toast.LENGTH_SHORT).show();
                    Log.d("debug", "cancel");
                }
                else{
                    Log.d("debug", "null");
                }
            }
        });
    }
}
*/