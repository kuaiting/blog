package com.yuan.constant;

public interface GlobalConst {

    //"F://Workspaces/blog/src/main/resources/static/"
    String BASE_PATH = "F://Workspaces/blog/src/main/resources/static/";
    String IMG_FOLDER = "imgs/";
    String head = "<!DOCTYPE html> <html> <head> <title>博客</title> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <style type=\"text/css\"> html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video { margin: 0; padding: 0; border: 0; } body { font-family: Helvetica, arial, freesans, clean, sans-serif; font-size: 14px; line-height: 1.6; color: #333; background-color: #fff; padding: 20px; max-width: 960px; margin: 0 auto; } body>*:first-child { margin-top: 0 !important; } body>*:last-child { margin-bottom: 0 !important; } p, blockquote, ul, ol, dl, table, pre { margin: 15px 0; } h1, h2, h3, h4, h5, h6 { margin: 20px 0 10px; padding: 0; font-weight: bold; -webkit-font-smoothing: antialiased; } h1 tt, h1 code, h2 tt, h2 code, h3 tt, h3 code, h4 tt, h4 code, h5 tt, h5 code, h6 tt, h6 code { font-size: inherit; } h1 { font-size: 28px; color: #000; } h2 { font-size: 24px; border-bottom: 1px solid #ccc; color: #000; } h3 { font-size: 18px; } h4 { font-size: 16px; } h5 { font-size: 14px; } h6 { color: #777; font-size: 14px; } body>h2:first-child, body>h1:first-child, body>h1:first-child+h2, body>h3:first-child, body>h4:first-child, body>h5:first-child, body>h6:first-child { margin-top: 0; padding-top: 0; } a:first-child h1, a:first-child h2, a:first-child h3, a:first-child h4, a:first-child h5, a:first-child h6 { margin-top: 0; padding-top: 0; } h1+p, h2+p, h3+p, h4+p, h5+p, h6+p { margin-top: 10px; } a { color: #4183C4; text-decoration: none; } a:hover { text-decoration: underline; } ul, ol { padding-left: 30px; } ul li > :first-child, ol li > :first-child, ul li ul:first-of-type, ol li ol:first-of-type, ul li ol:first-of-type, ol li ul:first-of-type { margin-top: 0px; } ul ul, ul ol, ol ol, ol ul { margin-bottom: 0; } dl { padding: 0; } dl dt { font-size: 14px; font-weight: bold; font-style: italic; padding: 0; margin: 15px 0 5px; } dl dt:first-child { padding: 0; } dl dt>:first-child { margin-top: 0px; } dl dt>:last-child { margin-bottom: 0px; } dl dd { margin: 0 0 15px; padding: 0 15px; } dl dd>:first-child { margin-top: 0px; } dl dd>:last-child { margin-bottom: 0px; } pre, code, tt { font-size: 12px; font-family: Consolas, \"Liberation Mono\", Courier, monospace; } code, tt { margin: 0 0px; padding: 0px 0px; white-space: nowrap; border: 1px solid #eaeaea; background-color: #f8f8f8; border-radius: 3px; } pre>code { margin: 0; padding: 0; white-space: pre; border: none; background: transparent; } pre { background-color: #f8f8f8; border: 1px solid #ccc; font-size: 13px; line-height: 19px; overflow: auto; padding: 6px 10px; border-radius: 3px; } pre code, pre tt { background-color: transparent; border: none; } kbd { -moz-border-bottom-colors: none; -moz-border-left-colors: none; -moz-border-right-colors: none; -moz-border-top-colors: none; background-color: #DDDDDD; background-image: linear-gradient(#F1F1F1, #DDDDDD); background-repeat: repeat-x; border-color: #DDDDDD #CCCCCC #CCCCCC #DDDDDD; border-image: none; border-radius: 2px 2px 2px 2px; border-style: solid; border-width: 1px; font-family: \"Helvetica Neue\",Helvetica,Arial,sans-serif; line-height: 10px; padding: 1px 4px; } blockquote { border-left: 4px solid #DDD; padding: 0 15px; color: #777; } blockquote>:first-child { margin-top: 0px; }  blockquote>:last-child { margin-bottom: 0px; } hr { clear: both; margin: 15px 0; height: 0px; overflow: hidden; border: none; background: transparent; border-bottom: 4px solid #ddd; padding: 0; } table th { font-weight: bold; } table th, table td { border: 1px solid #ccc; padding: 6px 13px; } table tr { border-top: 1px solid #ccc; background-color: #fff; } table tr:nth-child(2n) { background-color: #f8f8f8; } img { max-width: 100% } .BlogAnchor { background: #f1f1f1; padding: 10px; line-height: 180%; position: fixed; right: 48px; top: 48px; border: 1px solid #aaaaaa; } .BlogAnchor p { font-size: 18px; color: #15a230; margin: 0 0 0.3rem 0; text-align: right; } .BlogAnchor .AnchorContent { padding: 5px 0px; overflow: auto; } .BlogAnchor li{ text-indent: 0.5rem; font-size: 14px; list-style: none; } .BlogAnchor li .nav_item{ padding: 3px; } .BlogAnchor li .item_h1{ margin-left: 0rem; } .BlogAnchor li .item_h2{ margin-left: 2rem; font-size: 0.8rem; } .BlogAnchor li .nav_item.current{ color: white; background-color: #5cc26f; } #AnchorContentToggle { font-size: 13px; font-weight: normal; color: #FFF; display: inline-block; line-height: 20px; background: #5cc26f; font-style: normal; padding: 1px 8px; } .BlogAnchor a:hover { color: #5cc26f; } .BlogAnchor a { text-decoration: none; } </style> <script src=\"http://code.jquery.com/jquery-1.7.2.min.js\"></script> <script type=\"text/javascript\"> var showNavBar = true; var expandNavBar = true; $(document).ready(function(){ var h1s = $(\"body\").find(\"h1\"); var h2s = $(\"body\").find(\"h2\"); var h3s = $(\"body\").find(\"h3\"); var h4s = $(\"body\").find(\"h4\"); var h5s = $(\"body\").find(\"h5\"); var h6s = $(\"body\").find(\"h6\"); var headCounts = [h1s.length, h2s.length, h3s.length, h4s.length, h5s.length, h6s.length]; var vH1Tag = null; var vH2Tag = null; for(var i = 0; i < headCounts.length; i++){ if(headCounts[i] > 0){ if(vH1Tag == null){ vH1Tag = 'h' + (i + 1); }else{ vH2Tag = 'h' + (i + 1); } } } if(vH1Tag == null){ return; } $(\"body\").prepend('<div class=\"BlogAnchor\">' + '<span style=\"color:red;position:absolute;top:-6px;left:0px;cursor:pointer;\" οnclick=\"$(\\'.BlogAnchor\\').hide();\">×</span>' + '<p>' + '<b id=\"AnchorContentToggle\" title=\"收起\" style=\"cursor:pointer;\">目录▲</b>' + '</p>' + '<div class=\"AnchorContent\" id=\"AnchorContent\"> </div>' + '</div>' ); var vH1Index = 0; var vH2Index = 0; $(\"body\").find(\"h1,h2,h3,h4,h5,h6\").each(function(i,item){ var id = ''; var name = ''; var tag = $(item).get(0).tagName.toLowerCase(); var className = ''; if(tag == vH1Tag){ id = name = ++vH1Index; name = id; vH2Index = 0; className = 'item_h1'; }else if(tag == vH2Tag){ id = vH1Index + '_' + ++vH2Index; name = vH1Index + '.' + vH2Index; className = 'item_h2'; } $(item).attr(\"id\",\"wow\"+id); $(item).addClass(\"wow_head\"); $(\"#AnchorContent\").css('max-height', ($(window).height() - 180) + 'px'); $(\"#AnchorContent\").append('<li><a class=\"nav_item '+className+' anchor-link\" οnclick=\"return false;\" href=\"#\" link=\"#wow'+id+'\">'+name+\" · \"+$(this).text()+'</a></li>'); }); $(\"#AnchorContentToggle\").click(function(){ var text = $(this).html(); if(text==\"目录▲\"){ $(this).html(\"目录▼\"); $(this).attr({\"title\":\"展开\"}); }else{ $(this).html(\"目录▲\"); $(this).attr({\"title\":\"收起\"}); } $(\"#AnchorContent\").toggle(); }); $(\".anchor-link\").click(function(){ $(\"html,body\").animate({scrollTop: $($(this).attr(\"link\")).offset().top}, 500); }); var headerNavs = $(\".BlogAnchor li .nav_item\"); var headerTops = []; $(\".wow_head\").each(function(i, n){ headerTops.push($(n).offset().top); }); $(window).scroll(function(){ var scrollTop = $(window).scrollTop(); $.each(headerTops, function(i, n){ var distance = n - scrollTop; if(distance >= 0){ $(\".BlogAnchor li .nav_item.current\").removeClass('current'); $(headerNavs[i]).addClass('current'); return false; } }); }); if(!showNavBar){ $('.BlogAnchor').hide(); } if(!expandNavBar){ $(this).html(\"目录▼\"); $(this).attr({\"title\":\"展开\"}); $(\"#AnchorContent\").hide(); } }); </script> </head> <body>";
    String tail="</body></html>";
    interface State {
        int SHOW = 1;
        int HIDE = 0;
    }


}
