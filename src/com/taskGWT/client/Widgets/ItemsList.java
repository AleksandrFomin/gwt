package com.taskGWT.client.Widgets;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.taskGWT.client.Entities.Person;

import java.util.Arrays;
import java.util.List;

public class ItemsList extends Composite implements IsWidget {
    interface ItemsListUiBinder extends UiBinder<HTMLPanel, ItemsList> {
    }

    private static ItemsListUiBinder ourUiBinder = GWT.create(ItemsListUiBinder.class);

    private static final List<Person> PEOPLE = Arrays.asList(
            new Person("Aleksandr", "Fomin"),
            new Person("Roman", "Fomin"));

    private PersonCell personCell;

    private CellList<Person> cellList;

    public List<Person> getData() {
        return data;
    }

    public ListDataProvider<Person> getDataProvider() {
        return dataProvider;
    }

    private List<Person> data;

    private ListDataProvider<Person> dataProvider;

    @UiField
    HTMLPanel listOfEntities;
    @UiField
    HTMLPanel entWindow;

    public ItemsList() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initEntityList();
    }

    private void initEntityList() {
        personCell = new PersonCell();

        cellList = new CellList<>(personCell);

        EntityWindow entityWindow = new EntityWindow(this);

        dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(cellList);

        data = dataProvider.getList();
        for (Person p : PEOPLE) {
            data.add(p);
        }

        SingleSelectionModel<Person> selectionModel = new SingleSelectionModel<>();
        cellList.setSelectionModel(selectionModel);

        cellList.addDomHandler(event -> {
            Person selected = selectionModel.getSelectedObject();
            entityWindow.showEntity(selected);
        }, DoubleClickEvent.getType());

        listOfEntities.add(cellList);
        entWindow.add(entityWindow);
    }

}