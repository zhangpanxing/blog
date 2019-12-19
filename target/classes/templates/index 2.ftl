<html lang="zh">
<head>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title!'路漫漫其修远'}</title>

    <!-- Custom styles for this template -->
    <link href="/css/full-slider/full-slider.css" rel="stylesheet">

<#-- 自定义 样式 -->
<#include "public/front_custom_css.ftl">

<#-- CSS -->
<#include "public/front_css.ftl">



<#-- JS -->
<#include "public/front_js.ftl">
</head>

<body>
<style>
    @media (max-width: 768px) {
        #fontSizeId{
            font-size: 30px;
        }
        #up_test{
            font-size: 25px;
        }
        .image_ul:after{
            content:'';
            visibility:hidden;
            font-size:0;
            height:0;
            display:block;
            clear:both;
        }

        .image_div{
            margin-top: -30px;
            width:90%;
            margin-left: 5%;
            margin-bottom: -30px;
        }

        .image_ul{
           padding-left: 30px;
            display:inline-block;

        }

        .image_li{
            float:left;
            width:110px;
            height:110px;
            margin:10px 6%;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            -o-border-radius: 10px;
            border-radius: 10px;
            cursor:pointer;
            list-style-type:none;
            overflow:hidden;
            /*background-size: 100%*/
        }

    }


    @media (min-width: 768px) {
        #fontSizeId{
            font-size: 60px;
        }
        #up_test{
            font-size: 50px;
        }
        .image_ul:after{
            content:'';
            visibility:hidden;
            font-size:0;
            height:0;
            display:block;
            clear:both;
        }

        .image_div{
            margin-top: -30px;
            width:90%;
            margin-left: 5%;
            margin-bottom: -30px;
        }

        .image_ul{
            padding-left: 0px;
        }

        .image_li{
            width: 14%;
            height: 12vw;
            float:left;
            /*width:200px;*/
            /*height:200px;*/
            margin:10px 1.3%;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            -o-border-radius: 10px;
            border-radius: 10px;
            cursor:pointer;
            list-style-type:none;
            overflow:hidden;
            background-size: 100%
        }
    }

    .image_img{
        width: 100%;
        height: 100%;
        top: 0;
        position: relative;
        left: 0;
        -webkit-transition: -webkit-transform 0.3s;
        -moz-transition: -moz-transform 0.3s;
        -o-transition: -o-transform 0.3s;
        transition: transform 0.3s;
    }
    .image_img:hover{
        -webkit-transform: scale(1.5);
        -moz-transform: scale(1.5);
        -ms-transform: scale(1.5);
        -o-transform: scale(1.5);
        transform: scale(1.5);
    }





</style>
<#assign page_index = 0>
<#-- s-nav.ftl -->
<#include "public/nav.ftl">
<#-- e-nav.ftl -->

<#-- s-slide-header-->
<header>
    <div id="slideIndicators" class="carousel slide" data-ride="carousel" data-interval="3000" data-pause="">
        <ol class="carousel-indicators">
            <li data-target="#slideIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#slideIndicators" data-slide-to="1"></li>
            <li data-target="#slideIndicators" data-slide-to="2"></li>
        </ol>
        <div class="txt_shadow" id="fontSizeId" style="color: #fdfffd;position: absolute;top: 15%;z-index: 5;width: 100%;text-align:center;opacity: 0.7" > 人生如画，岁月如歌</div>
        <div class="carousel-inner" role="listbox">

            <!-- Slide One - Set the background image for this slide in the line below -->
            <div class="carousel-item active"
                 style="background-image: url('http://cdn.azmart.com.cn/pdf/5ab4494ad387789f4da22306bc0a71b.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h3 class="txt_shadow">世界上一成不变的东西，只有“任何事物都是在不断变化的”这条真理。</h3>
                    <p class="txt_shadow">The only constant thing in the world is the truth that everything is changing. </p>
                </div>
            </div>
            <!-- Slide Two - Set the background image for this slide in the line below -->
            <div class="carousel-item"
                 style="background-image: url('http://ppe.oss-cn-shenzhen.aliyuncs.com/palette/133331/1575112215532/thumb_Sat_Nov_30_2019.jpg')">
            <div class="carousel-caption d-none d-md-block">
                    <h3 class="txt_shadow">路漫漫其修远兮，吾将上下而求索。</h3>
                    <p class="txt_shadow">The way ahead is so long without ending, yet high and low I'll search with my will unbending. </p>
                </div>
            </div>
            <!-- Slide Three - Set the background image for this slide in the line below -->
            <div class="carousel-item"
                 style="background-image: url('https://cdn.pixabay.com/photo/2016/11/22/21/47/architecture-1850732_1280.jpg')">
                <div class="carousel-caption d-none d-md-block">
                    <h3 class="txt_shadow">你们中大多数人都熟悉程序员的美德，有三种：那就是懒惰、急躁和傲慢</h3>
                    <p class="txt_shadow">Most of you are familiar with the programmer's virtues, and there are three: laziness,
                        impatience, and arrogance. </p>
                </div>
            </div>
        </div>
    <#--<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">-->
    <#--<span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
    <#--<span class="sr-only">Previous</span>-->
    <#--</a>-->
    <#--<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">-->
    <#--<span class="carousel-control-next-icon" aria-hidden="true"></span>-->
    <#--<span class="sr-only">Next</span>-->
    <#--</a>-->
    </div>
</header>
<#-- e-slide-header-->
<#--中间图片-->
<section class="py-5" style="background-color: rgba(16,15,15,0.6);" >
    <div class="image_div">
        <ul class="image_ul">
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2016/12/07/23/41/winter-1890653_1280.jpg"/>
            </li>
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2015/01/10/10/50/road-595101_1280.jpg"/>
            </li>
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2019/11/17/16/45/windrader-4632839_1280.jpg"/>
            </li>
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2015/02/03/07/52/winter-622126_1280.jpg"/>
            </li>
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2017/08/03/14/55/perspective-2576685_1280.jpg"/>
            </li>
            <li class="image_li"    >
                <img class="image_img" src="https://cdn.pixabay.com/photo/2019/10/07/14/00/mountain-4532686_1280.jpg"/>
            </li>
        </ul>
        <script  type="text/javascript">
            $(document).ready(function(){
                 //alert("dasdas");
                $('.image_li>.image_img').mouseover(function(){
                    $(this).css({"opacity":"0.4","z-index":"9999"})
                });
                $('.image_li>.image_img').mouseout(function () {
                    $(this).css({"opacity":"1","z-index":"9999"})
                });
            });
        </script>



    </div>
</section>

<!-- Page Content -->
<section class="py-5">
    <div class="container container-fluid" style="width: 100%;text-align: center;">
        <h1 id="up_test">修行之道 —— 想做一条咸鱼的程序员</h1>
    </div>
</section>

<#-- s-footer -->
<#include "public/footer.ftl">
<#-- e-footer -->

</body>
</html>
