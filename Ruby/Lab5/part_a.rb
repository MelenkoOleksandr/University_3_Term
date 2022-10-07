def f1(x)
  x**3 * Math::E ** (2*x)
end

def f2(x)
  Math.tan(x/2.0 + Math::PI / 4) ** (5.0/2.0)
end

f1_proc = Proc.new{|x| f1(x)}
f2_proc = Proc.new{|x| f2(x)}

def prm(a, b, n, h = (b - a).to_f / n )
  sum = 0
  x = a + h - h / 2

  while x < b do
    f = yield(x)
    sum += f
    x += h
  end

  sum * h
end

def trp(a, b, func, n)
  h = (b - a).to_f / n
  cur = a + h
  sum = func.call(a) / 2 + func.call(b) / 2

  while cur < b do
    f = func.call(cur)
    sum += f
    cur += h
  end

  sum * h
end


puts "First Integration"
puts prm(0, 0.8, 1000) { |x| f1(x) }
puts trp(0, 0.8, f1_proc, 1000)

puts "Second Integration"
puts prm(0, Math::PI / 8, 1000) { |x| f2(x) }
puts trp(0, Math::PI / 8, f2_proc, 1000)


