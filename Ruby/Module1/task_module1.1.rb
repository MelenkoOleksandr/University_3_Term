require 'test-unit'
# puts "A: "
# A = gets.chop.to_f
# puts "B: "
# B = gets.chop.to_f
# puts "C: "
# C = gets.chop.to_f
# puts "X_START: "
# X_START = gets.chop.to_f
# puts "X_END: "
# X_END = gets.chop.to_f
# puts "STEP: "
# STEP = gets.chop.to_f

def calc_f(a, b, c, x_start, x_end, step)
  if ((a.truncate | b.truncate) + (a.truncate | c.truncate) % 2) == 0
    a = a.to_f
    b = b.to_f
    c = c.to_f
  else
    a = a.to_i
    b = b.to_i
    c = c.to_i
  end

  results = []
  i = x_start
  while i <= x_end
    puts "x: #{i}\t|\tf(x): #{f(i, a , b, c).round(2)}"
    results.push(f(i, a , b, c).round(2))
    i = i + step
  end
 results
end

def f(x, a, b, c)
  if x < 5 && c != 0
    -a.to_f * x**2 - b
  elsif x > 5 && c == 0
    (x - a).to_f / x
  else
    -x.to_f / c
  end
end

# calc_f(A, B, C, X_START, X_END, STEP)

class Test_Phone < Test::Unit::TestCase
  def test_func
    assert_equal([-0.67, -0.43, -0.25, -0.11, 0.0], calc_f(10, 6, 0, 6, 10, 1))
  end
end

