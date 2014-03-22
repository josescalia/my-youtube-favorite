package org.josescalia.youtube.system

class HomeController {

    def springSecurityService



    def mnItem = new HashMap<String, String>()

    def menuTopRight = new ArrayList<Map<String, List>>()
    def menuTopLeft = new ArrayList<Map<String, List>>()
    def menuAdmin = new ArrayList<Map<String, List>>()
    def menuUser = new ArrayList<Map<String, List>>()

    def mapMenuHeader

    def index() {}


    /**
     * New Menu Constructor more simple
     * To Use this menuConstructor just create an array containing each menu header and menu category in Config.groovy file
     * */
    def constructMenu() {

        //menuRightTop
        def menuRightHeaderList = grailsApplication.config.menuRightHeaderList
        menuRightHeaderList.each {
            def menuRightHeader = new ArrayList<Map<String, String>>()
            def menuItemList = grailsApplication.config.get("menu" + it + "ItemList")
            menuItemList.each { menuName, menuLink ->
                mnItem = new LinkedHashMap<String, String>();
                mnItem.put("menuLink", request.contextPath + "/" + menuLink)
                mnItem.put("menuName", menuName)
                menuRightHeader.add(mnItem)

            }
            mapMenuHeader = new HashMap<String,List>();
            mapMenuHeader.put(it.toString(),menuRightHeader)
            menuAdmin.add(mapMenuHeader)   //add all to admin Role
            if(it.toString().equalsIgnoreCase("Transaction") || it.toString().equalsIgnoreCase("Summary") ){
                menuUser.add(mapMenuHeader);//add Transaction and summary only to user Role
            }
        }

        //menuTopLeft
        def menuLeftHeaderList = grailsApplication.config.menuLeftHeaderList
        menuLeftHeaderList.each{ headerMenuIndex, headerMenuName ->
            def menuLeftHeader = new ArrayList<Map<String, String>>()
            def menuItemList = grailsApplication.config.get("menu" + headerMenuIndex + "ItemList")
            menuItemList.each { menuName, menuLink ->
                mnItem = new LinkedHashMap<String, String>();
                mnItem.put("menuLink", request.contextPath + "/" + menuLink)
                mnItem.put("menuName", menuName)
                menuLeftHeader.add(mnItem)

            }
            mapMenuHeader = new HashMap<String,List>();
            mapMenuHeader.put(headerMenuName.toString(),menuLeftHeader)
            menuTopLeft.add(mapMenuHeader) //add all menu to regular user
        }
    }

    /**
     * Display topRightMenu
     * */
    def menuTop() {
        constructMenu()
        if (springSecurityService.isLoggedIn()) {
            if ((springSecurityService.principal.authorities.toString()).equalsIgnoreCase("[ROLE_ADMIN]")) {
                [dataList: menuAdmin]
            } else {
                [dataList: menuUser]
            }

        }
    }


    /**
     * Display menuTopLeft
     * */
    def menuTopLeft() {
        constructMenu()
        [dataList: menuTopLeft]  //user generic menu
    }

    /**
     * @deprecated : this method is deprecated, use constructMenu() instead
     * */
    def initMenu() {
        /*mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Rss Category")
        mnItem.put("menuLink", request.contextPath + "/rssCategory/list")
        mnMaster.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Video Category")
        mnItem.put("menuLink", request.contextPath + "/videoCategory/list")
        mnMaster.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "To Do")
        mnItem.put("menuLink", request.contextPath + "/toDoItem/list")
        mnTransaction.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Rss Feeder")
        mnItem.put("menuLink", request.contextPath + "/rssFeeder/list")
        mnTransaction.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Youtube Video")
        mnItem.put("menuLink", request.contextPath + "/youtubeVideo/list")
        mnTransaction.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "To Do Summary")
        mnItem.put("menuLink", request.contextPath + "/summary/toDo")
        mnSummary.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Rss Summary")
        mnItem.put("menuLink", request.contextPath + "/summary/rssFeeder")
        mnSummary.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Video Summary")
        mnItem.put("menuLink", request.contextPath + "/summary/videoYoutube")
        mnSummary.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "User Role")
        mnItem.put("menuLink", request.contextPath + "/appRole/list")
        mnSystem.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "User Account")
        mnItem.put("menuLink", request.contextPath + "/appUser/list")
        mnSystem.add(mnItem)

        menuTopRight.addAll(['Master': mnMaster], ["Transaction": mnTransaction], ["Summary": mnSummary], ["System": mnSystem])

        *//*menuLeft*//*
        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Rss Reader")
        mnItem.put("menuLink", request.contextPath + "/rssReaderTools/index")
        mnTools.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Youtube Fav List")
        mnItem.put("menuLink", request.contextPath + "/video/index")
        mnTools.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Unresolved To Do List")
        mnItem.put("menuLink", request.contextPath + "/myPage/unresolvedToDo")
        mnMyPage.add(mnItem)

        mnItem = new HashMap<String, String>()
        mnItem.put("menuName", "Resolved To Do List")
        mnItem.put("menuLink", request.contextPath + "/myPage/resolvedToDo")
        mnMyPage.add(mnItem)

        menuTopLeft.addAll(['Tools': mnTools], ["My Page": mnMyPage])

        //define menu by Role here
        //def menuRightFromConfig = grailsApplication.config.menuHeader

        menuAdmin.addAll(['Master': mnMaster], ["Transaction": mnTransaction], ["Summary": mnSummary], ["System": mnSystem])
        menuUser.addAll(["Transaction": mnTransaction], ["Summary": mnSummary])
*/

    }


}
