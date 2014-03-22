package org.josescalia.common.domain

/**
 * Created by josescalia on 3/12/14.
 */
abstract class AuditableBase {

    def springSecurityService

    String createdBy;
    Date createdDate;
    String updatedBy;
    Date updatedDate;

    static constraints = {
        createdBy(nullable: true)
        createdDate(nullable: true)
        updatedBy(nullable: true)
        updatedDate(nullable: true)
    }

    static mapping = {
        createdBy(length: 50)
        updatedBy(length: 50)
    }

    private getUsername() {
        // get user using spring security
        return springSecurityService.getCurrentUser()?.username
    }

    def beforeInsert() {
        createdDate = new Date()
        if (springSecurityService.isLoggedIn() == false) {
            createdBy = "Init Data"
            updatedBy = "Init Data"
        } else {
            createdBy = getUsername()
            updatedBy = getUsername()
        }

    }

    def beforeUpdate() {
        updatedDate = new Date()
        updatedBy = getUsername()
    }
}
