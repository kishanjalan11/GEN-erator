
const baseServerURL="http://localhost:8080";
let input=document.getElementById("textInput");

async function fetchData(data) {
    // showLoadingAnimation();
    const url = `${baseServerURL}/joke/`+data ;
     fetch(url, {
         method: "POST",
        //  mode: 'no-cors',
         body: JSON.stringify(data),
         headers: {
             "Content-Type": "application/json"
            }
        })
        .then(function (res) {
            console.log(res);
            return res.text();
        })
        .then(function (res) {
            console.log(res);
        })
        .catch(function (error) {
            console.error('Network Error',error);
        });
    }      

    let submitBTN=document.getElementById("submit");

    submitBTN.addEventListener("click", function(event){
        event.preventDefault();
        let data = input.value;
        fetchData(data);
});