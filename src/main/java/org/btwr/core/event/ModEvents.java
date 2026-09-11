package org.btwr.core.event;

import org.btwr.core.event.events.ModBlockUseEvents;
import org.btwr.core.event.events.ModNetworkingEvents;

public class ModEvents {
    public static void initialize() {
        // Not finished adding all items to item groups, so it's disabled
        //ModItemGroupEvents.initialize();

        ModNetworkingEvents.initialize();
        ModBlockUseEvents.initialize();
    }
}