package torvalds.istinventorymanagement.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import torvalds.istinventorymanagement.R;

/**
 * Created by ivan on 2/24/17.
 */

public class CredentialsLoginDialog extends DialogFragment {

    MaterialEditText etUsername;
    MaterialEditText etPassword;

    static CredentialsLoginDialog newInstance() {
        CredentialsLoginDialog f = new CredentialsLoginDialog();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.credentials_dialog, null);
        etUsername = (MaterialEditText) dialogView.findViewById(R.id.et_username);
        etPassword = (MaterialEditText) dialogView.findViewById(R.id.et_password);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Login using your credentials")
                .setView(dialogView)
                .setPositiveButton("Login", (dialog, whichButton) -> {
                            //TODO: Check if input entered
                            ((LoginView)getActivity()).loginCredentialsEntered(
                                    etUsername.getText().toString(),
                                    etPassword.getText().toString());
                        }
                )
                .create();
    }


}
