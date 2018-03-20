$( document ).ready(function() {

    /*$("#docSuccess").text(0);
    $("#docSuccessTwice").text(0);
    $("#docFaild").text(0);
    $("#docFaildTwice").text(0);*/
    var x = document.getElementById("doc_details");
     x.style.display = "none";
    $("#barcode").keyup(function(){
        var textLength = $("#barcode").val().length;
        if(textLength == 13){
            ajaxGet();

        }
    })


    // DO GET
    function ajaxGet(){
        var documentId = 0;
        var documentNo = $("#barcode").val();
        var holderNo = $("#holder").val();
        $.ajax({
            type : "GET",
            url :  "/Archive/doc/" +holderNo ,
            cache: false,
            data: 'documentNo=' + documentNo +'&documentId='+ documentId ,
            success: function(response){
                x.style.display = "block";
                var obj =JSON.stringify(response);
                obj = JSON.parse(obj);
                $("#docbarcode").text(obj.documentNo);
                $("#PaperScanDate").text("Paper Scan Date : "+obj.paperScanDate);
                $("#currHolderNo").text("Current Holder Number : " +obj.currDocumnetHolder);
                $("#preHolderNo").text("Previous Holder Number : "+obj.pervDocumnetHolder);
                $("#docType").text("Document Type : "+obj.documentType);
                $("#paperProcessDate").text("Paper Processing Date : "+obj.paperProcessDate);
                $("#paperArchiveDate").text("Paper Archive Date : "+obj.paperArchiveDate);
                $("#archiver").text("Archiver Username : "+obj.archiver);
                $("#docSuccess").text(obj.docPassOnceSum);
                $("#docSuccessTwice").text(obj.docPassTwiceSum );
                $("#docFaild").text(obj.docFaildOnceSum);
                $("#docFaildTwice").text(obj.docFaildTwiceSum );
                if(obj.archiveStatus == 4){
                   $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-success" ).removeClass( "panel-info").addClass("panel-warning");
                   // $(".panel-danger , .panel-pricing").removeClass( "panel-danger" ).addClass("panel-warning");
                }
                else if(obj.archiveStatus == 1){
                    $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).removeClass( "panel-info").addClass("panel-success");
                }
                else if(obj.archiveStatus == 3){
                    $("#docPanel").removeClass( "panel-warning" ).removeClass( "panel-success" ).removeClass( "panel-info").addClass("panel-danger");
                }
                else if(obj.archiveStatus == 2){
                    $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).removeClass( "panel-success" ).addClass("panel-info");
                }
                else
                 {
                    // $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).addClass("panel-info");
                    alert(obj.rersultMsg)

                }

            },
            error : function(e) {
                alert("Error!")
            }
        });

        $("#barcode").val("");

    }


})