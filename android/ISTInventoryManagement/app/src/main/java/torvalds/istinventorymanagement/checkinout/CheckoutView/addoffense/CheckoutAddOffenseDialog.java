package torvalds.istinventorymanagement.checkinout.CheckoutView.addoffense;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Offense;
import torvalds.istinventorymanagement.model.StatusResponse;

import static torvalds.istinventorymanagement.Constants.SIGN_ITEMS_KEY;

/**
 * Created by ivan on 2/24/17.
 */

public class CheckoutAddOffenseDialog extends DialogFragment {

    private EditText etOffenseTitle;
    private EditText etOffenseDescription;

    public static CheckoutAddOffenseDialog newInstance(Item items) {
        CheckoutAddOffenseDialog f = new CheckoutAddOffenseDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SIGN_ITEMS_KEY, items);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Item item = (Item) getArguments().getSerializable(SIGN_ITEMS_KEY);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.checkout_offense_dialog, null);
        etOffenseTitle = (EditText) dialogView.findViewById(R.id.et_offense_title);
        etOffenseDescription = (EditText) dialogView.findViewById(R.id.et_offense_description);

        return new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setTitle(R.string.report_offense_title)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Report", (dialogInterface, i) -> {
                    String title = etOffenseTitle.getText().toString().trim();
                    String description = etOffenseDescription.getText().toString().trim();

                    if(title.isEmpty() || description.isEmpty()) {
                        //TODO: Error
                    } else {
                        reportOffense(item, title, description);
                    }


                })
                .create();
    }

    private void reportOffense(Item item, String title, String description) {
        Offense offense = new Offense();
        offense.setItemId(item.getId());
        offense.setUserId(item.getUserId());
        offense.setRentalId(item.getRentalId());
        offense.setOffenseName(title);
        offense.setOffenseDescription(description);
        offense.setOffenseDate("2017-05-03"); //TODO: this

        ISTInventoryClient.getApi().addOffense(offense).enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                //Toast.makeText(getContext(), "Offense added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                System.out.println("Error adding offense");
            }
        });
    }

}
