package me.talksy.android;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by omarq on 09-Aug-16.
 */

class contactsRow {

    String image;
    String name;
    String number;

    contactsRow(String image, String name, String number) {
        this.image = image;
        this.name = name;
        this.number = number;
    }


}

public class ContactsAdapter extends BaseAdapter {


    private Context context;

    ArrayList<contactsRow> contactsArray;

    public ContactsAdapter(Context context) {
        this.context = context;

        contactsArray = new ArrayList<contactsRow>();

        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String image = ContactsContract.CommonDataKinds.Photo.PHOTO_FILE_ID;
            //Toast.makeText(getActivity().getApplicationContext(),name, Toast.LENGTH_LONG).show();
            System.out.println(image);
            contactsArray.add(new contactsRow(image,name,phoneNumber));

        }

        phones.close();

    }
    @Override
    public int getCount() {
        return contactsArray.size();
    }

    @Override
    public Object getItem(int i) {

        return contactsArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        // Setup layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Get the base design layout
        View row = inflater.inflate(R.layout.contact_baserow,viewGroup,false);

        // Get the specific elements
        ImageView IMAGE = (ImageView) row.findViewById(R.id.imageView);
        TextView NAME = (TextView) row.findViewById(R.id.nameView);
        TextView NUMBER = (TextView) row.findViewById(R.id.numberView);

        // Get the specified row and set to temp variable
        contactsRow temp = contactsArray.get(i);

        if(contactsArray.size() == 0) {
            NAME.setText("No contacts :(");
            NUMBER.setText("Add someone!");
            IMAGE.setVisibility(View.INVISIBLE);
        } else {

            // Setup the name, number and image:
            NAME.setText(temp.name);
            NUMBER.setText(temp.number);
            //IMAGE.setImageDrawable(Drawable.createFromPath("@mipmap/ic_launcher"));
        }
        return row;
    }

}
