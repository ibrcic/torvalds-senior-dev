/**
 * Created by Ruzica on 3.5.2017..
 */
function validate(){
    var username = document.getElementById("inputEmail").value;
    var password = document.getElementById("inputPassword").value;
    if ( username == "admin" && password == "admin"){
        alert ("Login successfully");
        window.location = "home.html";
        return false;
    }
    else{
        alert("Username or password is not correct!");
    }
}