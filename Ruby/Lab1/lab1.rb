# 7 Варіант
def calc(x, fi, a, b)
  return  (x * ((fi - a ) ** (1/4))) + (Math.log(Math.sin(b).abs, Math::E) ** 3 + Math.tan(x) ** (1/3))/(2.3 * Math.log10(x.abs))
end

puts "X: "
x = gets.chop.to_f
puts "fi: "
fi = gets.chop.to_f
puts "a: "
a = gets.chop.to_f
puts "b: "
b = gets.chop.to_f

result = calc(x, fi, a, b)
puts "Result: #{result}"