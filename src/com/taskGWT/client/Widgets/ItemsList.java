package com.taskGWT.client.Widgets;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.taskGWT.client.Entities.Person;

import java.util.Arrays;
import java.util.List;

public class ItemsList extends Composite {
    interface ItemsListUiBinder extends UiBinder<HTMLPanel, ItemsList> {
    }

    private static ItemsListUiBinder ourUiBinder = GWT.create(ItemsListUiBinder.class);

    private static final List<Person> PEOPLE = Arrays.asList(
            new Person("Aleksandr","Fomin"),
            new Person("Roman", "Fomin"));

    private PersonCell personCell = new PersonCell();

    public ItemsList() {
        initWidget(ourUiBinder.createAndBindUi(this));
        CellList<Person> cellList = new CellList<Person>(personCell);
        cellList.setRowCount(PEOPLE.size(),true);
        cellList.setRowData(0,PEOPLE);
        RootPanel.get().add(cellList);
    }
}