package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recycleview.Adapter.ContactAdapter;
import com.example.recycleview.Model.ContactModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ListView lv;

    RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList = new ArrayList<>();

    ContactAdapter adapter;

    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        actionButton = findViewById(R.id.add);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_dialog);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPno  = dialog.findViewById(R.id.edtPno);
                Button addBtn = dialog.findViewById(R.id.addbtn);

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", pno  = "";
                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtPno.getText().toString().equals("")){
                            pno = edtPno.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please enter the phone no.", Toast.LENGTH_SHORT).show();
                        }

                        arrayList.add(new ContactModel(name, pno));
                        adapter.notifyItemChanged(arrayList.size()-1);
                        recyclerView.scrollToPosition((arrayList.size()-1));

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        arrayList.add(new ContactModel("Ashu", "1234567"));
        arrayList.add(new ContactModel("G", "35987345"));
        arrayList.add(new ContactModel("S", "454564567"));

        adapter = new ContactAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
    }
}