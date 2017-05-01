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

        $('#itemId').html(json.idItem);
        $('#itemName').html(json.itemTypeName);
        $('#itemModel').html(json.model);
        $('#itemManufacturer').html(json.manufacturer);
        $('#itemSerialNumber').html(json.serialNumber);
        $('#itemAcquireDate').html(json.aquireDate);

        // hidden
        /*
        console.log(json.severity);
        $('#severity').html(json.severity);
        $('#cost').html(json.cost);
        $('#yellowTag').html(json.yellowTag);
        $('#assetTag').html(json.assetTag);
        $('#warrentyId').html(json.warrentyId);
        $('#damageId').html(json.damageId);
        $('#typeId').html(json.typeId);
        $('#itemTypeId').html(json.itemTypeId);
        //
        */

        $('#itemid-input').attr("value", json.idItem);
        $('#itemSeverity-input').attr("value", json.severity);
        $('#itemSerialNumber-input').attr("value", json.serialNumber);
        $('#itemCost-input').attr("value", json.cost);
        $('#yellowtag-input').attr("value", json.yellowTag);
        $('#assettag-input').attr("value", json.assetTag);
        $('#itemManufacturer-input').attr("value", json.manufacturer);
        $('#warrentyid-input').attr("value", json.warrentyId);
        $('#damageid-input').attr("value", json.damageId);
        $('#procurement-input').attr("value", json.procurementOrder);
        $('#itemName-input').attr("value", json.itemTypeName);
        $('#itemModel-input').attr("value", json.model);
        $('#typeid-input').attr("value", json.itemTypeId);
        $('#dept-input').attr("value", json.department);
        $('#itemAcquireDate-input').attr("value", json.aquireDate);

        // tu popuni sve do kraja, a onda dole napravi novu ajax metodu koja ce radit put i povlacit vrijednosti iz ovih fieldova
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

        $('#userId').html(json.borrowerId);
        $('#userName').html(json.userName);
        $('#userEmail').html(json.email);
        $('#userMajor').html(json.majorTitle);
        $('#userClass').html(json.className);

        console.log(json.userName);

        $('#userid-input').attr("value", json.borrowerId);
        $('#classId-input').attr("value", json.classId);
        $('#classTitle-input').attr("value", json.classTitle);
        $('#majorId-input').attr("value", json.majorId);
        $('#flagged-input').attr("value", json.flagged);
        $('#className-input').attr("value", json.className);
        $('#section-input').attr("value", json.section);
        $('#userName-input').attr("value", json.userName);
        $('#email-input').attr("value", json.email);
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