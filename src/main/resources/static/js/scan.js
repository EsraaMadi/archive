$( document ).ready(function() {



    var x = document.getElementById("doc_details");
     x.style.display = "none";


    $("#holder").keyup(function(){
        var textLength = $("#holder").val().length;
        if(textLength >0){
            $( "#barcode" ).prop( "disabled", false );
        }
        else
        {
            $( "#barcode" ).prop( "disabled", true );
        }
    })

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
                $("#PaperScanDate").text(obj.paperScanDate);
                $("#currHolderNo").text(obj.currDocumnetHolder);
                $("#preHolderNo").text(obj.pervDocumnetHolder);
                $("#docType").text(obj.documentType);
                $("#paperProcessDate").text(obj.paperProcessDate);
                $("#paperArchiveDate").text(obj.paperArchiveDate);
                $("#archiver").text(obj.archiver);
                $("#docSuccess").text(obj.docPassOnceSum);
                $("#docSuccessTwice").text(obj.docPassTwiceSum );
                $("#docFaild").text(obj.docFaildOnceSum);
                $("#docFaildTwice").text(obj.docFaildTwiceSum );
                var tx_color ;
                if(obj.archiveStatus == 4){ //yellow
                   $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-success" ).removeClass( "panel-info").addClass("panel-warning");
                    tx_color="#977d4f"
                   // $(".panel-danger , .panel-pricing").removeClass( "panel-danger" ).addClass("panel-warning");
                }
                else if(obj.archiveStatus == 1){ // green
                    $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).removeClass( "panel-info").addClass("panel-success");
                    tx_color="#4f844f"
                }
                else if(obj.archiveStatus == 3){ // red
                    $("#docPanel").removeClass( "panel-warning" ).removeClass( "panel-success" ).removeClass( "panel-info").addClass("panel-danger");
                    tx_color="#af504e"
                }
                else if(obj.archiveStatus == 2){ // blue
                    $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).removeClass( "panel-success" ).addClass("panel-info");
                    tx_color="#457f9b"
                }
                else
                 {
                    // $("#docPanel").removeClass( "panel-danger" ).removeClass( "panel-warning" ).addClass("panel-info");
                     tx_color="#818181";
                     //alert(obj.rersultMsg)
                     $("#messageId").text(obj.rersultMsg);
                     $("#messageModal").modal('show');
                 }
                $("#PaperScanDate").css("color", tx_color);
                $("#currHolderNo").css("color", tx_color);
                $("#preHolderNo").css("color", tx_color);
                $("#docType").css("color", tx_color);
                $("#paperProcessDate").css("color", tx_color);
                $("#paperArchiveDate").css("color", tx_color);
                $("#archiver").css("color", tx_color);

            },
            error : function(e) {
                alert("Error!")
            }
        });

        $("#barcode").val("");

    }


})