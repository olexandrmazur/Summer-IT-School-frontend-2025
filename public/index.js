const first = document.getElementById("first");
const second = document.getElementById("second");
first.addEventListener("click", () => {
    moveTo("first.html")
});
second.addEventListener("click", () => {
    moveTo("second.html")
});
function moveTo(param){
    window.location.href = param;
}