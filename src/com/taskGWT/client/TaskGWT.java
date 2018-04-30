package com.taskGWT.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.taskGWT.client.Widgets.ItemsList;

public class TaskGWT implements EntryPoint {

    public void onModuleLoad() {
        ItemsList itemsList = new ItemsList();

        RootPanel.get("content").add(itemsList);
    }

}
