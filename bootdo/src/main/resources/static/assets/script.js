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
 }); 




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
                  html += '<option value="' + rows[i].type + '">' + rows[i].name + '</option>'
              }
              $("#"+elementId).append(html);
              $("#"+elementId).chosen({
                  maxHeight : 200
              });
          }
      });
  }