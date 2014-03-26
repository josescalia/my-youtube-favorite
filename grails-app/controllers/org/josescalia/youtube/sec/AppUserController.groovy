package org.josescalia.youtube.sec

class AppUserController {

    def index() {
        redirect(action: "list")
    }

    def list(){
        [text:"For some reason this feature has disabled :P"]
    }
}
