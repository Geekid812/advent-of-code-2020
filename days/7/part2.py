all_bags = []

class Bag:
    def __init__(self, color, contains):
        self.color = color
        self.contains = contains

        if list(contains.values())[0] == "no":
            self.contains = {}

    def bags_inside(self):
        counter = 0

        for bag_color in self.contains:
            find_bag = [bag for bag in all_bags if bag.color == bag_color][0]
            contained = find_bag.bags_inside()
            quantity = int(self.contains[bag_color])
            counter += contained * quantity + quantity
        
        return counter

with open("input.txt", "r") as f:
    content = f.read()

for line in content.split("\n"):
    words = line.split(" ")
    color = " ".join(words[:2])
    inner_bags = " ".join(words[4:]).split(", ")
    contains = {" ".join(bag.split(" ")[1:3]) : bag.split(" ")[0] for bag in inner_bags}
    all_bags.append(Bag(color, contains))


shiny_bag = [bag for bag in all_bags if bag.color == "shiny gold"][0]
total = shiny_bag.bags_inside()

print(f"There are {total} bags inside a shiny gold bag.")