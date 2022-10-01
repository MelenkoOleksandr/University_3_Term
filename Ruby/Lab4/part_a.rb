require 'test/unit'

def print_array(array)
  print "Array: "
  array.each { |value| print "#{value} " }
  puts ""
end

def fill_array(array)
  LENGTH.times do |index|
    user_input = rand(10)
    array[index] = user_input
  end
end

array1 = []
array2 = []


fill_array(array1)
fill_array(array2)

def lab4_a(array1, array2, len)

  array1 = array1 + array1
  array2 = array2 + array2

  # p array1
  # p array2

  result = []

  (1..len).each { |i|
    result.push(array1[i] * array2[i + 1] - array1[len - 2 + i] * array2[len - 3 + i])
  }

  # p result
  result
end


p lab4_a([1, 2, 3], [1, 2, 3], 3)


class Test_A < Test::Unit::TestCase
  def test_task1
    assert_true lab4_a([1, 2, 3], [1, 2, 3], 3) == [0, 0, 0]
  end
end