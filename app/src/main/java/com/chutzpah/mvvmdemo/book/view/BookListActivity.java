package com.chutzpah.mvvmdemo.book.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.chutzpah.mvvmdemo.R;
import com.chutzpah.mvvmdemo.book.adapter.BookAdapter;
import com.chutzpah.mvvmdemo.book.model.BookBean;
import com.chutzpah.mvvmdemo.book.vm.BookVM;
import com.chutzpah.mvvmdemo.databinding.ActivityBookListBinding;

import java.util.ArrayList;

/**
 * Created by xiaowu on 2019/03/07.
 */
public class BookListActivity extends AppCompatActivity {

    BookAdapter bookAdapter;


    public static Intent prepareIntent(int id, AppCompatActivity activity) {
        Intent i = new Intent(activity, BookListActivity.class);
        i.putExtra("key_id", id);
        return i;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBookListBinding bookListBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_book_list);

        int id = getIntent().getIntExtra("key_id", -1);


        bookListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookAdapter = new BookAdapter();

        bookListBinding.recyclerView.setAdapter(bookAdapter);

        BookVM bookVM = ViewModelProviders.of(this).get(BookVM.class);


        bookVM.fetchBookListData(id).observe(this, new Observer<ArrayList<BookBean>>() {
            @Override
            public void onChanged(@Nullable ArrayList<BookBean> bookBeans) {
                if (bookBeans == null) return;

                bookAdapter.setBookBeanArrayList(bookBeans);
                bookAdapter.notifyDataSetChanged();


            }
        });


    }
}
