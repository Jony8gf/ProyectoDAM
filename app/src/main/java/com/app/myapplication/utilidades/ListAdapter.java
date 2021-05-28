package com.app.myapplication.utilidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.myapplication.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ItemListener itemListener;


    public ListAdapter(List<ListElement> itemList, Context context, ItemListener itemListener){
        this.mInflater = LayoutInflater.from(context);
        this.context =context;
        this.mData=itemList;
        this.itemListener = itemListener;
    }


    @Override
    public int getItemCount() {return  mData.size();}

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view, itemListener);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<ListElement> items) { mData = items;}



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View containerLayout;
        public View txtNumber;
        ImageView iconImage;
        ImageView typeGameImage;
        TextView name, personas;

        ViewHolder(View itemView, final ItemListener itemListener){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.minijuego);
            personas = itemView.findViewById(R.id.personas);
            typeGameImage = itemView.findViewById(R.id.typeGameImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onClick(getAdapterPosition());
                }
            });
        }

        void bindData (final ListElement item){
            iconImage.setBackgroundResource(item.getColor());
            name.setText(item.getNombre());
            personas.setText(item.getJugadores());
            typeGameImage.setBackgroundResource(item.getTypeGame());
        }

    }
    public  interface  RecyclerItemClick{
        void itemClick(ViewHolder list);
    }

}
