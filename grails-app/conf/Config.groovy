// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.resources.debug = true
        grails.resources.processing.enabled = false
        grails.resources.adhoc.excludes = ['**/*.*']
        // cached-resources
        grails.resources.mappers.hashandcache.excludes = ['**/*.*']
        // resource bundling
        grails.resources.mappers.bundle.excludes = ['**/*.*']
        // zipped-resources
        grails.resources.mappers.zip.excludes = ['**/*.*']
    }
    production {
        grails.logging.jul.usebridge = true
        grails.resources.debug = true
        grails.resources.processing.enabled = false
        grails.resources.adhoc.excludes = ['**/*.*']
        // cached-resources
        grails.resources.mappers.hashandcache.excludes = ['**/*.*']
        // resource bundling
        grails.resources.mappers.bundle.excludes = ['**/*.*']
        // zipped-resources
        grails.resources.mappers.zip.excludes = ['**/*.*']
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.josescalia.youtube.sec.AppUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.josescalia.youtube.sec.AppUserAppRole'
grails.plugin.springsecurity.authority.className = 'org.josescalia.youtube.sec.AppRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	'/**/favicon.ico':                ['permitAll']
]


//intercept url
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
        '/youtubeVideo/*':                  ['ROLE_ADMIN', 'ROLE_USER'],
        '/video/*':                         ['ROLE_ADMIN', 'ROLE_USER'],
        '/youtubeAPI/*':                    ['ROLE_ADMIN', 'ROLE_USER'],
        '/summary/*':                       ['ROLE_ADMIN', 'ROLE_USER'],


        //admin and non authenticated session
        '/videoCategory/*':                 ['ROLE_ADMIN'],
        '/appRole/*':                       ['ROLE_ADMIN'],
        '/appUser/*':                       ['ROLE_ADMIN'],
        'js/**':                            ['IS_AUTHENTICATED_ANONYMOUSLY'],
        'css/**':                           ['IS_AUTHENTICATED_ANONYMOUSLY'],
        'images/**':                        ['IS_AUTHENTICATED_ANONYMOUSLY'],
        'fonts/**':                         ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/**':                              ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '':                                 ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/login/**':                        ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/logout/**':                       ['IS_AUTHENTICATED_ANONYMOUSLY']
]
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.auth.loginFormUrl = '/'

//CUSTOM PROPERTIES
application.name = "My Youtube Favorite"
application.version = "1.0"

//menu header Left and right
menuRightHeaderList = ["Master","Transaction","Summary","System"]
menuLeftHeaderList = ["Tools":"Tools","MyPage":"My Page"]

//menuItemList by category
//contains menuName:menuLink
menuMasterItemList = ["Video Category": "videoCategory/list"]
menuTransactionItemList = ["Youtube Video": "youtubeVideo/list"]
menuSystemItemList = ["App Role": "appRole/list","App User":"appUser/list"]
menuSummaryItemList = ["Video Summary":"summary/videoYoutube"]
menuToolsItemList = ["Watch Youtube Video":"video/","Search Youtube":"youtubeAPI/"]
menuMyPageItemList = ["My Youtube Favorite":"youtubeFavorite/"]

