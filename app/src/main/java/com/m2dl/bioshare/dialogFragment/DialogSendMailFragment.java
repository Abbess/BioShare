package com.m2dl.bioshare.dialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.m2dl.bioshare.R;
import com.m2dl.bioshare.mail.Mail;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogSendMailFragment} interface
 * to handle interaction events.
 * Use the {@link DialogSendMailFragment} factory method to
 * create an instance of this fragment.
 */
public class DialogSendMailFragment extends DialogFragment {

    private String addrMailChoisi;

    private Mail sender;

    private EditText mEditText;


    public DialogSendMailFragment() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View content = inflater.inflate(R.layout.fragment_dialog_send_mail, null);
        mEditText = (EditText) content.findViewById(R.id.mailAddress);

        builder.setView(content)
                // Add action buttons
                .setPositiveButton(getString(R.string.confirm_envoi_fragment), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        addrMailChoisi = mEditText.getText().toString();

                        if (!addrMailChoisi.isEmpty()) {
                            // mListener.onDialogPositiveClick(DialogSendMailFragment.this);

                            Thread t = new Thread() {
                                @Override
                                public void run() {
                                    sender = new Mail();
                                    sender.sendMailTo(addrMailChoisi, "Subject Test", "Body TEST !! ");
                                }
                            };
                            t.start();
                        }
                    }
                })
                .setNegativeButton(getString(R.string.cancel_name), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // mListener.onDialogNegativeClick(DialogSendMailFragment.this);
                    }
                });
        return builder.create();
    }


    public String getAddressMail() {

        return addrMailChoisi;

    }


}
