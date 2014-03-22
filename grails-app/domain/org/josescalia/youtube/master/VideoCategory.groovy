package org.josescalia.youtube.master

import org.josescalia.common.domain.AuditableBase

class VideoCategory extends AuditableBase{

    String categoryName
    String categoryDescription

    static constraints = {
        categoryName unique: true
        categoryDescription nullable: true
    }

    static mapping = {
        categoryName length:50
        categoryDescription length:50
    }


    @Override
    public String toString() {
        return "VideoCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", version=" + version +
                "} " + super.toString();
    }
}
