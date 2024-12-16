package com.company.jmixcrud.view.teacher;

import com.company.jmixcrud.entity.Teacher;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

import java.util.HashMap;
import java.util.Map;


@Route(value = "teachers", layout = MainView.class)
@ViewController(id = "Teacher.list")
@ViewDescriptor(path = "teacher-list-view.xml")
@LookupComponent("teachersDataGrid")
@DialogMode(width = "64em")
public class TeacherListView extends StandardListView<Teacher> {
    @ViewComponent
    private CollectionLoader<Teacher> teachersDl;
    @ViewComponent
    private TypedTextField<Object> addressField;
    @ViewComponent
    private TypedTextField<Object> ageField;
    @ViewComponent
    private TypedTextField<Object> nameField;
    @ViewComponent
    private TypedTextField<Object> phoneNumberField;

    protected void reload() {
        String query = "select e from Teacher e where 1 = 1";
        Map<String, Object> params = new HashMap<>();

        if (addressField != null && !addressField.getValue().isEmpty()) {
            query += " and (e.address = :addressField)";
            params.put("addressField", addressField.getValue());
        }

        if (ageField != null && !ageField.getValue().isEmpty()) {
            query += " and (e.age = :ageField)";
            params.put("ageField", Integer.valueOf(ageField.getValue()));
        }

        if (nameField != null && !nameField.getValue().isEmpty()) {
            query += " and (e.name = :nameField)";
            params.put("nameField", nameField.getValue());
        }

        if (phoneNumberField != null && !phoneNumberField.getValue().isEmpty()) {
            query += " and (e.phoneNumber = :phoneNumberField)";
            params.put("phoneNumberField", phoneNumberField.getValue());
        }

        // Sắp xếp kết quả theo `id`
        query += " order by e.id desc";

        // Đặt câu truy vấn và các tham số cho `DataLoaders`
        teachersDl.setQuery(query);
        teachersDl.setParameters(params);
        teachersDl.setFirstResult(0);
        teachersDl.load();
    }

    @Subscribe("teachersDataGrid.refresh")
    public void teachersDataGridRefresh(final ActionPerformedEvent event) {
        reload();
    }

}