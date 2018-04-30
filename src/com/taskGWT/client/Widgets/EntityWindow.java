package com.taskGWT.client.Widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.taskGWT.client.Entities.Person;

public class EntityWindow extends Composite implements IsWidget {
    interface EntityWindowUiBinder extends UiBinder<HTMLPanel, EntityWindow> {
    }

    private static EntityWindowUiBinder ourUiBinder = GWT.create(EntityWindowUiBinder.class);

    private ItemsList itemsList;

    private Person person;

    @UiField
    TextBox name;
    @UiField
    TextBox lastName;

    public EntityWindow(ItemsList itemsList) {
        initWidget(ourUiBinder.createAndBindUi(this));
        setVisible(false);
        this.itemsList = itemsList;
    }

    public void showEntity(Person person){
        setVisible(true);
        this.person = person;
        name.setText(person.getName());
        lastName.setText(person.getLastName());
    }

    public void addEntity(){
        setVisible(true);
        name.setText(null);
        lastName.setText(null);
    }

    @UiHandler("submit")
    public void updateClick(ClickEvent event) {
        int index = itemsList.getData().indexOf(person);
        itemsList.getData().get(index).setName(name.getText());
        itemsList.getData().get(index).setLastName(lastName.getText());
        itemsList.getDataProvider().refresh();
        setVisible(false);
    }
}