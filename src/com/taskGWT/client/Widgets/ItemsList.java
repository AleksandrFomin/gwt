package com.taskGWT.client.Widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.taskGWT.client.Entities.Person;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ItemsList extends Composite implements IsWidget {
    interface ItemsListUiBinder extends UiBinder<HTMLPanel, ItemsList> {
    }

    private static ItemsListUiBinder ourUiBinder = GWT.create(ItemsListUiBinder.class);

    private static final List<Person> PEOPLE = Arrays.asList(
            new Person("Aleksandr", "Fomin", new Date(100000000), new Time(100)),
            new Person("Roman", "Fomin", new Date(2000), new Time(400)));

    private PersonCell personCell;
    private CellList<Person> cellList;
    private MultiSelectionModel<Person> selectionModel;
    private List<Person> data;
    private ListDataProvider<Person> dataProvider;
    private EntityWindow entityWindow;

    @UiField
    HorizontalPanel horizontalPanel;
    @UiField
    VerticalPanel listPanel;
    @UiField
    Button deleteBtn;
    @UiField
    Button addBtn;
    @UiField
    HorizontalPanel btnPanel;

    public ItemsList() {
        initWidget(ourUiBinder.createAndBindUi(this));
        init();
    }

    private void init() {
        deleteBtn.setEnabled(false);
        deleteBtn.setStyleName("buttons");
        deleteBtn.addStyleName("deleteBtn");
        addBtn.setStyleName("buttons");
        addBtn.addStyleName("addBtn");

        personCell = new PersonCell();
        cellList = new CellList<>(personCell);
        entityWindow = new EntityWindow(this);
        dataProvider = new ListDataProvider<>();

        dataProvider.addDataDisplay(cellList);

        data = dataProvider.getList();
        data.addAll(PEOPLE);

        selectionModel = new MultiSelectionModel<>();
        cellList.setSelectionModel(selectionModel);

        selectionModel.addSelectionChangeHandler(event -> {
                    Set<Person> selected = selectionModel.getSelectedSet();
                    if (!selected.isEmpty()) {
                        deleteBtn.setEnabled(true);
                    } else {
                        deleteBtn.setEnabled(false);
                    }
                }
        );

        cellList.addDomHandler(event -> {
            Set<Person> selected = selectionModel.getSelectedSet();
            entityWindow.showEntity(selected.iterator().next());
        }, DoubleClickEvent.getType());

        btnPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        cellList.setHeight("500px");
        ScrollPanel scrollPanel = new ScrollPanel(cellList);

        listPanel.add(scrollPanel);
        horizontalPanel.add(entityWindow);
        listPanel.setWidth("100%");
        entityWindow.setWidth("100%");
        int width = Window.getClientWidth();
        horizontalPanel.setWidth(width+"px");
        entityWindow.addStyleName("mainPanel");
        horizontalPanel.setCellWidth(listPanel,width/2 + "px");
        horizontalPanel.setCellWidth(entityWindow,width/2 + "px");

        Window.addResizeHandler(event -> {
            int newWidth = event.getWidth();
            horizontalPanel.setWidth(newWidth + "px");
            horizontalPanel.setCellWidth(listPanel,newWidth/2 + "px");
            horizontalPanel.setCellWidth(entityWindow,newWidth/2 + "px");
        });
    }

    @UiHandler("deleteBtn")
    public void deleteBtnClick(ClickEvent event) {
        Set<Person> selected = selectionModel.getSelectedSet();
        for (Person person : selected) {
            dataProvider.getList().remove(person);
        }
        deleteBtn.setEnabled(false);
    }

    @UiHandler("addBtn")
    public void addBtnClick(ClickEvent event) {
        entityWindow.addEntity();
    }

    public List<Person> getData() {
        return data;
    }

    public ListDataProvider<Person> getDataProvider() {
        return dataProvider;
    }


}