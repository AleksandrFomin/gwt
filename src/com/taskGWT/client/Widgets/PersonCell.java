package com.taskGWT.client.Widgets;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.taskGWT.client.Entities.Person;

public class PersonCell extends AbstractCell<Person> {
    @Override
    public void render(Context context, Person value, SafeHtmlBuilder sb) {
        if (value != null) {
            sb.appendHtmlConstant("<table><tr><td>");
            sb.appendEscaped(value.getLastName());
            sb.appendHtmlConstant("</td></tr><tr><td>");
            sb.appendEscaped(value.getName());
            sb.appendHtmlConstant("</td></tr></table>");
        }
    }
}
