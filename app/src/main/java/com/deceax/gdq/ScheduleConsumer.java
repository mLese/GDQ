package com.deceax.gdq;

import java.util.List;

public interface ScheduleConsumer {

    public void onScheduleRetrieved(List<Run> runList);
    public void onFailure(Throwable throwable);
}
