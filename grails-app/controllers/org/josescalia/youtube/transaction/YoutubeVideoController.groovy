package org.josescalia.youtube.transaction

import grails.converters.JSON
import org.josescalia.youtube.master.VideoCategory
import org.josescalia.utils.JsonMessage
import org.josescalia.utils.ViewUtil

class YoutubeVideoController {

    def springSecurityService

    def index() {
        redirect(action: "list")
    }

    /*standard crud*/
    /*FORM*/

    def list() {
        /*need to add empty model data here to fill up modal form */
        def modelData = new YoutubeVideo()
        [model: modelData, catList: VideoCategory.list()]
    }

    def edit(Long id) {
        def modelData = YoutubeVideo.get(id)
        [model: modelData, formTitle: "Youtube Video", catList: VideoCategory.list()]
    }

    /*---------------------------*/

    /*db action*/

    def dataTableList() {
        def propertiesToRender = ['id', 'videoTitle', 'videoKey', 'category', 'available','rate']
        def filters = ['videoTitle', 'videoKey', 'category.categoryName']
        def criteria = new HashMap<String, Object>()
        criteria.put("updatedBy", "'" + springSecurityService.currentUser.username + "'") //so the init data can be fetch by admin
        //print params
        render ViewUtil.dataTablesUtilSpecific(params, criteria, propertiesToRender, filters, YoutubeVideo.class) as JSON
        //render ViewUtil.dataTablesUtil(params, propertiesToRender, filters, YoutubeVideo.class) as JSON
    }

    def add() {
        def modelData = new YoutubeVideo()
        modelData.videoTitle = params.videoTitle
        modelData.videoKey = params.videoKey
        modelData.category = VideoCategory.get(params.categoryId)
        modelData.available = new Boolean(params.available)
        modelData.rate = new Integer(params.rate)
        if (!modelData.save(flush: true)) {
            render JsonMessage.showErrorMessage("Save Failed", "Unknown Error") as JSON
        }
        render JsonMessage.showInfoMessage("Save Succeed") as JSON
    }


    def update() {
        println params.rate
        def oldModel = YoutubeVideo.get(params.id)
        if (oldModel != null) {
            oldModel.videoTitle = params.videoTitle
            oldModel.videoKey = params.videoKey
            oldModel.category = VideoCategory.get(params.categoryId)
            oldModel.available = new Boolean(params.available)
            oldModel.rate = new Integer(params.rate)
            if (!oldModel.save(flush: true)) {
                render JsonMessage.showErrorMessage("Updated Failed", "Unknown Error") as JSON
            }
            render JsonMessage.showInfoMessage("Updated Succeed") as JSON
        }
    }

    def delete() {
        def model = YoutubeVideo.get(params.id)
        if (model != null) {
            try {
                model.delete()
                render JsonMessage.showInfoMessage("Delete Succeed") as JSON
            } catch (Exception e) {
                render JsonMessage.showErrorMessage("Delete Failed", e.getMessage()) as JSON
            }
        }
    }
    /*----------------------------*/

    /*referensi*/

    def dataTableAsRef() {

    }

    /*should be dialog*/
    def addFromList(){
        [model:params,catList: VideoCategory.list()]
    }
}
