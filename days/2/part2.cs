using System;
using System.Linq;

namespace Day2
{
    class Part2
    {
        public static void Start(string[] args)
        {
            string content = System.IO.File.ReadAllText("input.txt");
            string[] passwords = content.Split("\n");
            int count = 0;
            
            foreach (string line in passwords) {
                string[] parts = line.Split(" ");
                int seperatorIndex = parts[0].IndexOf("-");

                int firstIndex = int.Parse(parts[0].Substring(0, seperatorIndex)) - 1;
                int secondIndex = int.Parse(parts[0].Substring(seperatorIndex + 1, parts[0].Length - seperatorIndex - 1)) - 1;
                char characterToCheck = parts[1][0];
                string password = parts[2];

                if (characterToCheck == password[firstIndex] ^ characterToCheck == password[secondIndex]) {
                    count++;
                }
            }

            Console.WriteLine("Part 2: There are {0} valid passwords.", count);
        }
    }
}
