package com.projectmanagement.saasovation.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectmanagement.saasovation.team.domain.BaseEntity;
import com.projectmanagement.saasovation.team.domain.Member;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "project")
public class Project extends BaseEntity <Long> {
    /*
     @ManyToOne(
          fetch = FetchType.LAZY,
          optional = false
  )
  @JoinColumn(
          name = "manager_id",
          nullable = false
  )
     */


    @Field(termVector = TermVector.YES)
    @Column(name = "pr_name", nullable = false, unique = false)
    private String projectName;

    @ManyToOne
    @JoinColumn(name="project_owner_id")
    private Member projectOwner;

    @Field(termVector = TermVector.YES)
    @Column(name = "pr_type", nullable = false, unique = false)
    private String projectType;
//
//    private Team team;
//
//    private Set <Task> tasks;

    public Project() {
        super();
    }

    public Project(String projectName, Member projectOwner, String projectType) {
        super();
        this.projectOwner = projectOwner;
        this.projectName = projectName;
        this.projectType = projectType;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Member getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(Member projectOwner) {
        this.projectOwner = projectOwner;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Override
    public boolean equals(Object toCompareWith) {
        boolean isEqual = false;
        if (toCompareWith != null && this.getClass() == toCompareWith.getClass()) {
            Project typedObject = (Project) toCompareWith;
            isEqual = this.getId().equals(typedObject.getId()) &&
                    this.getProjectOwner().equals(typedObject.getProjectOwner());
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCodeValue =
                +(71121 * 79)
                        + this.getId().hashCode()
                        + this.getProjectOwner().hashCode();
        return hashCodeValue;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectOwner=" + projectOwner.toString() +
                ", projectType='" + projectType + '\'' +
                '}';
    }
}
