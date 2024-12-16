package com.company.jmixcrud.security;

import com.company.jmixcrud.entity.Step;
import com.company.jmixcrud.entity.User;
import com.company.jmixcrud.entity.UserStep;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Employee", code = EmployeeRole.CODE, scope = "UI")
public interface EmployeeRole {
    String CODE = "employee";

    @EntityPolicy(entityClass = UserStep.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    @EntityAttributePolicy(entityClass = UserStep.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void userStep();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void user();

    @EntityAttributePolicy(entityClass = Step.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Step.class, actions = EntityPolicyAction.READ)
    void step();
}