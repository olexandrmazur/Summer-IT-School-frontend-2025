import { moveTo } from './move.js';
const first = document.getElementById("first");
const second = document.getElementById("second");
const third = document.getElementById("third");
const fourth = document.getElementById("fourth");
first.addEventListener("click", () => {
    moveTo("first.html")
});
second.addEventListener("click", () => {
    moveTo("second.html")
});
third.addEventListener("click", () => {
    moveTo("calculator.html")
});
fourth.addEventListener("click", () => {
    moveTo("fourth.html")
});
