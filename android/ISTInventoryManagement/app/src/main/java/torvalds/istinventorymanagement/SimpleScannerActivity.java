package torvalds.istinventorymanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import torvalds.istinventorymanagement.bus.RxBusBarcodeScan;

import static torvalds.istinventorymanagement.Constants.SCAN_TYPE_KEY;


/**
 * Created by ivan on 4/22/17.
 */

public class SimpleScannerActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView mScannerView;
    private final String TAG = "Barcode";
    private Constants.ScanType scanType;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        mScannerView.setAutoFocus(true);
        mScannerView.setFlash(true);
        setContentView(mScannerView);                // Set the scanner view as the content view
        scanType = (Constants.ScanType) getIntent().getSerializableExtra(SCAN_TYPE_KEY);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getContents()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        RxBusBarcodeScan.instanceOf().barcodeScanned(rawResult.getContents(), scanType);
        finish();

    }
}