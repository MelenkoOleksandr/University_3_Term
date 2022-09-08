#7 Варіант
A = true
B = false
C = false
X = 22
Y = 30
Z = 50

puts "1."
puts !(A || B) && (A && !B)
# puts (Z != Y) <= (6 >= Y) && A || B && C && X >= 1.5
puts (8 - X * 2 <= Z) && (X ** 2 != Y ** 2) || (Z >= 15)
puts X > 0 && Y < 0 || Z >= (X*Y + (-Y/X)) + (-Z)/3
puts !(A || B&& !(C || (!A || B)))
puts X * 2 + Y * 2 >= 1 && X >= 0 && Y >= 0
puts (A && (C && B != B || A) || C) && B

#2
n = 1
P = false
puts "\n2."
puts Math.exp(n * Math.log(2, Math::E)) < Math.sqrt(n) || !(P || !P)
