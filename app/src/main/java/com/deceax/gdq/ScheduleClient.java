package com.deceax.gdq;

import android.util.Log;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ScheduleClient {

    ScheduleConsumer consumer;

    public ScheduleClient(ScheduleConsumer consumer) {
        this.consumer = consumer;
    }

    public void fetchSchedule() {
        // fetch schedule and provide to consumer
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://deceax.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ScheduleService scheduleService = retrofit.create(ScheduleService.class);

        Call<Schedule> call = scheduleService.getSchedule();
        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Response<Schedule> response, Retrofit retrofit) {
                Log.d("Client", response.toString());
                consumer.onScheduleRetrieved(response.body().getRuns());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
