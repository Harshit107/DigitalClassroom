package com.harshit.digitalclassroom.model;

public class ClassList {

    String className;
    String teacherName;
    String classDescription;
    String profilePic;
    String backgroundImage;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public ClassList() {

    }

    public ClassList(String className, String teacherName, String classDescription, String profilePic, String backgroundImage) {
        this.className = className;
        this.teacherName = teacherName;
        this.classDescription = classDescription;
        this.profilePic = profilePic;
        this.backgroundImage = backgroundImage;
    }
}
