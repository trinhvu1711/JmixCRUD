package com.company.jmixcrud.view.classroom;

import com.company.jmixcrud.entity.Classroom;
import com.company.jmixcrud.entity.Teacher;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


@Route(value = "classrooms", layout = MainView.class)
@ViewController(id = "Classroom.list")
@ViewDescriptor(path = "classroom-list-view.xml")
@LookupComponent("classroomsDataGrid")
@DialogMode(width = "64em")
public class ClassroomListView extends StandardListView<Classroom> {
    @ViewComponent
    private CollectionLoader<Classroom> classroomsDl;
    @ViewComponent
    private TypedTextField<Object> classroomCodeField;
    @ViewComponent
    private TypedTextField<Object> classroomNameField;
    @ViewComponent
    private EntityComboBox<Teacher> teacherField;

    protected void reload() {
        String query = "select e from Classroom e where 1 = 1";
        Map<String, Object> params = new HashMap<>();

        if (classroomNameField != null && !classroomNameField.getValue().isEmpty()) {
            query += " and (e.classroomName = :classroomNameField)";
            params.put("classroomNameField", classroomNameField.getValue());
        }

        if (classroomCodeField != null && !classroomCodeField.getValue().isEmpty()) {
            query += " and (e.classroomCode = :classroomCodeField)";
            params.put("classroomCodeField", classroomCodeField.getValue());
        }

        if (teacherField != null && teacherField.getValue() != null && teacherField.getValue().getId() != null) {
            query += " and (e.teacher.id = :teacherField)";
            params.put("teacherField", teacherField.getValue().getId());
        }

        // Sắp xếp kết quả theo `id`
        query += " order by e.id desc";

        // Đặt câu truy vấn và các tham số cho `DataLoader`
        classroomsDl.setQuery(query);
        classroomsDl.setParameters(params);
        classroomsDl.setFirstResult(0);
        classroomsDl.load();
    }

    @Subscribe("classroomsDataGrid.refresh")
    public void classroomsDataGridRefresh(final ActionPerformedEvent event) {
        reload();
    }

}