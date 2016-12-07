package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class mcTimetable extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fmAboutFragment;
    private ListView lv;
    private ListView Lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_timetable);

        fmAboutFragment = this.getFragmentManager();

        lv = (ListView) findViewById(R.id.ListViewTimes6);
        Lv = (ListView) findViewById(R.id.ListViewTimes21);
        // Instanciating an array list.

        String[] bus_times6 = {"05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","00:00"};
        String[] bus_times21 ={"05:30","06:30","07:30","08:30","09:30","10:30","11:30","12:30","13:30","14:30","15:30","16:30","17:30","18:30","19:30","20:30","21:30","22:30","23:30","00:30"};
        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bus_times6 );
        ArrayAdapter<String> arrayAdapter21 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bus_times21 );
        lv.setAdapter(arrayAdapter6);
        Lv.setAdapter(arrayAdapter21);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnChooseBus:
                Intent map = new Intent(mcTimetable.this, MapsActivity.class);
                startActivity(map);
                break;
            default:
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

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
}
