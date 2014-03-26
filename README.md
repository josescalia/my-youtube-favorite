#My Youtube Favorite 1.0
Website : [http://josescalia.net](http://josescalia.net)

##Description
This is the Youtube Favorite List Application, this application created using [Grails Framework](http://grails.org). The idea of this web application is to create a local playlist of youtube videos. So a user can easily add the youtube video to their collection.This application also need [Google Youtube API](https://developers.google.com/youtube/) key, to search the video using [Youtube Data API V3](https://developers.google.com/youtube/v3/). And the youtube video will be playing using [Youtube Player Iframe API](https://developers.google.com/youtube/iframe_api_reference).
####Css and Javascript framework used in this application:
1. [Bootstrap](http://getbootstrap.com) (Most Famous CSS Framework)
2. [Bootbox Js](http://bootboxjs.com) (Bootstrap Plugin)
3. [DataTables](http://www.datatables.net) (Integrated with Bootstrap to create tabular data)
4. [RGraph](http://www.rgraph.net) (Used to create chart for summary)
5. [Raty](http://wbotelhos.com/raty/) (Used to handle the video rating)

####Grails Plugin used in this application:
1. [Spring Security Core](http://grails.org/plugins/spring-security-core/) plugin</a>

####The Screen Shot
I create a screen shot of this web application and a little description of each pages, can check out the screen shot [here](https://picasaweb.google.com/108778507341023372805/MyYoutubeFavoriteV10)

##Configuring The Projects
The development should be set up using **Grails Framework**. This application also need **MySQL database**, but still can be configured using any kind of database specified by Grails Framework guide lines. Just edit File **DataSource.groovy** in **grails-app/conf/** folder to use with your own specified database.

This application also need Google Youtube API key to use the Youtube Data API. Please grab your own API Key, and put it down the API key in the head section of **YoutubeAPIController.groovy** file at **grails-app/controllers/org/josescalia/youtube/tools/** folder


##Road Map
* Need to create Security UI to allow **admin** of the application to _create_, _edit_, or _delete_ **user**, cannot use the Grails Spring Security UI, cause it will get conflict with the Bootstrap and Bootboxjs, especially in alert and modal form



Regards
######Josescalia