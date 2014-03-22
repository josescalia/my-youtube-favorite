package org.josescalia.youtube.master

import grails.converters.JSON
import org.josescalia.utils.JsonMessage
import org.josescalia.utils.ViewUtil

class VideoCategoryController {

    def index() {
        redirect(action: 'list')
    }

    /*standard crud*/
    /*FORM*/

    def list() {
        /*need to add empty model data here to fill up modal form */
        def modelData = new VideoCategory()
        [model: modelData]
    }

    def edit(Long id) {
        def modelData = VideoCategory.get(id)
        [model: modelData, formTitle:"Video Category"]
    }

    /*---------------------------*/
    /*db action*/

    def dataTableList() {
        def propertiesToRender = ['id', 'categoryName', 'categoryDescription']
        def filters = ['categoryName', 'categoryDescription']
        //print params
        render ViewUtil.dataTablesUtil(params, propertiesToRender, filters, VideoCategory.class) as JSON
    }

    def add() {
        def modelData = new VideoCategory(params)
        if (!modelData.save(flush: true)) {
            render JsonMessage.showErrorMessage("Save Failed", "Unknown Error") as JSON
        }
        render JsonMessage.showInfoMessage("Save Succeed") as JSON
    }


    def update() {
        def oldModel = VideoCategory.get(params.id)
        if (oldModel != null) {
            oldModel.categoryName = params.categoryName
            oldModel.categoryDescription = params.categoryDescription
            if (!oldModel.save(flush: true)) {
                render JsonMessage.showErrorMessage("Updated Failed", "Unknown Error") as JSON
            }
            render JsonMessage.showInfoMessage("Updated Succeed") as JSON
        }
    }

    def delete() {
        def model = VideoCategory.get(params.id)
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
}
