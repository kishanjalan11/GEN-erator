
const baseServerURL="http://localhost:8080";
let input=document.getElementById("textInput");
let output=document.getElementById("output");
async function fetchData(choice,data) {
    showLoadingAnimation();
    output.innerText="";
    const url = `${baseServerURL}/`+choice+`/`+data ;
     fetch(url, {
         method: "POST",
         body: JSON.stringify(data),
         headers: {
             "Content-Type": "application/json"
            }
        })
        .then(function (res) {
            return res.text();
        })
        .then(function (res) {
            console.log(res);
            hideLoadingAnimation();
            output.innerText=res;
        })
        .catch(function (error) {
            console.error('Network Error',error);
            hideLoadingAnimation();
            output.innerText="Network Error. Please Try Later"
        });
    }      

    let submitBTN=document.getElementById("submit");

    submitBTN.addEventListener("click", function(event){
        event.preventDefault();
        let choice=document.getElementById("options").value;
        console.log(choice);
        let data = input.value;
        fetchData(choice,data);
});


function showLoadingAnimation() {
    document.getElementById("loadingAnimation").style.display = "block";
}

function hideLoadingAnimation() {
    document.getElementById("loadingAnimation").style.display = "none";
}