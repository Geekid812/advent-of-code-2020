fs = require('fs');

content = fs.readFile('input.txt', (err, data) => {
    if (err) throw err;

    const content = data.toString();
    const nums = content.split('\r\n');

    nums.forEach((value) => {
        const firstValue = value;

        nums.forEach((value) => {
            const secondValue = value;
            const numberToSeek = 2020 - firstValue - secondValue;

            if (nums.includes(numberToSeek.toString())) {
                // We found it!
                console.log(`${firstValue} + ${secondValue} + ${numberToSeek} = 2000`)
                console.log(`${firstValue} * ${secondValue} * ${numberToSeek} = ${firstValue * secondValue * numberToSeek}`);
                process.exit();
            }
        })
    })
});