package com.company.jmixcrud.view.student;

import com.company.jmixcrud.entity.Course;
import com.company.jmixcrud.entity.Student;
import com.company.jmixcrud.view.course.CourseDetailView;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "students/:id", layout = MainView.class)
@ViewController(id = "Student.detail")
@ViewDescriptor(path = "student-detail-view.xml")
@EditedEntityContainer("studentDc")
public class StudentDetailView extends StandardDetailView<Student> {
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private CollectionPropertyContainer<Course> coursesDc;

    @Subscribe("coursesDataGrid.create")
    public void onCreateCourseBtnClick(final ActionPerformedEvent event) {
        DialogWindow<CourseDetailView> window = dialogWindows.detail(this, Course.class)
                .withViewClass(CourseDetailView.class)
                .newEntity()
                .build();

        window.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.SAVE)) {
                Course newCourse = afterCloseEvent.getView().getEditedEntity();
                coursesDc.getMutableItems().add(newCourse);
            }

        });
        window.open();
    }

    @Subscribe("coursesDataGrid.edit")
    public void onEditCourseBtnClick(final ActionPerformedEvent event) {
        DialogWindow<CourseDetailView> window = dialogWindows.detail(this, Course.class)
                .withViewClass(CourseDetailView.class)
                .editEntity(coursesDc.getItem())
                .build();

        window.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.SAVE)) {
                // Get the updated entity from the dialog
                Course updatedCourse = afterCloseEvent.getView().getEditedEntity();

                // Replace or update the entity in the CollectionPropertyContainer
                List<Course> items = coursesDc.getMutableItems();
                boolean isUpdated = items.stream()
                        .filter(course -> course.getId().equals(updatedCourse.getId()))
                        .findFirst()
                        .map(existingCourse -> {
                            coursesDc.getMutableItems().remove(existingCourse);
                            existingCourse.setName(updatedCourse.getName());
                            existingCourse.setScore(updatedCourse.getScore());
                            existingCourse.setSortValue(updatedCourse.getSortValue());
                            coursesDc.getMutableItems().add(existingCourse);
                            return true;
                        })
                        .orElse(false);

                if (!isUpdated) {
                    items.add(updatedCourse);
                }
            }
        });
        window.open();
    }
}