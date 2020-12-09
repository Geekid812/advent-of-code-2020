fs = require('fs');

content = fs.readFile('input.txt', (err, data) => {
    if (err) throw err;

    const content = data.toString();
    const nums = content.split('\r\n');

    nums.forEach((value) => {
        const numberToSeek = 2020 - value;

        if (nums.includes(numberToSeek.toString())) {
            // We found it!
            console.log(`${value} + ${numberToSeek} = 2000 \n${value} * ${numberToSeek} = ${value * numberToSeek}`);
            process.exit();
        }
    })
});