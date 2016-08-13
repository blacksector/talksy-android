package me.talksy.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class Tab3 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tab3, container, false);

        final ContactsAdapter allContacts = new ContactsAdapter(getActivity());

        final ListView lv = (ListView) rootView.findViewById(R.id.contactsList);

        lv.setAdapter(allContacts);

        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                contactsRow contact = (contactsRow) new ContactsAdapter(getActivity()).getItem(position);

                //System.out.println(contact.name);

                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("EXTRA_CONTACT_IMAGE", contact.image);
                intent.putExtra("EXTRA_CONTACT_NAME", contact.name);
                intent.putExtra("EXTRA_CONTACT_NUMBER", contact.number);
                startActivity(intent);
            }
        });

        return rootView;

    }

}
