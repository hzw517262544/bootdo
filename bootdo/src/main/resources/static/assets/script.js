  $(document).ready(function() {
   $("#owl-example").owlCarousel();
  $('.listing-detail span').tooltip('hide');
        $('.carousel').carousel({
            interval: 3000
        }); 
        $('.carousel').carousel('cycle');

      /*加载select*/
      $("select").each(function (index,element) {
          var selectType = element.name;
          var id = element.id;
          if(selectType != undefined&&selectType != ''){
              getSelectType(id,selectType,null);
          }
      });
        //手机号码验证
      jQuery.validator.addMethod("isMobile", function(value, element) {
          var length = value.length;
          var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
          return this.optional(element) || (length == 11 && mobile.test(value));
      }, "请正确填写手机号码");

      validateRule_login();
      initLoginUserInfo();
  });

  /*$.validator.setDefaults({
      submitHandler : function() {
          debugger;
          easyRentLogin();
      }
  });*/

  $(function() {
        var Page = (function() {
          var $nav = $( '#nav-dots > span' ),
            slitslider = $( '#slider' ).slitslider( {
              onBeforeChange : function( slide, pos ) {

                $nav.removeClass( 'nav-dot-current' );
                $nav.eq( pos ).addClass( 'nav-dot-current' );

              }
            } ),

            init = function() {

              initEvents();
              
            },
            initEvents = function() {

              $nav.each( function( i ) {
              
                $( this ).on( 'click', function( event ) {
                  
                  var $dot = $( this );
                  
                  if( !slitslider.isActive() ) {

                    $nav.removeClass( 'nav-dot-current' );
                    $dot.addClass( 'nav-dot-current' );
                  
                  }
                  
                  slitslider.jump( i + 1 );
                  return false;
                
                } );
                
              } );

            };

            return { init : init };

        })();

        Page.init();

        /**
         * Notes: 
         * 
         * example how to add items:
         */

        /*
        
        var $items  = $('<div class="sl-slide sl-slide-color-2" data-orientation="horizontal" data-slice1-rotation="-5" data-slice2-rotation="10" data-slice1-scale="2" data-slice2-scale="1"><div class="sl-slide-inner bg-1"><div class="sl-deco" data-icon="t"></div><h2>some text</h2><blockquote><p>bla bla</p><cite>Margi Clarke</cite></blockquote></div></div>');
        
        // call the plugin's add method
        ss.add($items);

        */
      
      });

  function getSelectType(elementId,selectType,parentSelectTypeId) {
      $.ajax({
          url : '/rent/loadDict',
          data:{
              limit: 10000,
              offset:0,
              'type':selectType,
              'parentId':parentSelectTypeId
          },
          success : function(data) {
              //加载数据
              var html = "";
              var rows = data.rows;
              for (var i = 0; i < rows.length; i++) {
                  html += '<option value="' + rows[i].value + '">' + rows[i].name + '</option>'
              }
              $("#"+elementId).append(html);
              $("#"+elementId).chosen({
                  maxHeight : 200
              });
          }
      });
  }


  function easyRentLogin() {
      if(!$('#easyRentLoginForm').valid()){
            return false;
      }
      $.ajax({
          url : '/rent/login',
          type : "POST",
          data:$('#easyRentLoginForm').serialize(),
          success : function(data) {
              if (data.code == 0) {
                  /*$("#nav_logout").show();
                  $("#nav_login").hide();
                  $("#nav_register").hide();*/
                  $('#loginpop').modal('hide');
                  initLoginUserInfo();
              }else {
                  parent.layer.alert(data.msg);
              }
          }
      });
  }

  function validateRule_login() {
      var icon = "<i class='fa fa-times-circle'></i> ";
      $("#easyRentLoginForm").validate({
          rules : {
              username_login : {
                  required : true
              },
              password_login : {
                  required : true,
              }
          },
          messages : {
              username_login : {
                  required : icon + "请输入用户名"
              },
              password_login : {
                  required : icon + "请输入密码"
              }
          }
      })
  }

  //设置自定义过期时间cookie
  function setCookie(name,value,time)
  {
      var msec = getMsec(time); //获取毫秒
      var exp = new Date();
      exp.setTime(exp.getTime() + msec*1);
      document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
  }

  //将字符串时间转换为毫秒,1秒=1000毫秒
  function getMsec(DateStr)
  {
      var timeNum=DateStr.substring(0,DateStr.length-1)*1; //时间数量
      var timeStr=DateStr.substring(DateStr.length-1,DateStr.length); //时间单位前缀，如h表示小时

      if (timeStr=="s") //20s表示20秒
      {
          return timeNum*1000;
      }
      else if (timeStr=="h") //12h表示12小时
      {
          return timeNum*60*60*1000;
      }
      else if (timeStr=="d")
      {
          return timeNum*24*60*60*1000; //30d表示30天
      }
  }

  /**
   * 读取Cookie
   * @param name
   * @returns {*}
   */
  function getCookie(name)
  {
      var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); //正则匹配
      if(arr=document.cookie.match(reg)){
          return unescape(arr[2]);
      }
      else{
          return null;
      }
  }
  /**
   * 删除Cookie
   * @param name
   * @returns {*}
   */
  function delCookie(name)
  {
      var exp = new Date();
      exp.setTime(exp.getTime() - 1);
      var cval=getCookie(name);
      if(cval!=null){
          document.cookie= name + "="+cval+";expires="+exp.toGMTString();
      }
  }
  
  function initLoginUserInfo() {
      $.ajax({
          url : '/rent/initLoginUserInfo',
          type : "POST",
          data:{},
          success : function(data) {
              if (data.code == 0) {
                  var easyRentLoginUser = data.easyRentLoginUser;
                  if(easyRentLoginUser.username == undefined||easyRentLoginUser.username == ''){
                      $("#nav_logout").hide();
                      $("#nav_login").show();
                      $("#nav_register").show();
                  }else{
                      $("#nav_logout").show();
                      $("#nav_login").hide();
                      $("#nav_register").hide();
                  }
                  if(data.easyRentLoginUserPicUrl != undefined&&data.easyRentLoginUserPicUrl != ''&&
                      data.easyRentLoginUserPicUrl != $("#nav_img").attr("src")){
                      $("#nav_img").attr("src", data.easyRentLoginUserPicUrl);
                  }
                  var easyRentCompanyInfo = data.easyRentCompanyInfo;
                  if(easyRentCompanyInfo != undefined&&easyRentCompanyInfo.length>0){
                      for(var i=0;i<easyRentCompanyInfo.length;i++){
                            var element = easyRentCompanyInfo[i];
                            if($("#"+element.name).text() != element.value){
                                $("#"+element.name).text(element.value);
                            }
                      }
                  }
              }else {
                  parent.layer.alert(data.msg);
              }
          }
      });
  }