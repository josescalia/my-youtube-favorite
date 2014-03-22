<%--
  Created by IntelliJ IDEA.
  User: josescalia
  Date: 3/16/14
  Time: 7:12 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="custom-layout-01">
    <title></title>
</head>

<body>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h4 class="panel-title text-center">Video Summary By Category</h4>
    </div>

    <div class="panel-body">
        <div class="col-lg-3">
            <div id="rssFeederSumData">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="text-center">Category</th>
                        <th class="text-center">Count</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
        </div>

        <div class="col-lg-9">
            <div class="graph-container">
                <canvas id="cvsByCat" width="600" height="425">[No canvas support]</canvas>
            </div>
        </div>
    </div>
</div>

<div class="panel panel-info">
    <div class="panel-heading">
        <h4 class="panel-title text-center">Video Summary By Rate</h4>
    </div>

    <div class="panel-body">
        <div class="col-lg-3">
            <div id="videoRateSumData">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="text-center">Rate</th>
                        <th class="text-center">Count</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
        </div>

        <div class="col-lg-9">
            <div class="graph-container">
                <canvas id="cvsByRate" width="600" height="225">[No canvas support]</canvas>
            </div>
        </div>
    </div>
</div>
%{--add for graph animtaion--}%
<link rel="stylesheet" href="${resource(dir: 'css/rgraph', file: 'animations.css')}" media="screen"/>
<script type="text/javascript" src="${resource(dir: 'js/raty', file: 'jquery.raty.js')}"></script> %{--javascript rating--}%
<script type="text/javascript" src="${resource(dir: 'js/rgraph', file: 'RGraph.common.core.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js/rgraph', file: 'RGraph.common.dynamic.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js/rgraph', file: 'RGraph.common.effects.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js/rgraph', file: 'RGraph.bar.js')}"></script>
<script type="text/javascript">
    var tBody;
    var tFoot;
    var tBodyRate;
    var tFootRate;
    $(document).ready(function () {
        $.get("videoSumByCat", function (sumCategoryJsonData) {
            $.each(sumCategoryJsonData.detail, function (key, value) {
                tBody += "<tr>";
                tBody += "<td>" + key + "</td>";
                tBody += "<td>" + value + "</td>";
                tBody += "</tr>";
            });
            tFoot = "<tr style='background-color:#efefef' class='text-bold'><td> Total</td><td>" + sumCategoryJsonData.total + "</td></tr>";
            $("#rssFeederSumData").find("tbody").html(tBody);
            $("#rssFeederSumData").find("tfoot").html(tFoot);
        });

        //generating rGraph
        $.get("videoSumByStatusForRGraph", function (jsonDataCategory) {
            var barGraph = new RGraph.Bar('cvsByCat', jsonDataCategory.data)
                    .Set('labels', jsonDataCategory.labels)
                    .Set('shadow', true)
                    .Set('colors.sequential', true)
                    .Set('title', "Video Summary By Category")
                    .Set('title.size', 10)
                    .Set('title.vpos', 0.3)
                    .Set('title.color', 'darkgreen')
                    .Set('labels.above.specific', jsonDataCategory.data)
                    .Set('gutter.bottom', 100)
                    .Set('gutter.left', 150)
                    .Set('gutter.top', 35)
                    .Set('gutter.right', 100)
                    .Set('text.angle', 45)
                    .Draw();
        });

        $.get("videoSumByRate", function (sumRateJsonData) {
            $.each(sumRateJsonData.detail, function (key, value) {
                var starTemp="";
                for(var z=0; z < key;z++){
                    starTemp +=" <span class='glyphicon glyphicon-star'></span>";
                }
                tBodyRate += "<tr>";
                tBodyRate += "<td>" + starTemp + "</td>";
                tBodyRate += "<td>" + value + "</td>";
                tBodyRate += "</tr>";
            });
            tFootRate = "<tr style='background-color:#efefef' class='text-bold'><td> Total </td><td>" + sumRateJsonData.total + "</td></tr>";
            $("#videoRateSumData").find("tbody").html(tBodyRate);
            $("#videoRateSumData").find("tfoot").html(tFootRate);
        });

        //generating rGraph
        $.get("videoSumByRateForRGraph", function (jsonDataRate) {
            var starTemp = "";
            var modLabel =[];
            $.each(jsonDataRate.labels,function(key,value){
                starTemp = "*";
                for(var z=1; z <= key;z++){
                    starTemp += "*";
                    //starTemp += "*";
                }
                modLabel.push(starTemp)
            });
            console.log(modLabel);
            var barGraph = new RGraph.Bar('cvsByRate', jsonDataRate.data)
                    .Set('labels', jsonDataRate.labels)
                    .Set('labels.above.specific', modLabel)
                    .Set('shadow', true)
                    .Set('colors.sequential', true)
                    .Set('title', "Video Summary By Rate")
                    .Set('title.size', 10)
                    .Set('title.vpos', 0.3)
                    .Set('title.color', 'darkgreen')
                    .Set('gutter.bottom', 20)
                    .Set('gutter.left', 150)
                    .Set('gutter.top', 35)
                    .Set('gutter.right', 100)
                    .Draw();
        });

    })
</script>
</body>
</html>