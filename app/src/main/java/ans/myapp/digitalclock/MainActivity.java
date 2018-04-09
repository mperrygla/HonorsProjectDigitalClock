package ans.myapp.digitalclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextClock tv_hour_12, tv_hour_24, tv_min, tv_sec, tv_am_pm, tv_date, tv_mon, tv_yr; //tv_day,
    boolean doubleBackToExitPressedOnce = false;
    private TextView tv_hr_dot, tv_min_dot, day, tv_greeting;
    private LinearLayout ll_main;
    private ImageView ll_graphic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initialization for all the objects and components
//        tv_day = (TextClock) findViewById(R.id.tv_day);
        tv_hour_12 = (TextClock) findViewById(R.id.tv_hour_12);
        tv_hour_24 = (TextClock) findViewById(R.id.tv_hour_24);
        tv_min = (TextClock) findViewById(R.id.tv_min);
        tv_sec = (TextClock) findViewById(R.id.tv_sec);
        tv_am_pm = (TextClock) findViewById(R.id.tv_am_pm);
        tv_date = (TextClock) findViewById(R.id.tv_date);
        tv_mon = (TextClock) findViewById(R.id.tv_mon);
        tv_yr = (TextClock) findViewById(R.id.tv_yr);

        day = (TextView) findViewById(R.id.day);
        tv_greeting = (TextView) findViewById(R.id.tv_greeting);
        tv_hr_dot = (TextView) findViewById(R.id.tv_hr_dot);
        tv_min_dot = (TextView) findViewById(R.id.tv_min_dot);

        ll_main = (LinearLayout) findViewById(R.id.ll_main);

        ll_graphic = (ImageView) findViewById(R.id.ll_graphic);

//        tv_day.setFormat12Hour(null);
        tv_hour_12.setFormat12Hour(null);
        tv_hour_24.setFormat12Hour(null);
        tv_min.setFormat12Hour(null);
        tv_sec.setFormat12Hour(null);
        tv_am_pm.setFormat12Hour(null);
        tv_date.setFormat12Hour(null);
        tv_mon.setFormat12Hour(null);
        tv_yr.setFormat12Hour(null);

        Date d = new Date(new Date().getTime());
        String s1 = new SimpleDateFormat("dd/MM/yyyy EEE hh:mm:ss a").format(d);
        String s2 = new SimpleDateFormat("HH").format(d);
        String s3 = new SimpleDateFormat("mm").format(d);
        String s4_day = new SimpleDateFormat("EEE").format(d);

        Log.e("Format_12", s1);
        Log.e("Format_24", s2);


        day.setText(day_select(s4_day));
//        tv_day.setFormat24Hour("EEE");            // only day

        tv_hour_12.setFormat24Hour("hh");            // 12 hour

        tv_hour_24.setFormat24Hour("HH");            // 24 hour

        tv_min.setFormat24Hour("mm");            // only minutes

        tv_sec.setFormat24Hour("ss");            // only sec

        tv_am_pm.setFormat24Hour("a");            // only AM PM

        tv_date.setFormat24Hour("dd");            // only date

        tv_mon.setFormat24Hour("MMM");            // only date

        tv_yr.setFormat24Hour("yyyy");            // only date


        // Flag set for the first time app run for switch on off in setting page
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            // mark first time has runned.

            PreferenceUtil.putData(getApplicationContext()).putString("switch_day", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_12_hr", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_hour", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_min", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_date", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_mnth", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_year", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_greeting", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("switch_graphic", "1").apply();
            PreferenceUtil.putData(getApplicationContext()).putString("text_size", "M").apply();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        } else {

            //displays time text size differently depending on what setting
            if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("S")) {
                tv_hour_12.setTextSize(30);
                tv_hour_24.setTextSize(30);
                tv_min.setTextSize(30);
                tv_sec.setTextSize(30);
                tv_hr_dot.setTextSize(30);
                tv_min_dot.setTextSize(30);
            } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("M")) {
                tv_hour_12.setTextSize(40);
                tv_hour_24.setTextSize(40);
                tv_min.setTextSize(40);
                tv_sec.setTextSize(40);
                tv_hr_dot.setTextSize(40);
                tv_min_dot.setTextSize(40);
            } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("L")) {
                tv_hour_12.setTextSize(50);
                tv_hour_24.setTextSize(50);
                tv_min.setTextSize(50);
                tv_sec.setTextSize(50);
                tv_hr_dot.setTextSize(50);
                tv_min_dot.setTextSize(50);
            } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("XL")) {
                tv_hour_12.setTextSize(60);
                tv_hour_24.setTextSize(60);
                tv_min.setTextSize(60);
                tv_sec.setTextSize(60);
                tv_hr_dot.setTextSize(60);
                tv_min_dot.setTextSize(60);
            }


            // Condition for day visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_day", "").equalsIgnoreCase("1")) {
//                tv_day.setVisibility(View.VISIBLE);
                day.setVisibility(View.VISIBLE);
            } else {
//                tv_day.setVisibility(View.GONE);
                day.setVisibility(View.GONE);
            }


            // Condition for hour visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_hour", "").equalsIgnoreCase("1")) {
//                tv_hour_12.setVisibility(View.VISIBLE);
//                tv_hour_24.setVisibility(View.VISIBLE);

                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_12_hr", "").equalsIgnoreCase("1")) {
                    tv_hour_12.setVisibility(View.VISIBLE);
                    tv_hour_24.setVisibility(View.GONE);
                } else {
                    tv_hour_12.setVisibility(View.GONE);
                    tv_hour_24.setVisibility(View.VISIBLE);

                    tv_am_pm.setVisibility(View.GONE);
                }

            } else {
                tv_hour_12.setVisibility(View.GONE);
                tv_hour_24.setVisibility(View.GONE);

                tv_min.setVisibility(View.GONE);
                tv_sec.setVisibility(View.GONE);
                tv_am_pm.setVisibility(View.GONE);

                tv_hr_dot.setVisibility(View.GONE);
                tv_min_dot.setVisibility(View.GONE);

//                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_12_hr", "").equalsIgnoreCase("1")) {
//                    tv_hour_12.setVisibility(View.VISIBLE);
//                    tv_hour_24.setVisibility(View.GONE);
//                } else {
//                    tv_hour_12.setVisibility(View.GONE);
//                    tv_hour_24.setVisibility(View.VISIBLE);
//                }

            }


            // Condition for min visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_min", "").equalsIgnoreCase("1")) {
                tv_min.setVisibility(View.VISIBLE);
                tv_hr_dot.setVisibility(View.VISIBLE);
                tv_min_dot.setVisibility(View.VISIBLE);
            } else {
                tv_min.setVisibility(View.GONE);
                tv_hr_dot.setVisibility(View.GONE);
                tv_min_dot.setVisibility(View.GONE);
            }

            // Condition for sec visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_sec", "").equalsIgnoreCase("1")) {
                tv_sec.setVisibility(View.VISIBLE);
                tv_min_dot.setVisibility(View.VISIBLE);
            } else {
                tv_sec.setVisibility(View.GONE);
                tv_min_dot.setVisibility(View.GONE);
            }

            // Condition for AM/PM visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_am_pm", "").equalsIgnoreCase("1")) {
                tv_am_pm.setVisibility(View.VISIBLE);
            } else {
                tv_am_pm.setVisibility(View.GONE);
            }

            // Condition for date visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_date", "").equalsIgnoreCase("1")) {
                tv_date.setVisibility(View.VISIBLE);
            } else {
                tv_date.setVisibility(View.GONE);
            }


            // Condition for month visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_mnth", "").equalsIgnoreCase("1")) {
                tv_mon.setVisibility(View.VISIBLE);
            } else {
                tv_mon.setVisibility(View.GONE);
            }


            // Condition for year visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_year", "").equalsIgnoreCase("1")) {
                tv_yr.setVisibility(View.VISIBLE);
            } else {
                tv_yr.setVisibility(View.GONE);
            }

            // Condition for greetings visible/hide
            if (PreferenceUtil.getData(getApplicationContext()).getString("switch_greeting", "").equalsIgnoreCase("1")) {
                tv_greeting.setVisibility(View.VISIBLE);
            } else {
                tv_greeting.setVisibility(View.GONE);
            }
        }


        // Greetings validation from the hours of 4am to 11am morning greeting message with day graphic
        if (Integer.valueOf(s2) > 4 && Integer.valueOf(s2) < 12) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Morning");

                // Condition for graphic visible/hide
                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_graphic", "").equalsIgnoreCase("1")) {
                    ll_graphic.setVisibility(View.VISIBLE);
                    ll_graphic.setImageResource(R.drawable.one);
                } else {
                    ll_graphic.setVisibility(View.GONE);
                }

            }
        }

        // Greetings validation from the hours of 12 to 3pm afternoon greeting message with day graphic
        if (Integer.valueOf(s2) > 11 && Integer.valueOf(s2) < 16) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Afternoon");

                // Condition for graphic visible/hide
                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_graphic", "").equalsIgnoreCase("1")) {
                    ll_graphic.setVisibility(View.VISIBLE);
                    ll_graphic.setImageResource(R.drawable.one);
                } else {
                    ll_graphic.setVisibility(View.GONE);
                }

            }
        }

        // Greetings validation from the hours of 4pm to 9pm morning greeting message with night graphic
        if (Integer.valueOf(s2) > 15 && Integer.valueOf(s2) < 21) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Evening");

                // Condition for graphic visible/hide
                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_graphic", "").equalsIgnoreCase("1")) {
                    ll_graphic.setVisibility(View.VISIBLE);
                    ll_graphic.setImageResource(R.drawable.two);
                } else {
                    ll_graphic.setVisibility(View.GONE);
                }

            }
        }

        // Greetings validation from the hours of 9pm to 4am morning greeting message with night graphic
        if (Integer.valueOf(s2) > 20 && Integer.valueOf(s2) < 5) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Night");

                // Condition for graphic visible/hide
                if (PreferenceUtil.getData(getApplicationContext()).getString("switch_graphic", "").equalsIgnoreCase("1")) {
                    ll_graphic.setVisibility(View.VISIBLE);
                    ll_graphic.setImageResource(R.drawable.two);
                } else {
                    ll_graphic.setVisibility(View.GONE);
                }

            }
        }

        // sets the clocks background colour
        if (!PreferenceUtil.getData(getApplicationContext()).getString("color", "").isEmpty()) {
            int color = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("color", ""));

            ll_main.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), color));
        }

        // sets the clocks text colour
        if (!PreferenceUtil.getData(getApplicationContext()).getString("text_color", "").isEmpty()) {
            int text_color = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("text_color", ""));

//            tv_day.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_hour_12.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_hour_24.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_min.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_sec.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_am_pm.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_date.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_mon.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_yr.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));

            day.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_greeting.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));

            tv_hr_dot.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
            tv_min_dot.setTextColor(ContextCompat.getColor(getApplicationContext(), text_color));
        }


    }


    private String day_select(final String dayyy) {
        String Final_day = "";
        if (dayyy.equalsIgnoreCase("Mon")) {
            Final_day = "Monday";
        } else if (dayyy.equalsIgnoreCase("Tue")) {
            Final_day = "Tuesday";
        } else if (dayyy.equalsIgnoreCase("Wed")) {
            Final_day = "Wednesday";
        } else if (dayyy.equalsIgnoreCase("Thu")) {
            Final_day = "Thursday";
        } else if (dayyy.equalsIgnoreCase("Fri")) {
            Final_day = "Friday";
        } else if (dayyy.equalsIgnoreCase("Sat")) {
            Final_day = "Saturday";
        } else if (dayyy.equalsIgnoreCase("Sun")) {
            Final_day = "Sunday";
        }

        return Final_day;
    }


    @Override
    protected void onResume() {
        super.onResume();

        Date d = new Date(new Date().getTime());
        String s1 = new SimpleDateFormat("dd/MM/yyyy EEE hh:mm:ss a").format(d);
        String s2 = new SimpleDateFormat("HH").format(d);
        String s3 = new SimpleDateFormat("mm").format(d);
        String s4_day = new SimpleDateFormat("EEE").format(d);

        // Greetings validation
        if (Integer.valueOf(s2) > 4 && Integer.valueOf(s2) < 12) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Morning");
            }
        }

        if (Integer.valueOf(s2) > 11 && Integer.valueOf(s2) < 16) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Afternoon");
            }
        }

        if (Integer.valueOf(s2) > 15 && Integer.valueOf(s2) < 21) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Evening");
            }
        }

        if (Integer.valueOf(s2) > 20 && Integer.valueOf(s2) < 5) {
            if (Integer.valueOf(s3) < 60) {
                tv_greeting.setText("Night");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Click events of the option menu
        if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(this, Setting.class));
            finish();
        } else if (item.getItemId() == R.id.clock) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
