var pages = ["ingredientInfo", "recipeInfo"];
var current = 0;
var inquiryResponse = {};

var number = 5;


//x = new id
function add(x) {
    console.log("creating new ingredient box with id: ingredient" + x);
    var newNum = parseInt(x) + 1;
    var oldNum = parseInt(x) - 1;
    var newId = "ingredient" + newNum;

    var newTextBoxHTML = "<input type=\"text\" name=\"ings\" class=\"form-control\"" +
        "placeholder=\"Enter Ingredient\"" +
        "id=\"ingredient" + x + "\"" +
        "required" +
        "data-validation-required-message=\"Please enter a valid Ingredient\">\n";


    var newMinusButtonHTML = "<button id=\"rem" + x + "\"  onclick=\"remove('" + x + "')\" class=\"glyphicon glyphicon-minus\" aria-hidden=\"true\"></button>\n"
    var newAddButtonHTML = "<button id=\"add" + x + "\" onclick=\"add('" + newNum + "')\" class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></button>"



    var p = "<p id=\"" + x + "\" class=\"help-block text-danger\"></p>";

    var newHtml = newTextBoxHTML + newMinusButtonHTML + newAddButtonHTML + p;

    // div.innerHTML = div.innerHTML + newTextBoxHTML + newButtonHTML + p;

    $("#" + oldNum).after(newHtml);

    $("#rem" + oldNum).addClass("inactive");
    $("#add" + oldNum).addClass("inactive");

    $("#rem" + oldNum).prop("disabled", true);
    $("#add" + oldNum).prop("disabled", true);
}

//y = current id
function remove(y){
    console.log("removing current ingredient box with id: ingredient" + y);

    $("#ingredient" + y).remove();
    $("#rem" + y).remove();
    $("#add" + y).remove();
    $("#" + y).remove();


    var oldNum = parseInt(y) - 1;

    $("#rem" + oldNum).removeClass("inactive");
    $("#add" + oldNum).removeClass("inactive");
    $("#rem" + oldNum).prop("disabled", false);
    $("#add" + oldNum).prop("disabled", false);


}

function hideDivs() {
    $("#recipesInfo > div").addClass("hidden");
}

$(document).ready(function(e){
    hideDivs();
})

function resetPages() {
    if(pages[current] != 'ingredientInfo'){
        $("#selectionPage").addClass("hidden");
    }
    for(var i=0 ; i < pages.length; i++){
        $("form > div").addClass("hidden");
    }
}

function resetToFirst() {
    resetPages();
    $("#" + pages[current]).removeClass("hidden");
}

$(document).ready(resetToFirst());


function nextPage(pageNumber){

    if(pageNumber == 1){

        $("#info").addClass("hidden");

        var jsonObject = {};

        var ingredients = [];

        $("form#executeBatch :input.form-control").each(function(){
            var input = $(this).val(); // This is the jquery object of the input, do what you will
            ingredients.push(input);
        });

        console.log(ingredients);

        jsonObject["ingredients"] = ingredients;
        jsonObject["number"] = number;
        jsonObject["ranking"] = "2";

        console.log(jsonObject);

        var callback = function(data){
            // alert(data);
            console.log(data);
            inquiryResponse = data;
            current = 0;
            loadOngo();
            // resetToFirst();
        }

        ajaxCall(jsonObject, "/food_api/app/ingredient_inquiry", callback);
    }

    if(current + 1 < pages.length){
        current++;
        resetPages();
        $("#" + pages[current]).removeClass("hidden");
    }



}


function scrollAnimation(id) {
    $("html, body").animate({
        scrollTop: $("#" + id).offset().top - 80
    }, 700);
}

function canGoNext(pageNumber){



    var goNextPage = true;

    $("#" + pages[current] + " input[required]").map(function() {
        var isEmpty = $(this).val().trim === "";
        return goNextPage = goNextPage && !isEmpty;
    })



    if(goNextPage){
        console.log("herexs1");
        nextPage(pageNumber);
        resetErrorMessage();
    }
    else {
        console.log("Sorry fill everything out.");
        $("#errorMessage span").text("*Please fill out all the required fields*");
    }

    scrollAnimation("tool");


}


function resetErrorMessage(){
    $("#errorMessage span").text("");
}



function ajaxCall(json, url, callback){
    console.log("Doing ajax call to " + url);

    $.ajax({
        headers: {
            "Content-Type": "application/json"
        },
        type: "POST",
        url: url,
        data: JSON.stringify(json),
        success: function(response){
            callback(response);
            console.log(response);
        },
        fail: function(e){
            console.log("There was an error with the call");
        },
        done: function(e){
            console.log("The call is done");
        }
    });

}

function loadOngo() {

    $("#executeBatch2").removeAttr("hidden");

    console.log("load on go : " +inquiryResponse );
    console.log(inquiryResponse);

    console.log("we're done");

    for(var i = 0 ; i < inquiryResponse.length ; i++){
        console.log(i);
    }

    for (var key in inquiryResponse)
    {
        if (inquiryResponse.hasOwnProperty(key))
        {
            // here you have access to
            var id = inquiryResponse[key].id;
            var title = inquiryResponse[key].title;
            var image = inquiryResponse[key].image;

            var usedIngredients = inquiryResponse[key].usedIngredients;
            var missedIngredients = inquiryResponse[key].missedIngredients;

            console.log(id);
            console.log(title);
            console.log(image);
            console.log(usedIngredients);
            console.log(missedIngredients);

            var firstDiv = "<div class = \"form-group floating-label-form-group controls box-content right left bottom\">";

            var img = "<img class=\"col-sm-6 mid\" src=\"" + image + "\" alt=\"" + title + "\" width=\"75%\" height=\"75%\">"

            var secondDiv = "<div class=\"col-sm-6\">";

            var h4_1 = "<h4 class=\"top\">" + title + "</h4>";
            var h4_2 = "";
            var p1 = "";

            for (var key1 in usedIngredients){
                h4_2 = "<h4>You have:</h4>";

                if (usedIngredients.hasOwnProperty(key1)){
                    var originalString = usedIngredients[key1].originalString;
                    p1 += "<p>" + originalString + "</p>";
                }
            }

            var h4_3 = "";
            var p2 = "";

            for (var key2 in missedIngredients){
                h4_3 = "<h4>You need:</h4>";

                if (missedIngredients.hasOwnProperty(key2)){
                    var originalString = missedIngredients[key2].originalString;
                    p2 += "<p>" + originalString + "</p>";
                }
            }

            var br = "<br>";

            var p3 = "<p>Click here for instructions and full ingredients</p>";

            var closeSecondDiv = "</div>";

            var closeFirstDiv = "</div>";

            var newHtml = firstDiv + "\n" +
                img + "\n" +
                secondDiv + "\n" +
                h4_1 + "\n" +
                h4_2 + "\n" +
                p1 + "\n" +
                h4_3 + "\n" +
                p2 + "\n" +
                br + "\n" +
                p3 + "\n" +
                closeSecondDiv + "\n" +
                closeFirstDiv + "\n";


            console.log(newHtml);

            $("#allResults").append(newHtml);

            console.log("\n");
        }
    }


}
























