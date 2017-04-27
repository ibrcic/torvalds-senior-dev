package torvalds.istinventorymanagement.bus;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;
import torvalds.istinventorymanagement.Constants;

/**
 * Created by ivan on 4/14/17.
 */

public class RxBusBarcodeScan {

    private static RxBusBarcodeScan instance;

    private PublishRelay<String> itemRelay = PublishRelay.create();
    private PublishRelay<String> studentRelay = PublishRelay.create();

    public static RxBusBarcodeScan instanceOf() {

        if (instance == null) {
            instance = new RxBusBarcodeScan();
        }

        return instance;
    }

    public void barcodeScanned(String barcode, Constants.ScanType type) {
        if(type == Constants.ScanType.ITEM) {
            itemRelay.accept(barcode);
        } else if (type == Constants.ScanType.STUDENT) {
            studentRelay.accept(barcode);
        }

    }

    public Observable<String> getBarcodeScans(Constants.ScanType type) {

        if (type == Constants.ScanType.STUDENT) {
            return studentRelay;
        }

        return itemRelay;
    }

}
