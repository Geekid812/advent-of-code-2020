fs = require('fs');

data = fs.readFileSync("input.txt");
content = data.toString();
instructions = content.split("\r\n");

accumulator = 0;
positions = [0];

function end(new_position) {
    console.log(`Instruction at index ${new_position} is being repeated, stopping. The value of the accumulator is ${accumulator}.`);
    process.exit();
}

while (true) {
    current_position = positions[positions.length - 1];
    instruction = instructions[current_position];
    command = instruction.substring(0, 3);
    value = instruction.substring(4);

    if (command == "jmp") {
        new_position = current_position + parseInt(value);
        if (positions.includes(new_position)) end(new_position);

        positions.push(new_position);
        continue;
    }

    if (command == "acc") {
        accumulator = accumulator + parseInt(value);
    }

    new_position = current_position + 1;
    if (positions.includes(new_position)) end(new_position);

    positions.push(new_position);
}