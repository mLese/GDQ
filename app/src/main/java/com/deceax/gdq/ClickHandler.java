package com.deceax.gdq;

import android.view.View;

public class ClickHandler {

    private View runDetails;

    public ClickHandler(View itemView) {
        runDetails = itemView;
    }

    public void onClick(View view) {
        if (runDetails.getVisibility() == View.GONE) {
            runDetails.setVisibility(View.VISIBLE);
        } else {
           runDetails.setVisibility(View.GONE);
        }
    }
}
