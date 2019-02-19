$(document).ready(function () {

    $("#pageForm").submit(function (event) {

        //stop submit the form, we will post it manually.
    	event.preventDefault();
        fire_ajax_submit();

    });

});
function add(a,b){
	return a+b;
}
function fire_ajax_submit () {
        var nameField  = $("#name").val();
    	
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/baeldung/post/",
            data: JSON.stringify(nameField),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
           // 	alert("TESTED...");	
                var json = "<h4>Ajax Response</h4><pre>"
                    + JSON.stringify(data, null, 4) + "</pre>";
                $('#feedback').html(json);


            }
      
        });
 
}