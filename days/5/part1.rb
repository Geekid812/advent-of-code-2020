highest_id = 0

def get_seat_id(seat)
    row = 2 ** 7 - 1
    col = 2 ** 3 - 1
    chars = seat.split("")
    rows = chars.slice(0, 7)
    cols = chars.slice(7, 3)

    for i in 0..6
        if rows[i] == "F" then
            row -= 2 ** (6 - i)
        end
    end

    for i in 0..2
        if cols[i] == "L" then
            col -= 2 ** (2 - i)
        end
    end

    seat_id = 8 * row + col
    seat_id
end

File.open('input.txt', 'r') do |f|
    while line = f.gets
        current_id = get_seat_id(line)

        if current_id > highest_id then
            highest_id = current_id
        end
    end
end

puts "The highest seat ID is #{highest_id}."