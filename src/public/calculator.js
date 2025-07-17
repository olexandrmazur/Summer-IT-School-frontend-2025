import { moveTo } from './move.js';

function parseQuadratic(equation) {
    equation = equation.replace(/\s+/g, '').replace('=0', '');

    const terms = equation.match(/[+-]?[^+-]+/g);

    let a = 0, b = 0, c = 0;

    terms.forEach(term => {
        if (term.includes('x^2')) {
            let coeff = term.replace('x^2', '');
            if (coeff === '' || coeff === '+') coeff = '1';
            else if (coeff === '-') coeff = '-1';
            a = parseFloat(coeff);
        } else if (term.includes('x')) {
            let coeff = term.replace('x', '');
            if (coeff === '' || coeff === '+') coeff = '1';
            else if (coeff === '-') coeff = '-1';
            b = parseFloat(coeff);
        } else {
            c = parseFloat(term);
        }
    });

    return { a, b, c };
}

function solveQuadraticSymbolic(coeffs) {
    const {a, b, c} = coeffs;

    if (a === 0) {
        if (b === 0) {
            return c === 0 ? ['Безліч розвʼязків'] : ['Розвʼязків немає'];
        }
        return [`Лінійне рівняння, корінь: ${-c / b}`];
    }

    const d = b * b - 4 * a * c;

    if (d < 0) {
        return ['Коренів немає'];
    }

    const denom = 2 * a;

    if (d === 0) {
        const x = -b / denom;
        return [`Один корінь: x = ${x}`];
    }

    if (Number.isInteger(Math.sqrt(d))) {
        const sqrtD = Math.sqrt(d);
        const x1 = (-b + sqrtD) / denom;
        const x2 = (-b - sqrtD) / denom;
        return [`Два корені: x1 = ${x1}`, `x2 = ${x2}`];
    } else {
        const bStr = (b === 0) ? '' : (b > 0 ? `-${b}` : `+${-b}`);
        return [
            `x₁ = (${bStr} + √${d}) / ${denom}`,
            `x₂ = (${bStr} - √${d}) / ${denom}`
        ];
    }
}

const button = document.getElementById("calculator");
console.log(button)
button.addEventListener("click",()=>{
    const eq = document.getElementById("equation").value;
    console.log(eq);
    if(eq.length < 1) {
        alert("Ви не ввели рівняння а маєте ввести");
    } else {
        const coeffs = parseQuadratic(eq);
        const solving = solveQuadraticSymbolic(coeffs);
        const result = document.getElementById("text");
        console.log(result);
        result.innerHTML = "Результат: " + solving[0] + " " + solving[1];
        console.log(coeffs);
        console.log(solving);
    }
});
const main = document.getElementById("main");
main.addEventListener("click", () => {
    moveTo("index.html")
});