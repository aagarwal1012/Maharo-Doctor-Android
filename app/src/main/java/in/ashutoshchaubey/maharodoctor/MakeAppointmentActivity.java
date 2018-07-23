package in.ashutoshchaubey.maharodoctor;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Service;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MakeAppointmentActivity extends AppCompatActivity{

    EditText date, time;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        final InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);

        date = (EditText) findViewById(R.id.appointment_date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                final TextView dateValidityText = (TextView) findViewById(R.id.date_warning);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MakeAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar cal = Calendar.getInstance();
                                if(year<cal.get(Calendar.YEAR)){
                                    dateValidityText.setVisibility(View.VISIBLE);
                                }else {
                                    if(year==cal.get(Calendar.YEAR)){
                                        if(monthOfYear<cal.get(Calendar.MONTH)){
                                            dateValidityText.setVisibility(View.VISIBLE);
                                        }else if(monthOfYear == cal.get(Calendar.MONTH)){
                                            if(dayOfMonth<=cal.get(Calendar.DAY_OF_MONTH)){
                                                dateValidityText.setVisibility(View.VISIBLE);
                                            }else{
                                                dateValidityText.setVisibility(View.GONE);
                                            }
                                        }else{
                                            dateValidityText.setVisibility(View.GONE);
                                        }
                                    }else{
                                        dateValidityText.setVisibility(View.GONE);
                                    }
                                }
                                imm.hideSoftInputFromWindow(date.getWindowToken(),0);
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        time = (EditText) findViewById(R.id.appointment_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                final TextView timeValidityText = (TextView) findViewById(R.id.time_warning);
                Calendar cal = Calendar.getInstance();
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MakeAppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {


                                if(hourOfDay<9){
                                    timeValidityText.setVisibility(View.VISIBLE);
                                }else if(hourOfDay>=21){
                                    timeValidityText.setVisibility(View.VISIBLE);
                                }else{
                                    timeValidityText.setVisibility(View.GONE);
                                }
                                imm.hideSoftInputFromWindow(time.getWindowToken(),0);
                                time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
