package torvalds.istinventorymanagement.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import torvalds.istinventorymanagement.R;

/**
 * Created by Hassan Jegan Ndow on 4/2/2017.
 **/
public class CheckoutActivity extends AppCompatActivity {

    String productName = "";
    String productSerialNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent intent = getIntent();
        productName = intent.getStringExtra("productName");
        productSerialNumber = intent.getStringExtra("productSerialNumber");

        EditText editTextProductName = (EditText)findViewById(R.id.EditTextProductName);
        editTextProductName.setText(productName, TextView.BufferType.EDITABLE);
        EditText editTextProductSerialNumber = (EditText)findViewById(R.id.EditTextSerialNumber);
        editTextProductSerialNumber.setText(productSerialNumber, TextView.BufferType.EDITABLE);
    }


}
