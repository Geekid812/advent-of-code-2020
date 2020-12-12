all_bags = []

class Bag:
    def __init__(self, color, contains):
        self.color = color
        self.contains = contains
        self.can_hold_shiny = "shiny gold" in contains

        if contains[0] == "other bags.":
            self.contains = []

    def contains_shiny(self):
        if self.can_hold_shiny: return True

        for bag_color in self.contains:
            find_bag = [bag for bag in all_bags if bag.color == bag_color][0]
            is_shiny = find_bag.contains_shiny()

            if is_shiny:
                return True

        return False

with open("input.txt", "r") as f:
    content = f.read()

for line in content.split("\n"):
    words = line.split(" ")
    color = " ".join(words[:2])
    inner_bags = " ".join(words[4:]).split(", ")
    contains = [" ".join(bag.split(" ")[1:3]) for bag in inner_bags]
    all_bags.append(Bag(color, contains))

counter = 0
for bag in all_bags:
    if bag.contains_shiny():
        counter += 1

print(f"{counter} bags can hold a shiny gold bag.")