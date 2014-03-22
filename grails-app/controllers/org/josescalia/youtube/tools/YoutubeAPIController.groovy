package org.josescalia.youtube.tools

class YoutubeAPIController {
    def browserkey = "AIzaSyDyVOdWT7qGvH6IC7k0c_UcavkVW0xsBRE"

    def index() {
        redirect(action: 'topList')
    }

    def topList(){
        [browserKey: browserkey]
    }
}
