package org.josescalia.youtube.pages

import grails.converters.JSON
import org.josescalia.youtube.transaction.YoutubeVideo

class YoutubeFavoriteController {

    def index() {
       redirect(action: "thumbnailList")
    }

    def thumbnailList(Integer max){
        params.max = Math.min(max?:10,100)
        [thumbnailList:YoutubeVideo.list(params), total:YoutubeVideo.count()]
    }

}
