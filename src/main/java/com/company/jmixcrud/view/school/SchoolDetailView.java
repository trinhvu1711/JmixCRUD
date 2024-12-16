package com.company.jmixcrud.view.school;

import com.company.jmixcrud.entity.School;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "schools/:id", layout = MainView.class)
@ViewController(id = "School.detail")
@ViewDescriptor(path = "school-detail-view.xml")
@EditedEntityContainer("schoolDc")
public class SchoolDetailView extends StandardDetailView<School> {
}