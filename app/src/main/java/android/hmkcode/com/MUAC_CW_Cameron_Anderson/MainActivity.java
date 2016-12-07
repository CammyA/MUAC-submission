package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // variables
    MySQLiteHelper db = new MySQLiteHelper(this);
    FragmentManager fmAboutFragment;
    public static final String PREFS_NAME = "LoginPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fmAboutFragment = this.getFragmentManager();

         /* Check if we successfully logged in before.
         * If we did, redirect to home page */

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(MainActivity.this, mcMainMenu.class);
            startActivity(intent);
        }
     }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonSignIn:

                //create an instance of the username object for database to check with
                //create an instance of the password object for database to check with
                EditText editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
                EditText editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);

                //convert data entered into editViews, into strings
                String userName = editTextUserName.getText().toString();
                String passWord = editTextPassword.getText().toString();

                // if no username, display alert
                if(TextUtils.isEmpty(userName))
                {
                    editTextUserName.setError("Must enter a username");
                    return;
                }
                // if no password, display alert
                if (TextUtils.isEmpty(passWord)) {
                    editTextPassword.setError("Must enter a password");
                    return;
                }

                // send username and password to check if it exists
                db.getUsername(userName, passWord);

                boolean gu = db.getUsername(userName, passWord);

                // if it exists go to new activity
                if (gu == true) {
                    //make SharedPreferences object
                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("logged", "logged");
                    editor.commit();

                    // create successful message
                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    // create new instnace of the intent
                    Intent main = new Intent(MainActivity.this, mcMainMenu.class);
                    //start the new intent
                    startActivity(main);
                    //clear username and password
                    editTextPassword.setText("");
                    editTextUserName.setText("");
                } else {
                    //user doesnt exsist
                    Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.buttonSignUp:

                // create new instnace of the intent
                Intent signup = new Intent(MainActivity.this, mcSignUp.class);
                //start the new intent
                startActivity(signup);
                // display redirecting message
                Toast.makeText(MainActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;

        }
    }
}

