package org.josescalia.youtube.transaction

import org.josescalia.common.domain.AuditableBase
import org.josescalia.youtube.master.VideoCategory

class YoutubeVideo extends AuditableBase{
    String videoTitle
    String videoKey
    VideoCategory category
    Boolean available
    Integer rate  //number default 0 min=1 max=5 videoRating user style

    static constraints = {
        videoKey unique:true
        category nullable: true //null is uncategory
    }


    static mapping = {
        videoTitle length:50
        videoKey length:20
        rate length: 1
    }


    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "id=" + id +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoKey='" + videoKey + '\'' +
                ", category=" + category +
                ", available=" + available +
                ", rate=" + rate +
                ", version=" + version +
                "} " + super.toString();
    }
}
