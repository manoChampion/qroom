package com.example.luis.qroom;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {

    EditText inputNome, inputDesc, inputUrl;
    Spinner spinner;

    DatabaseReference databaseSala;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        Button btn = view.findViewById(R.id.btn_submit);
        inputNome = view.findViewById(R.id.etxt_nome);
        inputDesc = view.findViewById(R.id.etxt_descricao);
        inputUrl = view.findViewById(R.id.etxt_url);
        spinner = view.findViewById(R.id.spinner);

        List<String> blocos = new ArrayList<>();
        blocos.add("Administrativo");
        blocos.add("Agroindustria");
        blocos.add("Agropecuária");
        blocos.add("Convivência");
        blocos.add("Sala de Aula");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, blocos);

        spinner.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarSala();
            }
        });
        databaseSala = FirebaseDatabase.getInstance().getReference("Salas");

        return view;
    }

    public void cadastrarSala() {
        String id = databaseSala.push().getKey();
        String nome  = inputNome.getText().toString();
        String bloco =  spinner.getSelectedItem().toString();
        String desc = inputDesc.getText().toString();
        String url = inputUrl.getText().toString();

        if (!TextUtils.isEmpty(id) || !TextUtils.isEmpty(nome) || !TextUtils.isEmpty(bloco) || !TextUtils.isEmpty(desc)) {
            Sala sala = new Sala(id, nome, bloco, desc, url);
            databaseSala.child(id).setValue(sala);
            Toast.makeText(this.getContext(), "Sala criada com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        }
    }

}
