package com.app.pengeluaranque.view.add;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.pengeluaranque.R;
import com.app.pengeluaranque.databinding.ActivityAddDataBinding;
import com.app.pengeluaranque.model.entity.Pengeluaran;

public class AddDataActivity extends AppCompatActivity {

    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    // Untuk kebutuhan data yang akan dipakai pada Activitu AddData
    public static void startActivity(Context context, boolean isEdit, Pengeluaran pengeluaran) {
        Intent intent = new Intent(new Intent(context, AddDataActivity.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, pengeluaran);
        context.startActivity(intent);
    }

    private ActivityAddDataBinding binding;
    private AddDataViewModel addDataViewModel;

    private boolean mIsEdit = false;
    private int mUid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        addDataViewModel = ViewModelProviders.of(this).get(AddDataViewModel.class);

        loadData();
        initAction();
    }

    private void initAction() {
        binding.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = binding.etKeterangan.getText().toString();
                String price = binding.etHarga.getText().toString();

                if (note.isEmpty() || price.isEmpty()) {
                    Toast.makeText(AddDataActivity.this, getString(R.string.error_message_form_empty),
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mIsEdit) {
                        addDataViewModel.updatePengeluaran(mUid, note, Integer.parseInt(price));
                    } else {
                        addDataViewModel.addPengeluaran(note, Integer.parseInt(price));
                    }
                    finish();
                }
            }
        });
    }

    private void loadData() {
        mIsEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (mIsEdit) {
            Pengeluaran pengeluaran = getIntent().getParcelableExtra(KEY_DATA);
            if (pengeluaran != null) {
                mUid = pengeluaran.uid;
                String note = pengeluaran.keterangan;
                int price = pengeluaran.harga;

                binding.etKeterangan.setText(note);
                binding.etHarga.setText(String.valueOf(price));
            }
        }
    }
}
