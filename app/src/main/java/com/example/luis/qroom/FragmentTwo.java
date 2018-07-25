package com.example.luis.qroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    WebView webView;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        webView = view.findViewById(R.id.webview);
        return view;
    }

    public void carregarSala(String id) {
        DatabaseReference firebaseSalasRef = FirebaseDatabase.getInstance().getReference("Salas").child(id);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Sala sala = dataSnapshot.getValue(Sala.class);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setSupportZoom(false);
                webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + sala.getUrl());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadSala:onCancelled", databaseError.toException());
            }
        };

        firebaseSalasRef.addListenerForSingleValueEvent(listener);
    }
}
