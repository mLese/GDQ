package com.deceax.gdq;

import java.util.List;

/**
 * Created by Lese on 11/23/15.
 */
public interface ScheduleConsumer {

    public void onScheduleRetrieved(List<Run> runList);

}
