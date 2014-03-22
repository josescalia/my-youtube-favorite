package org.josescalia.youtube.tools

import grails.converters.JSON
import org.josescalia.youtube.transaction.YoutubeVideo

class VideoController {

    def springSecurityService

    def index() {
    }

    def newestYoutubeVideo() {
        def strQuery = "updatedBy='" + springSecurityService.currentUser.username + "' and available is true order by createdDate desc"
        def videoList = YoutubeVideo.executeQuery("from YoutubeVideo where " + strQuery)
        if (videoList.size() > 10) {
            render videoList.subList(0, 9) as JSON
        } else {
            render videoList as JSON
        }

    }

    def empty() {
        println params
        def text = params.text ?: "Choose the video you like to watch from the list by clicking the button besides video item"
        [text: text]
    }

    def loading() {
        redirect(action: "empty", params: [text: "<p class='text-center'><img src='${resource(dir:'images', file: "spinner.gif" )}'><br>Loading...</p> "])
    }

    def player(){
       def height = params.height?:390
       def width = params.width?:640
       [videoKey:params.vKey, width:width, height:height]
    }
}
