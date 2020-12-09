using System;
using System.Linq;

namespace Day2
{
    class Part1
    {
        static void Main(string[] args)
        {
            string content = System.IO.File.ReadAllText("input.txt");
            string[] passwords = content.Split("\n");
            int count = 0;
            
            foreach (string line in passwords) {
                string[] parts = line.Split(" ");
                int seperatorIndex = parts[0].IndexOf("-");

                int minOccurences = int.Parse(parts[0].Substring(0, seperatorIndex));
                int maxOccurences = int.Parse(parts[0].Substring(seperatorIndex + 1, parts[0].Length - seperatorIndex - 1));
                char characterToCount = parts[1][0];
                string password = parts[2];

                int numOccurences = password.Count(c => c == characterToCount);
                if (minOccurences <= numOccurences && numOccurences <= maxOccurences) {
                    count++;
                }
            }

            Console.WriteLine("Part 1: There are {0} valid passwords.", count);
            Part2.Start(args);
        }
    }
}
