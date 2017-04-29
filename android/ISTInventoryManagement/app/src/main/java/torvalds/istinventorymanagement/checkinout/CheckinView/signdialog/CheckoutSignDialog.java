package torvalds.istinventorymanagement.checkinout.CheckinView.signdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.bus.RxBusReservation;
import torvalds.istinventorymanagement.model.Item;
import torvalds.istinventorymanagement.model.Reservation;
import torvalds.istinventorymanagement.model.ReservationResponse;
import torvalds.istinventorymanagement.model.Student;

import static torvalds.istinventorymanagement.Constants.SIGN_ITEMS_KEY;
import static torvalds.istinventorymanagement.Constants.SIGN_STUDENT_KEY;

/**
 * Created by ivan on 2/24/17.
 */

public class CheckoutSignDialog extends DialogFragment {

    public static CheckoutSignDialog newInstance(ArrayList<Item> items, Student student) {
        CheckoutSignDialog f = new CheckoutSignDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SIGN_ITEMS_KEY, items);
        bundle.putSerializable(SIGN_STUDENT_KEY, student);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        List<Item> items = (List<Item>) getArguments().getSerializable(SIGN_ITEMS_KEY);
        Student student = (Student) getArguments().getSerializable(SIGN_STUDENT_KEY);

        ItemsAdapter itemsAdapter = new ItemsAdapter(items);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.checkout_sign_dialog, null);
        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.sign_items_list);
        recyclerView.setAdapter(itemsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        return new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                .setTitle(R.string.checkout_sign_dialog_title)
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("Checkout", (dialogInterface, i) -> {
                    checkoutItems(items, student.getUserId());
                })
                .create();
    }

    private void checkoutItems(List<Item> items, long borrowerId) {

        ISTInventoryClient.InventoryApi api = ISTInventoryClient.getApi();
        List<Observable<ReservationResponse>> reservations = new ArrayList<>();

        for (Item item : items) {
            Reservation reservation = new Reservation();
            reservation.setBorrowerId(borrowerId);
            reservation.setSignature("signature"); // Replace with img sometimes
            reservation.setItemTypeId(item.getItemTypeId());
            reservation.setIdItem(item.getId());
            reservations.add(api.addReservation(reservation));
        }

        // Connect all requests into one

        Observable.zip(reservations, (results) -> results)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results -> RxBusReservation.instanceOf().reservationMade(borrowerId),
                        error -> System.out.println("Error"));
    }



    private class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

        List<Item> items;

        public ItemsAdapter(List<Item> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_items_sign, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = items.get(position);
            holder.position.setText(position + 1 + ".");
            holder.name.setText(item.getName() + " " + item.getManufacturer() + " " + item.getModel());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView position;
            private final TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                this.position = (TextView) itemView.findViewById(R.id.sign_item_number);
                this.name = (TextView) itemView.findViewById(R.id.sign_number_name);
            }
        }
    }
}
