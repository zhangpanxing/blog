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
            width:100%;
            text-align:center;
        }

        .image_ul{
            display:inline-block;
        }

        .image_li{
            float:left;
            width:200px;
            height:200px;
            margin:10px 5%;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            -o-border-radius: 10px;
            border-radius: 10px;
            cursor:pointer;
            list-style-type:none;
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
            width:100%;
            text-align:center;
        }

        .image_ul{
            /*display:inline-block;*/
        }

        .image_li{
            float:left;
            width:200px;
            height:200px;
            margin:10px 1%;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            -o-border-radius: 10px;
            border-radius: 10px;
            cursor:pointer;
            list-style-type:none;
        }

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
        <div class="txt_shadow" id="fontSizeId" style="color: #ffffff;position: absolute;top: 15%;z-index: 5;width: 100%;text-align:center;opacity: 0.7" > 人生如画，岁月如歌</div>
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
                 style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567448084025&di=1a42f6dfeb3b930ac820b9f7bf9c2a5f&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F8%2F57eb323138b89.jpg')">
            <div class="carousel-caption d-none d-md-block">
                    <h3 class="txt_shadow">路漫漫其修远兮，吾将上下而求索。</h3>
                    <p class="txt_shadow">The way ahead is so long without ending, yet high and low I'll search with my will unbending. </p>
                </div>
            </div>
            <!-- Slide Three - Set the background image for this slide in the line below -->
            <div class="carousel-item"
                 style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567447869170&di=c032edbb1cbd5dd5450c84e6a3bf4455&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F6%2F580487e53454e.jpg')">
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
            <li class="image_li" style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576141178439&di=43de955548176f91974d9a71d1d6269d&imgtype=0&src=http%3A%2F%2Fwww.15605.net%2Fueditor%2Fphp%2Fupload%2Fimage%2F20190401%2F1554096231284086.jpg');background-size: 100%;"></li>
            <li class="image_li" style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576141178439&di=d560270f256576d19cdeb044be050c50&imgtype=0&src=http%3A%2F%2Fimage1.qianqianhua.com%2Fuploads%2F20180519%2F02%2F1526668502-PcsuFyhbDz.jpg');background-size: 100%"></li>
            <li class="image_li" style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576143482942&di=a5e06b56739266cc55e5d24f01eea347&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F120825%2F214833-120R5191Q448.jpg');background-size: 100%"></li>
            <li class="image_li" style="background-image: url('https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576143210149&di=9d45cb80636b33c810c1b2fbcf924426&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201301%2F05%2F005020v3z3etzfv4rktf3k.jpg');background-size: 100%"></li>
            <li class="image_li" style="background-image: url('https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2480469743,1548081758&fm=15&gp=0.jpg');background-size: 100%"></li>
            <li class="image_li" style="background-image: url('https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2498426484,1366561395&fm=15&gp=0.jpg');background-size: 100%"></li>
        </ul>
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

<#-- JS -->
<#include "public/front_js.ftl">
</body>
</html>
