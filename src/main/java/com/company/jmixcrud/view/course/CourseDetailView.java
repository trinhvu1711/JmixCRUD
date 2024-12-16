package com.company.jmixcrud.view.course;

import com.company.jmixcrud.entity.Course;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "courses/:id", layout = MainView.class)
@ViewController(id = "Course.detail")
@ViewDescriptor(path = "course-detail-view.xml")
@EditedEntityContainer("courseDc")
public class CourseDetailView extends StandardDetailView<Course> {
}