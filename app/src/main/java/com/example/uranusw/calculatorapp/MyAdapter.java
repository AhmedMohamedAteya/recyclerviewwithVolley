package com.example.uranusw.calculatorapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>
{

    private Context cnx;
    private List<Model> arrayList;

    public MyAdapter(Context cnx, List<Model> arrayList)
    {
        this.cnx = cnx;
        this.arrayList = arrayList;
    }

    @Override
    public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(cnx);
        View v = inflater.inflate(R.layout.custom_row, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyHolder holder, int position)
    {
        final Model model = arrayList.get(position);
        holder.name.setText(model.getName());
        holder.num.setText(model.getNum());
        holder.call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + model.getNum()));
                cnx.startActivity(callIntent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        TextView name, num;
        Button call;
        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);
            call = itemView.findViewById(R.id.call);
        }
    }
}