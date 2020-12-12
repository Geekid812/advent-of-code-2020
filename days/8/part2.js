const { exit } = require('process');

fs = require('fs');

data = fs.readFileSync("input.txt");
content = data.toString();
orignial_instructions = content.split("\r\n");

function runProgram(instructions) {
    accumulator = 0;
    positions = [0];
    
    while (true) {
        current_position = positions[positions.length - 1];
        instruction = instructions[current_position];

        // We reached the end of the instructions
        if (!instruction) return accumulator;

        command = instruction.substring(0, 3);
        value = instruction.substring(4);
    
        if (command == "jmp") {
            new_position = current_position + parseInt(value);
            if (positions.includes(new_position)) break;
    
            positions.push(new_position);
            continue;
        }
    
        if (command == "acc") {
            accumulator = accumulator + parseInt(value);
        }
    
        new_position = current_position + 1;
        if (positions.includes(new_position)) break;
    
        positions.push(new_position);
    }

    return positions;
}

function fixLine(line) {
    if (line.substring(0, 3) == "jmp") return "nop" + line.substring(3);
    return "jmp" + line.substring(3);
}

possible_corrupted_lines = runProgram(orignial_instructions);

possible_corrupted_lines.forEach((value) => {
    // Make a copy
    modified_program = orignial_instructions.slice();

    // Don't change accumulator lines
    if (modified_program[value].substring(0, 3) == "acc") return;

    modified_program[value] = fixLine(modified_program[value]);
    result = runProgram(modified_program);
    if (typeof result == 'number') {
        console.log(`Line at index ${value} was corrupted. The accumulator now has a value of ${result}.`);
        exit();
    }
});