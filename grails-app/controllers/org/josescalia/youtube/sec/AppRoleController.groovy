package org.josescalia.youtube.sec

import grails.converters.JSON
import org.josescalia.utils.JsonMessage
import org.josescalia.utils.ViewUtil

class AppRoleController {

    def index() {
        redirect(action: 'list')
    }

    /*standard crud*/
    /*FORM*/

    def list() {
        /*need to add empty model data here to fill up modal form */
        def modelData = new AppRole()
        [model: modelData]
    }

    def edit(Long id) {
        def modelData = AppRole.get(id)
        [model: modelData, formTitle: "Application Role"]
    }

    /*---------------------------*/
    /*db action*/

    def dataTableList() {
        def propertiesToRender = ['id', 'authority']
        def filters = ['authority']
        //print params
        render ViewUtil.dataTablesUtil(params, propertiesToRender, filters, AppRole.class) as JSON
    }

    def add() {
        def modelData = new AppRole(params)
        if (!modelData.save(flush: true)) {
            render JsonMessage.showErrorMessage("Save Failed", "Unknown Error") as JSON
        }
        render JsonMessage.showInfoMessage("Save Succeed") as JSON
    }


    def update() {
        def oldModel = AppRole.get(params.id)
        if (oldModel != null) {
            oldModel.authority = params.authority
            if (!oldModel.save(flush: true)) {
                render JsonMessage.showErrorMessage("Updated Failed", "Unknown Error") as JSON
            }
            render JsonMessage.showInfoMessage("Updated Succeed") as JSON
        }
    }

    def delete() {
        def model = AppRole.get(params.id)
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
