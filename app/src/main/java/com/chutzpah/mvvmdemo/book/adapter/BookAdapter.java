package com.chutzpah.mvvmdemo.book.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chutzpah.mvvmdemo.R;
import com.chutzpah.mvvmdemo.book.model.BookBean;
import com.chutzpah.mvvmdemo.databinding.ItemBooksBinding;

import java.util.ArrayList;

/**
 * Created by xiaowu on 2019/03/07.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<BookBean> bookBeanArrayList;

    public void setBookBeanArrayList(ArrayList<BookBean> bookBeanArrayList) {
        this.bookBeanArrayList = bookBeanArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemBooksBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_books, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(bookBeanArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return bookBeanArrayList == null ? 0 : bookBeanArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBooksBinding itemBooksBinding;


        public ViewHolder(@NonNull ItemBooksBinding itemBooksBinding) {
            super(itemBooksBinding.getRoot());
            this.itemBooksBinding = itemBooksBinding;
        }

        public void bind(BookBean bookBean) {
            itemBooksBinding.setBook(bookBean);
            itemBooksBinding.executePendingBindings();

        }
    }

}
