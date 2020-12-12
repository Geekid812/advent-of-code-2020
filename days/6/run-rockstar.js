const satriani = require('./rockstar/satriani/satriani.js');
const fs = require('fs');


let rockstar = new satriani.Interpreter();
let program = fs.readFileSync("part2-minimalist.rock").toString();
let ast = rockstar.parse(program);

let input = fs.readFileSync("input.txt").toString().split("\r\n");
let getInput = () => input.shift();
let result = rockstar.run(ast, getInput, console.log)
console.log("Result: "+result);