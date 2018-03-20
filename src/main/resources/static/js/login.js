   /* jQuery.i18n.properties({
        name: 'messages',
        path: 'bundle/',
        mode: 'both',
        language: 'en',
        callback: function () {
            $("#userEmail").text($.i18n.prop('userAuthCommand.password'));
            //jQuery.i18n.prop('msg_hello');
            alert(jQuery.i18n.prop('userAuthCommand.password'));
        }
    });
*/

/*var email = document.getElementById("userEmail");

email.addEventListener("input", function (event) {
    if (email.validity.typeMismatch) {
        email.setCustomValidity("I expect an e-mail, darling!");
    } else {
        email.setCustomValidity("");
    }
});*/

//});



/*$("#newUserForm").submit(function(event) {

    alert ("Esraa")
});

    $("#sumbitId").onclick(function(){
        $("#newUserModal").modal('open');

        });*/

  /*  function formSubmit()
    {
        alert ("Esraa")
       // alert ($("#newUserForm").validationMessage)
        var email = $("#userEmail").val();
        var password = $("#userPassword").val();
        $.ajax({
            url: "/NewUserW",
            type: "POST",
            cache: false,
            data: 'password='+ password ,
            //data: 'user_Password='+ password +' &userEmail=' + email ,
            success: function (response) {
                //console.log(response)
                var obj =JSON.stringify(response);
                obj = JSON.parse(obj);
                $("#userEmail").val("");
                //window.location.reload();
               // history.go(0);
               // alert(obj.username);
            },
            error : function(e) {
                alert("Error!")
            }
        });


    }*/

