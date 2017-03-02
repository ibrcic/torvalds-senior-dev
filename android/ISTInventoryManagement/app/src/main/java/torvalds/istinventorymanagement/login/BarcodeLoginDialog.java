package torvalds.istinventorymanagement.login;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.barcode.Barcode;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import torvalds.istinventorymanagement.R;
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;

/**
 * Created by ivan on 2/24/17.
 */

public class BarcodeLoginDialog extends DialogFragment implements ZBarScannerView.ResultHandler {

    private ZBarScannerView scannerView;
    private ViewGroup container;

    static BarcodeLoginDialog newInstance() {
        BarcodeLoginDialog f = new BarcodeLoginDialog();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.barcode_dialog, null);
        container = (ViewGroup) dialogView.findViewById(R.id.content_frame);
        scannerView = new ZBarScannerView(getActivity());
        scannerView.setAutoFocus(true);
        container.addView(scannerView);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Scan your Student ID to log in")
                .setView(dialogView)
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        System.out.println("Scanned barcode: " + result.getContents());
    }
}
