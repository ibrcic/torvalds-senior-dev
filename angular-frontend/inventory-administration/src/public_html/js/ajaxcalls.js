function getItems() {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        url: "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/items/data.json",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {
        var json = jQuery.parseJSON(data);
        var result = "";

        for (i=0; i<json.length; ++i) {
            result += "<a href='viewDetails.html?id="+ json[i].idItem +"' class='list-group-item'>" + json[i].itemTypeName + " - " + json[i].manufacturer + ", " + json[i].model + "</a>";
        }

        $('#itemList').append(result);
    });
}

function getItemDetails(id) {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        url: "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/items/"+ id +"/data.json",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {       
        var json = jQuery.parseJSON(data);
        var result = "";

        $('#itemName').html(json.itemTypeName);
        $('#itemModel').html(json.model);
        $('#itemManufacturer').html(json.manufacturer);
        $('#itemSerialNumber').html(json.serialNumber);
        $('#itemAcquireDate').html(json.aquireDate);
    });
}

function getUserDetails(id) {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        url: "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/users/"+ id +"/data.json",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {       
        var json = jQuery.parseJSON(data);
        var result = "";

        $('#firstName').html(json.firstName);
        $('#lastName').html(json.lastName);
        $('#username').html(json.userName);
        $('#email').html(json.email);
        $('#majorTitle').html(json.majorTitle);
        $('#className').html(json.className);
    });
}

function getUsers() {
    $.ajax({
        type: "GET",
        async: true,
        cache: false,
        url: "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/users/data.json",
        data: "",
        dataType: "text"
    }).fail(function() {
        console.log("ajax fail");
    }).success(function(data) {
        var json = jQuery.parseJSON(data);
        var result = "";

        for (i=0; i<json.length; ++i) {
            result += "<a href='viewUserDetails.html?id="+ json[i].borrowerId +"' class='list-group-item'>" + json[i].userName + ", " + json[i].email + "</a>";
        }

        $('#userList').append(result);
    });
}