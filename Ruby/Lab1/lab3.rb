#7 Варіант
 A = true
 B = false
 C = false
 X = 22
 Y = 30
 Z = 50

 puts "Task 1a"
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
 puts "\nTask1b"
 puts Math.exp(n * Math.log(2, Math::E)) < Math.sqrt(n) || !(P || !P)


 puts "\nTask 2"
 def with_if(x)
     if -4 < x and x <= 0
       return (((x - 2).abs / (x**2 * Math.cos(x))) ** (1/7)).to_f
     elsif  0 < x and x <= 12
      return (1/(Math.tan(x + 1/Math::E ** x)/Math.sin(x) ** 2)** (7/2)).to_f
     else
       return (1/(1 + x/(1 + x/(1 + x)))).to_f
     end
 end

 def with_case(x)
   case x
     when -4..0
       return (((x - 2).abs / (x**2 * Math.cos(x))) ** (1/7)).to_f
     when 1 .. 12
       return (1/(Math.tan(x + 1/Math::E ** x)/Math.sin(x) ** 2)** (7/2)).to_f
     else
       return (1/(1 + x/(1 + x/(1 + x)))).to_f
   end
 end

 puts with_if(-5)
 puts with_case(-5)

 def get_sum_2()
   sum = 0
   9.times { |i| sum += 1/3**i}
   sum
 end

def factorial(n)
  if (n < 1)
    return  1
  end
  sum = 1
  n.times{|i| sum *= (i + 1)}
  sum
end

 def get_sum_3(n, x)
   res = 0
   (n+1).times{|i| res += x**i/factorial(i)}
   res
 end

 puts "Task 3"
 puts "Enter n"
 n = gets.chop.to_i
puts "Result #{get_sum_3(n, 2)}"


puts "Task 4"
def calc4_1(i)
  numerator = factorial(i-1).to_f
  denominator = factorial(i + 1).to_f
  (numerator/denominator)**(i*(i+1))
end

def task4_1
  eps = 0.00001
  sum = 0
  i = 2
  prev = 10000
  cur = calc4_1(i)
  sum += cur
  while (cur - prev).abs > eps
    i += 1
    prev = cur
    cur = calc4_1(i)
    sum += cur
  end
  sum
end

def calc4_2(x, i)
  (-1)**i * x**(2*i)/factorial(2*i).to_f
end

def task4_2(x)
  eps = 0.00001
  sum = 0
  i = 0
  prev = 10000
  cur = calc4_2(x, i)
  sum += cur
  while (cur - prev).abs > eps
    i += 1
    prev = cur
    cur = calc4_2(x, i)
    sum += cur
  end
  sum
end

def calc4_3(i)
  factorial(4*i - 1).to_f/(factorial(4*i - 1) * 3**(2*i) * factorial(2*i)).to_f
end

def task4_3
  eps = 0.00001
  sum = 0
  i = 1
  prev = 10000
  cur = calc4_3(i)
  sum += cur
  while (cur - prev).abs > eps
    i += 1
    prev = cur
    cur = calc4_3(i)
    sum += cur
  end
  sum
end

puts "Task4_1: #{task4_1}"
puts "Enter x for task 4_2"
x = gets.chop.to_i
puts "Task4_2: #{task4_2(x)}"
puts "Task4_2 cos x: #{Math.cos(x)}"
puts "Task4_3: #{task4_3}"


puts "Task 5"
C = 5
N = 10

def y(x)
  ((((C**3/2 + 27*x**(3/5)) / (Math.sqrt(C) + 3*x**(1/5)) + 3*(32*x**2)**(1/10)) * 1/x**2  )** C + (1 + x**(1/C) + x**2)/(N - x + (7/3))).to_f
end

def z(x)
  ((1 - Math.cos( 4 * x)) / (Math.cos(2*x) ** (-2) - 1) + (1 + Math.cos(4 * x))/(Math.sin(2*x) ** (-2) - 1) + Math.tan(2*Math::PI/9 - x)** (C/N)).to_f
end

puts "Task 5_1"
step = ((N - 1).to_f / (N + C).to_f)
i = 1
while i <= N
  puts "y(x): #{y(i)}"
  i += step
end

puts "Task 5_2"
step = ((Math::PI - Math::PI/N) / (3/2 * N + C)).to_f
i = (Math::PI - Math::PI/N).to_f
while i <= Math::PI
  puts "z(x): #{z(i)}"
  i += step
end

puts "Task 5_3"
step = ((C - 2) / 2*N).to_f
i = 2
while i <= C
  if 2 < i && i < N
    puts "y(x): #{y(i)}"
    elsif N < i && i < 2 * N
    puts "z(x): #{z(i)}"
  else
    puts "y(x) + z(x): #{y(i) + z(i)}"
  end
  i += step
end