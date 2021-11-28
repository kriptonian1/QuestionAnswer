$(document).ready(function(){
    console.log(getCookie("USERNAME"));
    if (getCookie("userId") == ""){
        $("header").append(btnSignupHtml);
        $("header").append(btnLoginHtml);
    }
});

var btnSignupHtml = "<button onclick='onSignupClicked()''>Home</button>";
var btnLoginHtml = "<button onclick='onLoginClicked()''>Home</button>";

function generateUserDescription(){
    if (getCookie("userId") == "")
        return "Sign in to view your information";
    else{
        $.get(
            "/QuestionAnswer/user/id/"+getCookie("userId"),
            function (data, status){
                console.log(data);
                console.log(status);
            }
        );
    }
}