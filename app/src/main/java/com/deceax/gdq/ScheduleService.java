package com.deceax.gdq;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Lese on 12/26/15.
 */
public interface ScheduleService {
    @GET("/runs.json")
    Call<Schedule> getSchedule();
}
