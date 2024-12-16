package com.company.jmixcrud.view.student;

import com.company.jmixcrud.entity.Classroom;
import com.company.jmixcrud.entity.Student;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.datepicker.TypedDatePicker;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

import java.util.HashMap;
import java.util.Map;


@Route(value = "students", layout = MainView.class)
@ViewController(id = "Student.list")
@ViewDescriptor(path = "student-list-view.xml")
@LookupComponent("studentsDataGrid")
@DialogMode(width = "64em")
public class StudentListView extends StandardListView<Student> {
    @ViewComponent
    private TypedTextField<Object> addressField;
    @ViewComponent
    private TypedTextField<Object> citizenIdField;
    @ViewComponent
    private EntityComboBox<Classroom> classroomField;
    @ViewComponent
    private TypedTextField<Object> nameField;
    @ViewComponent
    private TypedTextField<Object> phoneNumberField;
    @ViewComponent
    private TypedTextField<Object> studentCodeField;
    @ViewComponent
    private TypedDatePicker<Comparable> birthDateField;
    @ViewComponent
    private CollectionLoader<Student> studentsDl;

    protected void reload() {
        String query = "select e from Student e where 1 = 1";
        Map<String, Object> params = new HashMap<>();

        if (addressField != null && !addressField.getValue().isEmpty()) {
            query += " and (e.address = :addressField)";
            params.put("addressField", addressField.getValue());
        }

        if (citizenIdField != null && !citizenIdField.getValue().isEmpty()) {
            query += " and (e.citizenId = :citizenIdField)";
            params.put("citizenIdField", citizenIdField.getValue());
        }

        if (classroomField != null && classroomField.getValue() != null && classroomField.getValue().getId() != null) {
            query += " and (e.classroom.id = :classroomField)";
            params.put("classroomField", classroomField.getValue().getId());
        }

        if (nameField != null && !nameField.getValue().isEmpty()) {
            query += " and (e.name = :nameField)";
            params.put("nameField", nameField.getValue());
        }

        if (phoneNumberField != null && !phoneNumberField.getValue().isEmpty()) {
            query += " and (e.phoneNumber = :phoneNumberField)";
            params.put("phoneNumberField", phoneNumberField.getValue());
        }

        if (studentCodeField != null && !studentCodeField.getValue().isEmpty()) {
            query += " and (e.studentCode = :studentCodeField)";
            params.put("studentCodeField", studentCodeField.getValue());
        }

        if (!birthDateField.isEmpty()) {
            query += " and e.birthDate = :birthDateField";
            params.put("birthDateField", birthDateField.getValue());
        }
        // Sắp xếp kết quả theo `id`
        query += " order by e.id desc";

        // Đặt câu truy vấn và các tham số cho `DataLoader`
        studentsDl.setQuery(query);
        studentsDl.setParameters(params);
        studentsDl.setFirstResult(0);
        studentsDl.load();
    }

    @Subscribe("studentsDataGrid.refresh")
    public void studentsDataGridRefresh(final ActionPerformedEvent event) {
        reload();
    }

}