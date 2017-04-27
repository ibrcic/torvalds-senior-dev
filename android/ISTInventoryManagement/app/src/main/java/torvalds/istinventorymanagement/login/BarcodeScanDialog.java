package torvalds.istinventorymanagement.login;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import torvalds.istinventorymanagement.R;

/**
 * Created by ivan on 2/24/17.
 */

public class BarcodeScanDialog extends DialogFragment implements ZBarScannerView.ResultHandler  {

    private static String TAG = "BarcodeScanDialog.class";
    private ZBarScannerView scannerView;
    private ViewGroup container;

    static BarcodeScanDialog newInstance() {
        BarcodeScanDialog f = new BarcodeScanDialog();
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
        // Do something with the result here
        Log.v(TAG, result.getContents()); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        scannerView.resumeCameraPreview(this);
    }
}
