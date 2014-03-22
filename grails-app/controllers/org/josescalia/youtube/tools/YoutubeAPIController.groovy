package org.josescalia.youtube.tools

class YoutubeAPIController {
    def browserkey = "<Google Youtube API Key>"


    def index() {
        redirect(action: 'topList')
    }

    def topList(){
        [browserKey: browserkey]
    }
}
