package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Cammy on 04/12/16.
 */

public class mcAboutFragment extends android.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        android.app.AlertDialog.Builder mcAboutDialog = new android.app.AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage("This application will allow you to locate the nearest bus stops and their arrival times.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.ic_help_outline_black_24dp);
        // Create the AlertDialog object and return it
        return mcAboutDialog.create();
    }
}
