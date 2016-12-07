package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class mcSignUp extends AppCompatActivity implements View.OnClickListener {
    MySQLiteHelper db = new MySQLiteHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_sign_up);
    }

    @Override
    public void onClick(View v) {

        //create an instance of the username object for database to check with
        //create an instance of the password object for database to check with
        EditText editTextUserName = (EditText) findViewById(R.id.editTextUsername);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //convert input to strings
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();

        // if username is empty then alert user
        if(TextUtils.isEmpty(userName))
        {
            editTextUserName.setError("Must enter a username");
            return;
        }
        // if password is empty then alert user
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Must enter a password");
            return;
        }


        db.getUsername(userName, password);

        boolean gu = db.getUsername(userName, password);
        // if username and password exsits
        if (gu == true) {
            //user is already in the databse
            Toast.makeText(mcSignUp.this, "User already exists", Toast.LENGTH_LONG).show();

        } else {
            //ad the new user to the databse
            db.addUser(new User(userName, password));
            Toast.makeText(mcSignUp.this, "Redirecting", Toast.LENGTH_SHORT).show();
            // redirect to the main menu for sign in
            Intent main = new Intent(mcSignUp.this, MainActivity.class);
            startActivity(main);
        }

    }
}
