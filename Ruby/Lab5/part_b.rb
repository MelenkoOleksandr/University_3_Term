require 'test-unit'
class Test_ < Test::Unit::TestCase
  def test_sum
    assert_equal(calc_sum(0.2, 50){ |x_val, i| calc_ai(x_val, i) }.round(6), 0.980067)
    assert_equal(calc_sum(0.4, 20){ |x_val, i| calc_ai(x_val, i) }.round(6), 0.921061)
  end
end


def factorial(n)
  n > 1 ? n * factorial(n - 1) : 1
end

def calc_ai(x, i)
  (-1)**i * x**(2*i).to_f / factorial(2*i)
end

def calc_sum(x, n = Float::INFINITY, eps = 0.001)
  if x < 0.1 || x > 1
    raise "Not correct x"
  end

  cur_sum = yield(x, 0)
  sum = cur_sum
  prev_sum = cur_sum

  (1..n).each do |i|
    cur_sum = yield(x, i)
    sum += cur_sum
    if (cur_sum - prev_sum).abs < eps
      break
    end

    prev_sum = cur_sum
  end
  sum
end

