package torvalds.istinventorymanagement.login;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import torvalds.istinventorymanagement.R;

/**
 * Created by ivan on 2/24/17.
 */

public class BarcodeLoginDialog extends DialogFragment implements ZXingScannerView.ResultHandler  {

    private ZXingScannerView scannerView;
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
        scannerView = new ZXingScannerView(getActivity());
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
        Toast.makeText(getActivity(), "Contents = " + result.getText() +
                ", Format = " + result.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(() -> scannerView.resumeCameraPreview(BarcodeLoginDialog.this), 2000);
    }
}
