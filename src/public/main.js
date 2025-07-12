const main = document.getElementById("main");
main.addEventListener("click", () => {
    moveTo("index.html")
});
function moveTo(param){
    window.location.href = param;
}