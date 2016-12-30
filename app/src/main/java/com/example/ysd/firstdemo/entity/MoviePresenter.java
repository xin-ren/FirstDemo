package com.example.ysd.firstdemo.entity;

import android.view.View;
import android.widget.Toast;

/**
 * Created by 任新 on 2016/12/30 16:19.
 * Function: MoviePresenter
 * Desc:
 */
public class MoviePresenter {
    public void buyTicket(View view, Movie movie) {
        Toast.makeText(view.getContext(), "buy ticket: " + movie.name, Toast.LENGTH_SHORT).show();
    }
}
