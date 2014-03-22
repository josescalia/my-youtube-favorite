package org.josescalia.youtube.report

import grails.converters.JSON
import org.josescalia.youtube.master.VideoCategory
import org.josescalia.youtube.transaction.YoutubeVideo

class SummaryController {
    def map  = null

    def index() {
    }

    def videoYoutube(){

    }

    def videoSumByRate(){
        def mapDetail = new HashMap<Integer, Integer>()
        def arrRate = [1,2,3,4,5] //5 star rate

        def totalData = 0
        arrRate.each {
            def sum = YoutubeVideo.executeQuery(" from YoutubeVideo where rate=" + it).size()
            totalData += sum
            mapDetail.put(it, sum)
        }
        map = [total: totalData, detail: mapDetail]
        render map as JSON

    }

    def videoSumByRateForRGraph() {
        def data = new ArrayList()
        def labels = new ArrayList()
        def arrStatus = [1,2,3,4,5]
        arrStatus.each {
            data.add(YoutubeVideo.executeQuery(" from YoutubeVideo where rate=" + it).size())
            labels.add("Score " + it)
        }
        map = [labels: labels, data: data]
        render map as JSON
    }


    def videoSumByCat(){
        def mapDetail = new HashMap<String, Integer>()
        def arrStatus = VideoCategory.list()

        def totalData = 0
        arrStatus.each {
            def sum = YoutubeVideo.executeQuery(" from YoutubeVideo where category.id=" + it.id).size()
            totalData += sum
            mapDetail.put(it.categoryName, sum)
        }
        map = [total: totalData, detail: mapDetail]
        render map as JSON
    }

    def videoSumByStatusForRGraph() {
        def data = new ArrayList()
        def labels = new ArrayList()
        def arrStatus = VideoCategory.list()
        arrStatus.each {
            data.add(YoutubeVideo.executeQuery(" from YoutubeVideo where category.id=" + it.id).size())
            labels.add(it.categoryName)
        }
        map = [labels: labels, data: data]
        render map as JSON
    }
}
