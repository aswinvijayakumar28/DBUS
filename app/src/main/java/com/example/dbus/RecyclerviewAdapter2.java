package com.example.dbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecyclerviewAdapter2 extends RecyclerView.Adapter<RecyclerviewAdapter2.MyHolder> {

    List<Listdata> listdata;

    public RecyclerviewAdapter2(List<Listdata> listdata) {
        this.listdata = listdata;
    }

    @Override
    public RecyclerviewAdapter2.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview2, parent, false);

        RecyclerviewAdapter2.MyHolder myHolder = new RecyclerviewAdapter2.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(RecyclerviewAdapter2.MyHolder holder, int position) {
        Listdata data = listdata.get(position);
        holder.vmessage.setText(data.getContent());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView vmessage;

        public MyHolder(View itemView) {
            super(itemView);
            vmessage = (TextView) itemView.findViewById(R.id.vmessage);


        }
    }

}