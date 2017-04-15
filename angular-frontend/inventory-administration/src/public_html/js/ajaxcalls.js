function getItems() {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        // this needs to be changed to our routes
        url: "https://demo1622751.mockable.io/",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {
        var json = jQuery.parseJSON(data);
        var result = "";

        for (i=0; i<json.length; ++i) {
            result += "<a href='viewDetails.html' class='list-group-item'>" + json[i].itemTypeName + " - " + json[i].manufacturer + ", " + json[i].model + "</a>";
        }

        $('#itemList').append(result);
    });
}

function getUsers() {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        // this needs to be changed to our routes
        url: "https://demo1622751.mockable.io/",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {
        console.log("Ušao u getUsers()");
        var json = jQuery.parseJSON(data);
        var result = "";

        for (i=0; i<json.length; ++i) {
            result += "<a href='viewUserDetails.html' class='list-group-item'>" + json[i].userName + ", " + json[i].email + "</a>";
        }

        $('#userList').append(result);
    });
}