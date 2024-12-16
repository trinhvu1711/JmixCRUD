package com.company.jmixcrud.security;

import com.company.jmixcrud.entity.Department;
import com.company.jmixcrud.entity.Step;
import com.company.jmixcrud.entity.User;
import com.company.jmixcrud.entity.UserStep;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "HR Manager", code = HRManagerRole.CODE)
public interface HRManagerRole {
    String CODE = "h-r-manager";

    @MenuPolicy(menuIds = "User.list")
    @ViewPolicy(viewIds = {"User.list", "User.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = UserStep.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = UserStep.class, actions = EntityPolicyAction.ALL)
    void userStep();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Step.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Step.class, actions = EntityPolicyAction.READ)
    void step();

    @EntityAttributePolicy(entityClass = Department.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Department.class, actions = EntityPolicyAction.READ)
    void department();
}