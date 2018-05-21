
var file;
$(document).ready(function(){
        $(".nav-tabs a").click(function(){
            $(this).tab('show');
        });

    /*function printData()
    {
        var divToPrint=document.getElementById("test");
        newWin= window.open("");
        newWin.document.write(divToPrint.outerHTML);
        newWin.print();
        newWin.close();
    }*/


        $("#holder_table tr td a").click(function(){
            $("#infoHolderNoId").val($(this).closest('tr').find('#list_holderNo').text());
            $("#infoHolderTypId").val($(this).closest('tr').find('#list_holderTyp').text());
            $("#infoHolderShelfId").val($(this).closest('tr').find('#list_shelf').text());
            $("#infoHolderDateId").val($(this).closest('tr').find('#list_holderDate').text());
        })

        $("#printId").click(function(event) {
            var startTag ="<object   width=\"100%\" height=\"900px\" data= \"../images/Doc/";
            var endTag =".pdf\"  type=\"application/pdf\"></object>";
            var item = startTag.concat($("#infoHolderNoId").val()).concat(endTag);
            $.ajax({

                url: '../images/Doc/' + $("#infoHolderNoId").val() + '.pdf',
                type: 'HEAD',
                error: function () {
                    $.ajax({
                        type : "GET",
                        url :  "/barcode/create" ,
                        cache: false,
                        data: 'barcode=' + $("#infoHolderNoId").val() ,
                        success: function(response){
                            var pdfWindow = window.open("");
                            pdfWindow.document.write(item);
                        },
                        error : function(e) {
                            alert("Error!");
                        }
                    });

                },
                success: function () {
                    var pdfWindow = window.open("");
                    pdfWindow.document.write(item);
                    //$("#barcodPDF").append(item);
                   // window.open('../images/Doc/' + $("#infoHolderNoId").val() + '.pdf', "_blank", "fullscreen=yes");

                }
            });


        });

        $("#submit_handle").click(function(event) {
            if ( ! $("#newHolderForm")[0].checkValidity() )
            {
                // bonk! failed to validate, so return true which lets the
                // browser show native validation messages to the user
                return true;
            }
            else
            {
                event.preventDefault();
                ajaxGet(function (){

                });
                $('.nav-tabs a[href="#All"]').tab('show');


            }
        });

        $("#sumbitId").click(function(){
             $("#submit_handle").click();
        });

        function ajaxGet( callback){
            $.ajax({
                type : "POST",
                url :  "/holder/create" ,
                cache: false,
                data: 'batchId=' + $("#batchId").val()+'&holderTypId='+ $("#holderTypId").val() +'&shelfNoId='+ $("#shelfNoId").val() ,
                success: function(response){
                    var obj =JSON.stringify(response);
                    obj = JSON.parse(obj);
                    $("#infoHolderNoId").val(obj.holderNo);
                    $("#infoHolderTypId").val(obj.holderTyp);
                    $("#infoHolderShelfId").val(obj.shelf);
                    $("#infoHolderDateId").val(obj.holderDate);
                    file = obj.holderNo;
                    callback();
                },
                error : function(e) {
                    alert("Error!");
                }
            });


        }
    });


