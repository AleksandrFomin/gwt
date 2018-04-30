package com.taskGWT.client.Widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.taskGWT.client.Entities.Person;

import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntityWindow extends Composite implements IsWidget {
    interface EntityWindowUiBinder extends UiBinder<HTMLPanel, EntityWindow> {
    }

    private static EntityWindowUiBinder ourUiBinder = GWT.create(EntityWindowUiBinder.class);

    private ItemsList itemsList;
    private Boolean isChange;
    private Person person;
    private DateBox dateBox;
    Logger logger = Logger.getLogger("Logger");

    @UiField
    IntegerBox hoursBox;
    @UiField
    TextBox name;
    @UiField
    TextBox lastName;
    @UiField
    VerticalPanel birthDate;
    @UiField
    IntegerBox minutesBox;

    public EntityWindow(ItemsList itemsList) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.itemsList = itemsList;
        setVisible(false);
        init();
    }

    private void init() {
        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
        dateBox = new DateBox();
        dateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));
        birthDate.add(dateBox);
    }

    public void showEntity(Person person) {
        logger.log(Level.SEVERE,person.getTime().toString());
        logger.log(Level.SEVERE, String.valueOf(person.getTime().getHours()));
        isChange = true;
        setVisible(true);
        this.person = person;
        name.setText(person.getName());
        lastName.setText(person.getLastName());
        dateBox.setValue(person.getBirthDate());
        hoursBox.setText(String.valueOf(person.getTime().getHours()));
        minutesBox.setText(String.valueOf(person.getTime().getMinutes()));
    }

    public void addEntity() {
        isChange = false;
        setVisible(true);
        name.setText(null);
        lastName.setText(null);
    }

    @UiHandler("submit")
    public void updateClick(ClickEvent event) {
        if (isChange) {
            int index = itemsList.getData().indexOf(person);
            itemsList.getData().get(index).setName(name.getText());
            itemsList.getData().get(index).setLastName(lastName.getText());
            itemsList.getData().get(index).setBirthDate(dateBox.getValue());
            Time newTime = new Time(0);
            newTime.setHours(hoursBox.getValue());
            newTime.setMinutes(minutesBox.getValue());
            logger.log(Level.SEVERE,newTime.toString());
            itemsList.getData().get(index).setTime(newTime);
        } else {
            Time newTime = new Time(0);
            newTime.setHours(hoursBox.getValue());
            newTime.setMinutes(minutesBox.getValue());
            person = new Person(name.getText(), lastName.getText(),
                    dateBox.getValue(), newTime);
            itemsList.getDataProvider().getList().add(person);
        }
        itemsList.getDataProvider().refresh();
        person = null;
        setVisible(false);
    }
}