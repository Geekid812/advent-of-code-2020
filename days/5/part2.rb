seat_ids = []

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
        seat_ids.push current_id
    end
end

puts "The lowest seat ID is #{seat_ids.min}."
puts "The highest seat ID is #{seat_ids.max}."

for i in seat_ids.min..seat_ids.max
    if !(seat_ids.include?(i)) then
        puts "The seat ID not included in this range is #{i}."
        exit
    end
end
