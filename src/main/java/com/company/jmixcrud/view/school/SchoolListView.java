package com.company.jmixcrud.view.school;

import com.company.jmixcrud.entity.School;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;

import java.util.HashMap;
import java.util.Map;


@Route(value = "schools", layout = MainView.class)
@ViewController(id = "School.list")
@ViewDescriptor(path = "school-list-view.xml")
@LookupComponent("schoolsDataGrid")
@DialogMode(width = "64em")
public class SchoolListView extends StandardListView<School> {
    @ViewComponent
    private TypedTextField<Object> nameField;
    @ViewComponent
    private CollectionLoader<School> schoolsDl;

    @Subscribe("schoolsDataGrid.refresh")
    public void classroomsDataGridRefresh(final ActionPerformedEvent event) {
        reload();
    }
    protected void reload() {
        String query = "select e from School e where 1 = 1";
        Map<String, Object> params = new HashMap<>();

        if (nameField != null && !nameField.getValue().isEmpty()) {
            query += " and (e.name = :nameField)";
            params.put("nameField", nameField.getValue());
        }

        // Sắp xếp kết quả theo `id`
        query += " order by e.id desc";

        // Đặt câu truy vấn và các tham số cho `DataLoader`
        schoolsDl.setQuery(query);
        schoolsDl.setParameters(params);
        schoolsDl.setFirstResult(0);
        schoolsDl.load();
    }
}