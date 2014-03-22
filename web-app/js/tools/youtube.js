var url = "";
var tDivItem = "";
var count;
var q = "";
var maxResults = "";
function search() {
    tDivItem = "";
    q = $('#query').val();
    maxResults = $('#max-results').val();
    if (!youtubeValidateSearch()) {
        return;
    }
    $("#search-container-2").html("<p class='text-center'><img src='" + loadingGif + "'/><br>Loading..!!!</p>");
    url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + q + "&key=" + key + "&maxResults=" + maxResults;
    $.getJSON(url , function (jsonData) {
    //$.getJSON("http://localhost:8080/my-youtube-favorite/search.json", function (jsonData) {  //example so can fast query
        tbodyYoutube = "";
        count = 0;
        $.each(jsonData.items, function (index, itemData) {
            count++;
            var vidKey = itemData.id.videoId;
            var vidTitle = escapeSpecialChar(youtubeShortTitle(itemData.snippet.title));
            var vidPublishedAt = new Date(itemData.snippet.publishedAt).format("dd mmm yyyy hh:MM:ss TT");
            var vidThumbnail = itemData.snippet.thumbnails.default.url;


            tDivItem += "<div class='col-md-2 youtube-thumbnail'>";
            tDivItem += "<p class='text-center'><a href='#' class='btn btn-sm' onclick='addYoutubeVideo(\"" + vidTitle + "\",\"" + vidKey + "\")'>";
            tDivItem += "<span class='glyphicon glyphicon-plus-sign'></span> Add</a>&nbsp";
            tDivItem += "<a href='#' class='btn btn-sm' onclick='viewYoutubeVideoPreview(\"" + vidKey + "\")'><span class='glyphicon glyphicon-search'></span> View</a>";
            tDivItem += "<img class='img-thumbnail' style='width:150px;height:120px;' src='" + vidThumbnail + "'/><hr></p>";
            tDivItem += "<span class='text-bold'>" + vidTitle + "</span><br>";
            /* tDivItem += "<span class='text-info'>" + vidKey+"</span><br>";*/
            tDivItem += "</div> ";
        });
        $("#search-container-2").html(tDivItem);
    });
}

function addYoutubeVideo(title, key) {
    if (key == "undefined") {
        bootbox.alert("That video cannot be add", function (result) {

        });
        return;
    }
    $.get(""+appContext+"/youtubeVideo/addFromList?videoTitle=" + title + "&videoKey=" + key + "&rate=5", function (htmlData) {
        $("#dialogRef").find("#dataContent").html(htmlData);
    });
    $("#dialogRef").modal('show');
}

function viewYoutubeVideoPreview(key) {
    window.open("https://www.youtube.com/embed/" + key, "_blank");
}

function youtubeValidateSearch() {
    if (($("#query").val()).trim().length == 0) {
        bootbox.alert("Please enter the query to search");
        return false;
    }
    return true;
}