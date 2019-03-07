package com.chutzpah.mvvmdemo.book.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;

import com.chutzpah.mvvmdemo.book.model.BookBean;

import java.util.ArrayList;

/**
 * Created by xiaowu on 2019/03/07.
 */
public class BookVM extends ViewModel {

    private final MutableLiveData<ArrayList<BookBean>> arrayListMutableLiveData;

    public BookVM() {
        arrayListMutableLiveData = new MutableLiveData<>();
    }

    //获取数据
    public MutableLiveData<ArrayList<BookBean>> fetchBookListData(int id) {

        //id 用来假装请求数据需要 ID


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ArrayList<BookBean> arrayList = new ArrayList<>();

                for (int i = 0; i < 10; i++) {

                    BookBean bookBean = new BookBean();
                    bookBean.setId("" + i);
                    bookBean.setId("name " + i);
                    arrayList.add(bookBean);
                }


                arrayListMutableLiveData.setValue(arrayList);
            }
        }, 2000);

        return arrayListMutableLiveData;
    }


}
