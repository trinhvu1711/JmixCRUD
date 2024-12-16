package com.company.jmixcrud.view.step;

import com.company.jmixcrud.entity.Step;
import com.company.jmixcrud.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "steps", layout = MainView.class)
@ViewController(id = "Step.list")
@ViewDescriptor(path = "step-list-view.xml")
@LookupComponent("stepsDataGrid")
@DialogMode(width = "64em")
public class StepListView extends StandardListView<Step> {
}