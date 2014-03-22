package org.josescalia.utils

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 9/27/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
class ViewUtil {

    /**
     * This method is created to shorten the logic of fetching data from domain
     * and pass the data's as specific DataTables Object format<br>
     * In Grails Application, this object have to be rendered as JSON
     * @param params GrailsParameterMap from grails views
     * @param propToRender ArrayList of properties or fields to display
     * @param clazz a GrailsDomainClass
     * @return LinkedHashMap HashMap
     */
    static dataTablesUtil(GrailsParameterMap params,
                          ArrayList<String> propToRender,
                          ArrayList<String> filterToRender,
                          Class clazz) {
        //println params
        def classToProcess = clazz.newInstance()
        def propertiesToRender = propToRender
        def dataToRender = [:]

        //println "Class => " + classToProcess
        // println "Properties => " + propertiesToRender

        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []                // Array of people.

        def filters = []
        for (String filterBy : filterToRender) {
            filters << "o." + filterBy + " like :filter" //will concatenated string filter
        }

        // println "filters => " + filters

        def filter = filters.join(" OR ")
        def query = new StringBuilder("from " + classToProcess.class.name + " as o")
        if (params.sSearch) {
            query.append(" where (${filter})")
        }

        dataToRender.iTotalRecords = classToProcess.count;
        //dataToRender.iTotalRecords = Country.count;
        def sortProperty = propertiesToRender[params.iSortCol_0 as int]
        def sortDir = params.sSortDir_0?.equals('asc') ? 'asc' : 'desc'
        query.append(" order by o.${sortProperty} ${sortDir}")

        // Execute the query
        def dataList = []
        if (params.sSearch) {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from " + classToProcess.class.name + "  as o where (${filter})")
            def result = classToProcess.executeQuery(countQuery.toString(),
                    [filter: "%${params.sSearch}%"])
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            dataList = classToProcess.findAll(query.toString(),
                    [filter: "%${params.sSearch}%"],
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        } else {
            dataToRender.iTotalDisplayRecords = classToProcess.count
            //dataToRender.iTotalDisplayRecords = 300
            dataList = classToProcess.findAll(query.toString(),
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }

        // Process the response
        dataList?.each { data ->
            def record = []
            propertiesToRender.each {
                record << data."${it}"
            }
            record << " " //to set action button delete or edit here

            dataToRender.aaData << record
        }
        return dataToRender;
    }

    static dataTablesDTO(String sEcho,
                         List dtoList,
                         ArrayList<String> propToRender,
                         Class clazz) {

        def dataToRender = [:]

        def dataList = []
        def propertiesToRender = propToRender


        dataToRender.sEcho = sEcho
        dataToRender.aaData = dtoList
        dataToRender.iTotalDisplayRecords = dtoList.size()
        dataToRender.iTotalRecords = dtoList.size()

        // Process the response
        dataList?.each { data ->
            def record = []
            propertiesToRender.each {
                record << data."${it}"
            }
            record << " " //to set action button delete or edit here

            dataToRender.aaData << record
        }

        return dataToRender

    }

    /**
     * @deprecated : I don't know whats wrong with this method but it always said
     * 'Not All named paramaters has beed set'<br>Just use dataTables spesific instead
     * */
    static dataTablesUtilFiltered(GrailsParameterMap params,
                                  ArrayList<String> propToRender,
                                  Map<String, Object> criteria,
                                  ArrayList<String> filterToRender,
                                  Class clazz) {
        //println params
        def classToProcess = clazz.newInstance()
        def propertiesToRender = propToRender
        def dataToRender = [:]

        //println "Class => " + classToProcess
        // println "Properties => " + propertiesToRender

        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []                // Array of people.

        def filters = []
        for (String filterBy : filterToRender) {
            filters << "o." + filterBy + " like :filter" //will concatenated string filter
        }

        // println "filters => " + filters

        def filter = filters.join(" OR ")
        def query = new StringBuilder("from " + classToProcess.class.name + " as o")
        String addOnQuery = "";

        if (criteria != null) {
            ArrayList<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(criteria.entrySet());
            for (Map.Entry mapEntry : list) {
                if (addOnQuery.length() > 0) {
                    addOnQuery += " AND "
                }
                addOnQuery += mapEntry.key + "=" + mapEntry.value

            }

            query.append(" WHERE " + addOnQuery)

        }

        //println "filter ==> " + filter
        if (params.sSearch) {
            query.append(" AND (${filter})")
        }

        //def queryCount = classToProcess.executeQuery(query.toString()).size()
        //dataToRender.iTotalRecords = queryCount

        dataToRender.iTotalRecords = classToProcess.findAll(query.toString()).size;
        def sortProperty = propertiesToRender[params.iSortCol_0 as int]
        def sortDir = params.sSortDir_0?.equals('asc') ? 'asc' : 'desc'
        query.append(" order by o.${sortProperty} ${sortDir}")

        // Execute the query
        def dataList = []
        if (params.sSearch) {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from " + classToProcess.class.name + "  as o")
            countQuery.append(" WHERE (${addOnQuery}) AND (${filter})")
            def result = classToProcess.executeQuery(countQuery.toString(),
                    [filter: "%${params.sSearch}%"])
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            dataList = classToProcess.findAll(query.toString(),
                    [filter: "%${params.sSearch}%"],
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        } else {
            dataToRender.iTotalDisplayRecords = classToProcess.executeQuery(query.toString()).size
            //dataToRender.iTotalDisplayRecords = 300
            dataList = classToProcess.findAll(query.toString(),
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }

        // Process the response
        dataList?.each { data ->
            def record = []
            propertiesToRender.each {
                record << data."${it}"
            }
            record << " " //to set action button delete or edit here

            dataToRender.aaData << record
        }
        return dataToRender;
    }

    /**
     * Method to fetch filtered data tables
     * Before the data fetch the data will be filtered 1st
     * @param params GrailsParameterMap from grails views
     * @param propToRender ArrayList of properties or fields to display
     * @param filterToRender a filter query from datatables ui
     * @param clazz a GrailsDomainClass
     * @return LinkedHashMap HashMap
     */
    static dataTablesUtilSpecific(GrailsParameterMap params,
                                  Map<String, Object> criteria,
                                  ArrayList<String> propToRender,
                                  ArrayList<String> filterToRender,
                                  Class clazz) {
        //println params
        def classToProcess = clazz.newInstance()
        def propertiesToRender = propToRender
        def dataToRender = [:]

        //println "Class => " + classToProcess
        //println "Properties => " + propertiesToRender

        dataToRender.sEcho = params.sEcho
        dataToRender.aaData = []                // Array of people.

        def filters = []
        for (String filterBy : filterToRender) {
            filters << "o." + filterBy + " like :filter" //will concatenated string filter
        }

        //println "filters => " + filters

        def filter = filters.join(" OR ")
        def query = new StringBuilder("from " + classToProcess.class.name + " as o")

        ArrayList<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(criteria.entrySet());
        String addOnQuery = "";
        for (Map.Entry mapEntry : list) {
            if (addOnQuery.length() > 0) {
                addOnQuery += " AND "
            }
            if (mapEntry.value != null) {
                addOnQuery += mapEntry.key + "=" + mapEntry.value
            } else {
                addOnQuery += mapEntry.key + "!=" + mapEntry.value
            }

        }

        query.append(" WHERE " + addOnQuery)
        //println "====> query : " + query

        //query to count
        def queryCount = classToProcess.executeQuery(query.toString()).size()
        //println "QUERY COUNT : " + queryCount

        if (params.sSearch) {
            query.append(" AND (${filter})")
        }

        dataToRender.iTotalRecords = queryCount
        //println "QUERY COUNT : " + dataToRender.iTotalRecords
        // dataToRender.iTotalRecords = queryCount
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords

        def sortProperty = propertiesToRender[params.iSortCol_0 as int]
        def sortDir = params.sSortDir_0?.equals('asc') ? 'asc' : 'desc'
        query.append(" order by o.${sortProperty} ${sortDir}")

        // Execute the query
        def dataList = []

        if (params.sSearch) {
            // Revise the number of total display records after applying the filter
            def countQuery = new StringBuilder("select count(*) from " + classToProcess.class.name + "  as o")
            countQuery.append(" WHERE (${addOnQuery}) AND (${filter})")
            def result = classToProcess.executeQuery(countQuery.toString(),
                    [filter: "%${params.sSearch}%"])
            if (result) {
                dataToRender.iTotalDisplayRecords = result[0]
            }
            dataList = classToProcess.findAll(query.toString(),
                    [filter: "%${params.sSearch}%"],
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        } else {
            dataList = classToProcess.findAll(query.toString(),
                    [max: params.iDisplayLength as int, offset: params.iDisplayStart as int])
        }

        // Process the response
        dataList?.each { data ->
            def record = []
            propertiesToRender.each {
                record << data."${it}"
            }
            record << " " //to set action button delete or edit here

            dataToRender.aaData << record
        }
        return dataToRender;
    }
}
