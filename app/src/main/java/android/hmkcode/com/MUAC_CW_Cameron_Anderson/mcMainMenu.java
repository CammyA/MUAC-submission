package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class mcMainMenu extends AppCompatActivity implements View.OnClickListener{
    //variables
    public static final String PREFS_NAME = "LoginPrefs";
    FragmentManager fmAboutFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_main_menu);

        //dialog fragment initialiser
        fmAboutFragment = this.getFragmentManager();
    }

    //create options menu
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    // options menu selection
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.about:
                DialogFragment mcAboutFragment = new mcAboutFragment();
                mcAboutFragment.show(fmAboutFragment,"menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonMap:
                //go to map screen
                Intent map = new Intent(mcMainMenu.this, MapsActivity.class);
                startActivity(map);

                break;

            case R.id.buttonTimetable:
                //go to timetable screen
                Intent timetable = new Intent(mcMainMenu.this,mcTimetable.class);
                startActivity(timetable);
              break;

            case R.id.buttonLogOut:
                // log out and return to the main menu
                // stops users going back into page without being logged in
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("logged");
                editor.commit();


                Intent main = new Intent(mcMainMenu.this, MainActivity.class);
                startActivity(main);
                finish();

                break;

            default:
                break;

        }
    }
}

