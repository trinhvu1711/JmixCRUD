package com.company.jmixcrud.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "CLASSROOM", indexes = {
        @Index(name = "IDX_CLASSROOM_TEACHER", columnList = "TEACHER_ID")
})
@Entity
public class Classroom {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CLASSROOM_CODE", nullable = false)
    @NotNull
    private String classroomCode;

    @InstanceName
    @Column(name = "CLASSROOM_NAME", nullable = false)
    @NotNull
    private String classroomName;

    @JoinColumn(name = "TEACHER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}