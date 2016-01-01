package com.deceax.gdq;

import retrofit.Call;
import retrofit.http.GET;

public interface ScheduleService {
    @GET("/runs.json")
    Call<Schedule> getSchedule();
}
