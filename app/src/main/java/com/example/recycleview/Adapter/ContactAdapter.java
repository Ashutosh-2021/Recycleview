package com.example.recycleview.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.MainActivity;
import com.example.recycleview.Model.ContactModel;
import com.example.recycleview.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<ContactModel> ContactLit;
    Context context;

    public ContactAdapter(Context context, ArrayList<ContactModel> ContactList) {
        this.context = context;
        this.ContactLit = ContactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tName.setText(ContactLit.get(position).getName());
        holder.tPhone.setText(ContactLit.get(position).getPno());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_dialog);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtPno = dialog.findViewById(R.id.edtPno);
                Button updateBtn = dialog.findViewById(R.id.updateBtn);

                edtName.setText(ContactLit.get(position).getName());
                edtPno.setText(ContactLit.get(position).getPno());

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", pno  = "";
                        if (!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }else{
                            Toast.makeText(context, "Please enter the name", Toast.LENGTH_SHORT).show();
                        }
                        if (!edtPno.getText().toString().equals("")){
                            pno = edtPno.getText().toString();
                        }else{
                            Toast.makeText(context, "Please enter the phone no.", Toast.LENGTH_SHORT).show();
                        }

                        ContactLit.set(position, new ContactModel(name, pno));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure want to delete")
                        .setIcon(R.drawable.delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    ContactLit.remove(position);
                                    notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                    builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return ContactLit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tName, tPhone;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.tvName);
            tPhone = itemView.findViewById(R.id.tvPno);
            layout = itemView.findViewById(R.id.row);
        }
    }
}
