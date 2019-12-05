package com.example.sixthhomework.recyclerview;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sixthhomework.R;

import java.util.ArrayList;

public class MyRecyclerAdapt extends RecyclerView.Adapter<MyRecyclerAdapt.MyInnerViewHolder> {
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> number = new ArrayList<>();
    private ArrayList<String> date =new ArrayList<>();
    private Context context;
    private Activity activity;

    public MyRecyclerAdapt(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyInnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new MyInnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyInnerViewHolder holder, int position) {
        holder.tvName.setText(name.get(position));
        holder.tvDate.setText(date.get(position));
        holder.tvNumber.setText(number.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public void setData(ArrayList<String> name,ArrayList<String> number,ArrayList<String> date){
            this.name=name;
            this.number=number;
            this.date=date;
    }
    public class MyInnerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDate;
        TextView tvNumber;

        public MyInnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvDate = itemView.findViewById(R.id.date);
            tvNumber =itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setTitle("你要做什么?");
                    final String[] items = {"打电话", "发短信"};
                    builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    checkCallPermission();
                                    break;
                                case 1:
                                    checkSendPermission();
                                    break;
                            }
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });
        }

        private void checkSendPermission() {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS)) {
                    Toast.makeText(context, "请在应用设置中授予权限", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, 1106);
                }
            else {
                send();
            }
        }

        private void checkCallPermission() {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(context, "请在应用设置中授予权限", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1106);
                }
            else {
                call();
            }
        }

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case 1106:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        call();
                    } else {
                        Toast.makeText(activity, "权限申请被拒绝", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:

            }
        }

        private void call() {
            try {
                Toast.makeText(context, "call", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+number.get(getAdapterPosition())));
                activity.startActivity(intent);

            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        private void send() {
            try {
                Toast.makeText(context, "send", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+number.get(getAdapterPosition())));
                activity.startActivity(intent);

            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
