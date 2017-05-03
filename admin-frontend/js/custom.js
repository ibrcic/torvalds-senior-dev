// function for getting GET parameters from URL (with javascript)
function $_GET(param) {
    var vars = {};
    window.location.href.replace( location.hash, '' ).replace( 
        /[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
        function( m, key, value ) { // callback
            vars[key] = value !== undefined ? value : '';
        }
    );

    if ( param ) {
        return vars[param] ? vars[param] : null;    
    }
    return vars;
}

function getEditItemLink(id) {
    console.log(id);
}

/*
$("#saveButtonItem").click(function(e) {
    console.log($('#itemid-input').val());

    e.preventDefault();
    $.ajax({
        type: "PUT",
        async: true,
        cache: false,
        url: "http://team-torvalds.ist.rit.edu:8080/LocalISTInventoryWebService/services/items/update",
        data: {
            id: $('#itemid-input').val(),
            severity: $('#itemSeverity-input').val(),
            serialNumber: $('#itemSerialNumber-input').val(),
            cost: $('#itemCost-input').val(),
            yellowTag: $('#yellowtag-input').val(),
            assetTag: $('#assettag-input').val(),
            manufacturer: $('#itemManufacturer-input').val(),
            warrentyId: $('#warrentyid-input').val(),
            damageId: $('#damageid-input').val(),
            procurementOrder: $('#procurement-input').val(),
            itemTypeName: $('#itemName-input').val(),
            model: $('#itemmodel-input').val(),
            itemTypeId: $('#typeid-input').val(),
            department: $('#dept-input').val(),
            aquireDate: $('#acquireDate-input').val()
        },
        success: function() {
            console.log('ok');
        },
        error: function(result) {
            console.log('error');
        }
    });
});*/