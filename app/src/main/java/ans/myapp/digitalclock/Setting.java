package ans.myapp.digitalclock;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    private Switch switch_day, switch_12_hr, switch_hour, switch_min, switch_sec, switch_am_pm, switch_date, switch_mnth, switch_year, switch_greeting, switch_graphic;
    private LinearLayout ll_alarm, ll_clr, ll_select_clr, ll_txt_clr, ll_select_txt_clr, ll_back, ll_size;
    private Dialog dialog;
    int background_color = 2, text_color = 1;
    private TextView tv_hello, tv_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // initialization for all the objects and components
        switch_day = (Switch) findViewById(R.id.switch_day);
        switch_12_hr = (Switch) findViewById(R.id.switch_12_hr);
        switch_hour = (Switch) findViewById(R.id.switch_hour);
        switch_min = (Switch) findViewById(R.id.switch_min);
        switch_sec = (Switch) findViewById(R.id.switch_sec);
        switch_am_pm = (Switch) findViewById(R.id.switch_am_pm);
        switch_date = (Switch) findViewById(R.id.switch_date);
        switch_mnth = (Switch) findViewById(R.id.switch_mnth);
        switch_year = (Switch) findViewById(R.id.switch_year);
        switch_greeting = (Switch) findViewById(R.id.switch_greeting);
        switch_graphic = (Switch) findViewById(R.id.switch_graphic);

        ll_alarm = (LinearLayout) findViewById(R.id.ll_alarm);

        ll_clr = (LinearLayout) findViewById(R.id.ll_clr);
        ll_select_clr = (LinearLayout) findViewById(R.id.ll_select_clr);

        ll_size = (LinearLayout) findViewById(R.id.ll_size);
        tv_size = (TextView) findViewById(R.id.tv_size);

        ll_txt_clr = (LinearLayout) findViewById(R.id.ll_txt_clr);
        ll_select_txt_clr = (LinearLayout) findViewById(R.id.ll_select_txt_clr);

        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_hello = (TextView) findViewById(R.id.tv_hello);


        // show current color for clock background
        if (!PreferenceUtil.getData(getApplicationContext()).getString("color", "").isEmpty()) {
            int color = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("color", ""));

            background_color = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("color_code", ""));

            ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), color));
            ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), color));


        }


        // show current text color
        if (!PreferenceUtil.getData(getApplicationContext()).getString("text_color", "").isEmpty()) {
            int txt_clr = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("text_color", ""));

            text_color = Integer.valueOf(PreferenceUtil.getData(getApplicationContext()).getString("text_color_code", ""));

            ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), txt_clr));
            tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), txt_clr));
        }

        // show whatever size is selected
        if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("S")) {
            tv_size.setText("S");
        } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("M")) {
            tv_size.setText("M");
        } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("L")) {
            tv_size.setText("L");
        } else if (PreferenceUtil.getData(getApplicationContext()).getString("text_size", "").equalsIgnoreCase("XL")) {
            tv_size.setText("XL");
        }


        ll_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, AlarmMe.class));
            }
        });

        ll_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Setting.this);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_size);
                dialog.show();

                LinearLayout ll_s = (LinearLayout) dialog.findViewById(R.id.ll_s);
                LinearLayout ll_m = (LinearLayout) dialog.findViewById(R.id.ll_m);
                LinearLayout ll_l = (LinearLayout) dialog.findViewById(R.id.ll_l);
                LinearLayout ll_xl = (LinearLayout) dialog.findViewById(R.id.ll_xl);

                ll_s.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_size.setText("S");
                        PreferenceUtil.putData(getApplicationContext()).putString("text_size", "S").apply();
                        dialog.dismiss();
                    }
                });

                ll_m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_size.setText("M");
                        PreferenceUtil.putData(getApplicationContext()).putString("text_size", "M").apply();
                        dialog.dismiss();
                    }
                });

                ll_l.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_size.setText("L");
                        PreferenceUtil.putData(getApplicationContext()).putString("text_size", "L").apply();
                        dialog.dismiss();
                    }
                });

                ll_xl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_size.setText("XL");
                        PreferenceUtil.putData(getApplicationContext()).putString("text_size", "XL").apply();
                        dialog.dismiss();
                    }
                });


            }
        });

        //changing colour of
        ll_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(Setting.this);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_clr);
                dialog.show();


                Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);


                LinearLayout clr_blck = (LinearLayout) dialog.findViewById(R.id.clr_blck);       //1
                LinearLayout clr_wht = (LinearLayout) dialog.findViewById(R.id.clr_wht);         //1
                LinearLayout clr_pink = (LinearLayout) dialog.findViewById(R.id.clr_pink);       //2
                LinearLayout clr_red = (LinearLayout) dialog.findViewById(R.id.clr_red);         //3
                LinearLayout clr_blue = (LinearLayout) dialog.findViewById(R.id.clr_blue);       //4
                LinearLayout clr_teal = (LinearLayout) dialog.findViewById(R.id.clr_teal);       //5
                LinearLayout clr_orange = (LinearLayout) dialog.findViewById(R.id.clr_orange);   //6
                LinearLayout clr_yellow = (LinearLayout) dialog.findViewById(R.id.clr_yellow);   //7
                LinearLayout clr_cyan = (LinearLayout) dialog.findViewById(R.id.clr_cyan);       //8

//                btn_ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        dialog.dismiss();
//                    }
//                });

                //validation so when a colour is selected it does not show in settings
                if (text_color == 1) {
                    clr_blck.setVisibility(View.GONE);
                } else if (text_color == 2) {
                    clr_wht.setVisibility(View.GONE);
                } else if (text_color == 3) {
                    clr_pink.setVisibility(View.GONE);
                } else if (text_color == 4) {
                    clr_red.setVisibility(View.GONE);
                } else if (text_color == 5) {
                    clr_blue.setVisibility(View.GONE);
                } else if (text_color == 6) {
                    clr_teal.setVisibility(View.GONE);
                } else if (text_color == 7) {
                    clr_orange.setVisibility(View.GONE);
                } else if (text_color == 8) {
                    clr_yellow.setVisibility(View.GONE);
                } else if (text_color == 9) {
                    clr_cyan.setVisibility(View.GONE);
                }

                clr_blck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.colorPrimary)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "1").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 1;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        dialog.dismiss();
                    }
                });

                clr_wht.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.white)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "2").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 2;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        dialog.dismiss();
                    }
                });

                clr_pink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.pink)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "3").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 3;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                        dialog.dismiss();
                    }
                });

                clr_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.red)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "4").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 4;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        dialog.dismiss();
                    }
                });


                clr_blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.blue)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "5").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 5;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                        dialog.dismiss();
                    }
                });


                clr_teal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.teal)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "6").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 6;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                        dialog.dismiss();
                    }
                });


                clr_orange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.orange)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "7").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 7;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                        dialog.dismiss();
                    }
                });

                clr_yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.yellow)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "8").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 8;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                        dialog.dismiss();
                    }
                });

                clr_cyan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("color", String.valueOf(R.color.cyan)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("color_code", "9").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        background_color = 9;
                        ll_select_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.cyan));
                        ll_back.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.cyan));
                        dialog.dismiss();
                    }
                });

            }


        });



        ll_txt_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog = new Dialog(Setting.this);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_clr);
                dialog.show();

                Button btn_ok = (Button) dialog.findViewById(R.id.btn_ok);

                LinearLayout clr_blck = (LinearLayout) dialog.findViewById(R.id.clr_blck);       //1
                LinearLayout clr_wht = (LinearLayout) dialog.findViewById(R.id.clr_wht);         //1
                LinearLayout clr_pink = (LinearLayout) dialog.findViewById(R.id.clr_pink);       //2
                LinearLayout clr_red = (LinearLayout) dialog.findViewById(R.id.clr_red);         //3
                LinearLayout clr_blue = (LinearLayout) dialog.findViewById(R.id.clr_blue);       //4
                LinearLayout clr_teal = (LinearLayout) dialog.findViewById(R.id.clr_teal);       //5
                LinearLayout clr_orange = (LinearLayout) dialog.findViewById(R.id.clr_orange);   //6
                LinearLayout clr_yellow = (LinearLayout) dialog.findViewById(R.id.clr_yellow);   //7
                LinearLayout clr_cyan = (LinearLayout) dialog.findViewById(R.id.clr_cyan);       //8

//                btn_ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {/
//                        dialog.dismiss();
//                    }
//                });

                if (background_color == 1) {
                    clr_blck.setVisibility(View.GONE);
                } else if (background_color == 2) {
                    clr_wht.setVisibility(View.GONE);
                } else if (background_color == 3) {
                    clr_pink.setVisibility(View.GONE);
                } else if (background_color == 4) {
                    clr_red.setVisibility(View.GONE);
                } else if (background_color == 5) {
                    clr_blue.setVisibility(View.GONE);
                } else if (background_color == 6) {
                    clr_teal.setVisibility(View.GONE);
                } else if (background_color == 7) {
                    clr_orange.setVisibility(View.GONE);
                } else if (background_color == 8) {
                    clr_yellow.setVisibility(View.GONE);
                } else if (background_color == 9) {
                    clr_cyan.setVisibility(View.GONE);
                }

                clr_blck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.colorPrimary)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "1").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 1;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                        dialog.dismiss();
                    }
                });


                clr_wht.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.white)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "2").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 2;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                        dialog.dismiss();
                    }
                });

                clr_pink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.pink)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "3").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 3;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                        dialog.dismiss();
                    }
                });

                clr_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.red)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "4").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 4;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                        dialog.dismiss();
                    }
                });


                clr_blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.blue)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "5").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 5;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                        dialog.dismiss();
                    }
                });


                clr_teal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.teal)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "6").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 6;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                        dialog.dismiss();
                    }
                });


                clr_orange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.orange)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "7").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 7;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                        dialog.dismiss();
                    }
                });

                clr_yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.yellow)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "8").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 8;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                        dialog.dismiss();
                    }
                });

                clr_cyan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color", String.valueOf(R.color.cyan)).apply();
                        PreferenceUtil.putData(getApplicationContext()).putString("text_color_code", "9").apply();
//                        Toast.makeText(Setting.this, "Color selected.", Toast.LENGTH_SHORT).show();
                        text_color = 9;
                        ll_select_txt_clr.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.cyan));
                        tv_hello.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.cyan));
                        dialog.dismiss();
                    }
                });

            }


        });


        // Condition for show day switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_day", "").equalsIgnoreCase("1")) {
            switch_day.setChecked(true);
        } else {
            switch_day.setChecked(false);
        }

        switch_day.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_day", "1").apply();
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_day", "0").apply();
                }
            }
        });


        // Condition for 12 hr format switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_12_hr", "").equalsIgnoreCase("1")) {
            switch_12_hr.setChecked(true);
        } else {
            switch_12_hr.setChecked(false);
        }

        //when the hour changes so does the am/pm
        switch_12_hr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_12_hr", "1").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "1").apply();
                    if (switch_hour.isChecked())
                        switch_am_pm.setChecked(true);
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_12_hr", "0").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "0").apply();
                    switch_am_pm.setChecked(false);
                }
            }
        });

        // Condition for show hour switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_hour", "").equalsIgnoreCase("1")) {
            switch_hour.setChecked(true);
        } else {
            switch_hour.setChecked(false);
        }


        //when the hour is switched off so does the second and minutes
        switch_hour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_hour", "1").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_min", "1").apply();
                    switch_min.setChecked(true);
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "1").apply();
                    switch_sec.setChecked(true);

                    if (switch_12_hr.isChecked()) {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "1").apply();
                        switch_am_pm.setChecked(true);
                    }
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_hour", "0").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_min", "0").apply();
                    switch_min.setChecked(false);
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "0").apply();
                    switch_sec.setChecked(false);
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "0").apply();
                    switch_am_pm.setChecked(false);
                }
            }
        });

        // Condition for show min switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_min", "").equalsIgnoreCase("1")) {
            switch_min.setChecked(true);
        } else {
            switch_min.setChecked(false);
        }

        //when the minute goes off so does the second
        switch_min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_hour.isChecked()) {
                    if (isChecked) {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_min", "1").apply();

                        PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "1").apply();
                        switch_sec.setChecked(true);
                    } else {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_min", "0").apply();

                        PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "0").apply();
                        switch_sec.setChecked(false);
                    }
                } else {
                    switch_min.setChecked(false);
                }
            }
        });

        // Condition for show sec switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_sec", "").equalsIgnoreCase("1")) {
            switch_sec.setChecked(true);
        } else {
            switch_sec.setChecked(false);
        }

        switch_sec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_hour.isChecked() && switch_min.isChecked()) {
                    if (isChecked) {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "1").apply();
                    } else {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_sec", "0").apply();
                    }
                } else {
                    switch_sec.setChecked(false);
                }
            }
        });

        // Condition for show AM/PM switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_am_pm", "").equalsIgnoreCase("1")) {
            switch_am_pm.setChecked(true);
        } else {
            switch_am_pm.setChecked(false);
        }

        switch_am_pm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_hour.isChecked() && switch_12_hr.isChecked()) {
                    if (isChecked) {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "1").apply();
                    } else {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_am_pm", "0").apply();
                    }
                } else {
                    switch_am_pm.setChecked(false);
                }

            }
        });


        // Condition for show date switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_date", "").equalsIgnoreCase("1")) {
            switch_date.setChecked(true);
        } else {
            switch_date.setChecked(false);
        }

        switch_date.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_mnth.isChecked()) {
                    if (isChecked) {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_date", "1").apply();
                    } else {
                        PreferenceUtil.putData(getApplicationContext()).putString("switch_date", "0").apply();
                    }
                } else {
                    switch_date.setChecked(false);
                }
            }
        });


        // Condition for show month switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_mnth", "").equalsIgnoreCase("1")) {
            switch_mnth.setChecked(true);
        } else {
            switch_mnth.setChecked(false);
        }

        //when month goes off so does the day of the month
        switch_mnth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_mnth", "1").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_date", "1").apply();
                    switch_date.setChecked(true);
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_mnth", "0").apply();

                    PreferenceUtil.putData(getApplicationContext()).putString("switch_date", "0").apply();
                    switch_date.setChecked(false);
                }
            }
        });

        // Condition for show year switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_year", "").equalsIgnoreCase("1")) {
            switch_year.setChecked(true);
        } else {
            switch_year.setChecked(false);
        }

        switch_year.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_year", "1").apply();
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_year", "0").apply();
                }
            }
        });

        // Condition for show greeting switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_greeting", "").equalsIgnoreCase("1")) {
            switch_greeting.setChecked(true);
        } else {
            switch_greeting.setChecked(false);
        }

        switch_greeting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_greeting", "1").apply();
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_greeting", "0").apply();
                }
            }
        });

        // Condition for show graphic switch on/off
        if (PreferenceUtil.getData(getApplicationContext()).getString("switch_graphic", "").equalsIgnoreCase("1")) {
            switch_graphic.setChecked(true);
        } else {
            switch_graphic.setChecked(false);
        }

        switch_graphic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_graphic", "1").apply();
                } else {
                    PreferenceUtil.putData(getApplicationContext()).putString("switch_graphic", "0").apply();
                }
            }
        });


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
//            startActivity(new Intent(this, Setting.class));
//            finish();
        } else if (item.getItemId() == R.id.clock) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
