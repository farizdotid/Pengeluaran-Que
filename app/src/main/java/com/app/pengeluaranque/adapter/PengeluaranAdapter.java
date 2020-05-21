package com.app.pengeluaranque.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.annotation.NonNull;

import com.app.pengeluaranque.R;
import com.app.pengeluaranque.databinding.ItemPengeluaranBinding;
import com.app.pengeluaranque.model.entity.Pengeluaran;
import com.app.pengeluaranque.utils.FunctionHelper;
import com.app.pengeluaranque.view.main.MainActivity;

public class PengeluaranAdapter extends
        RecyclerView.Adapter<PengeluaranAdapter.ViewHolder> {

    private static final String TAG = PengeluaranAdapter.class.getSimpleName();

    private Context context;
    private List<Pengeluaran> list;
    private PengeluaranAdapterCallback mAdapterCallback;
    private ItemPengeluaranBinding binding;

    public PengeluaranAdapter(Context context, List<Pengeluaran> list, PengeluaranAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemPengeluaranBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pengeluaran item = list.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<Pengeluaran> pengeluarans){
        this.list = pengeluarans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull ItemPengeluaranBinding itemView) {
            super(itemView.getRoot());

            itemView.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Pengeluaran pengeluaran = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(pengeluaran);
                    return true;
                }
            });


            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pengeluaran pengeluaran = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(pengeluaran);
                }
            });
        }

        void bindData(Pengeluaran item) {
            int price = item.harga;
            String initPrice = FunctionHelper.rupiahFormat(price);
            binding.tvPrice.setText(initPrice);

            String note = item.keterangan;
            binding.tvNote.setText(note);
        }
    }

    public interface PengeluaranAdapterCallback {
        void onEdit(Pengeluaran pengeluaran);
        void onDelete(Pengeluaran pengeluaran);
    }
}