package com.example.admin.mommammarket;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A placeholder fragment containing a simple view.
 */
public class Order_ActivityFragment extends Fragment {

    private Button call_phone_btn;
    private Button sms_phone;
    private EditText sms_name,sms_order,sms_address;


    public Order_ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        call_phone_btn = (Button) rootView.findViewById(R.id.call_phone);
        sms_phone = (Button) rootView.findViewById(R.id.sms_phone);

        call_phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(Intent.ACTION_CALL);
                newActivity.setData(Uri.parse("tel:0933803302"));
                startActivity(newActivity);
            }
        });
        sms_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_Sms_Phone();
            }
        });

        return rootView;
    }//End onCreateView

    private void send_Sms_Phone() {

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater(getArguments());

        View view = inflater.inflate(R.layout.alertdialog_layout, null);
        builder.setView(view);
        sms_name = (EditText)view.findViewById(R.id.sms_name);
        sms_order =(EditText)view.findViewById(R.id.sms_order);
        sms_address = (EditText)view.findViewById(R.id.sms_address);

        builder.setPositiveButton("สั่งซื้อ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String lineSep = System.getProperty("line.separator");
                SmsManager smsManager = SmsManager.getDefault();
                String strPhoneNo = "0933803302"; //เบอร์โทรที่ส่ง SMS
                String strMessage = "Name : "+sms_name.getText().toString()+lineSep+"Order : "+sms_order.getText().toString()
                        +lineSep+"Ads : "+sms_address.getText().toString();
                smsManager.sendTextMessage(strPhoneNo, null, strMessage, null, null);
                Toast.makeText(getActivity(), "ดำเนินการแล้ว",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.show();
    }


}//End Fregment


