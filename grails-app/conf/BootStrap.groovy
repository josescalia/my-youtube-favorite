import junit.framework.Assert
import org.josescalia.youtube.master.VideoCategory
import org.josescalia.youtube.sec.AppRole
import org.josescalia.youtube.sec.AppUser
import org.josescalia.youtube.sec.AppUserAppRole
import org.josescalia.youtube.transaction.YoutubeVideo

class BootStrap {

    def init = { servletContext ->

        //init user
        if (AppRole.count == 0) {
            def roleAdmin = new AppRole(authority: "ROLE_ADMIN").save(failOnError: true)
            def roleUser = new AppRole(authority: "ROLE_USER").save(failOnError: true)

            if (AppRole.count > 0) {
                def userAdmin = new AppUser(username: "admin", password: "admin123", enabled: true, accountLocked: false, accountExpired: false, passwordExpired: false).save(failOnError: true)
                def userMojo = new AppUser(username: "mojo", password: "mojo123", enabled: true, accountLocked: false, accountExpired: false, passwordExpired: false).save(failOnError: true)

                if (AppUser.count > 0) {
                    AppUserAppRole.create userAdmin, roleAdmin, true
                    AppUserAppRole.create userMojo, roleUser, true
                }

                assert AppUser.count() == 2
                assert AppRole.count() == 2
                assert AppUserAppRole.count() == 2
            }

            //initial data
            if (VideoCategory.count == 0) {
                def categoryMusic = new VideoCategory(categoryName: "MUSIC", createdBy: "Initial data", updatedBy: "Initial data").save(failOnError: true)
                def categorySports = new VideoCategory(categoryName: "SPORTS", createdBy: "Initial data", updatedBy: "Initial data").save(failOnError: true)
                def categoryMovieTrailer = new VideoCategory(categoryName: "TRAILER", createdBy: "Initial data", updatedBy: "Initial data").save(failOnError: true)
                def categoryNews = new VideoCategory(categoryName: "NEWS", createdBy: "Initial data", updatedBy: "Initial data").save(failOnError: true)

                if (VideoCategory.count > 0) {
                    //init sample video
                    def vid01 = new YoutubeVideo(videoTitle: "Maddi Jane - Jar of Hearts", videoKey: "RT9PQgMtkDo", category: categoryMusic, available: true, rate: 5).save(failOnError: true)
                    def vid02 = new YoutubeVideo(videoTitle: "Maddi Jane - Rolling in The Deep", videoKey: "lMrCW07XBS8", category: categoryMusic, available: true, rate: 5).save(failOnError: true)
                }

                assert VideoCategory.count == 4
                assert YoutubeVideo.count == 2
            }

        }
    }
    def destroy = {
    }
}
