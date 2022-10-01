require 'test/unit'

def print_matrix(matrix, b)
  (0..matrix.length - 1).each { |i|
    (0..matrix.length - 1).each { |j|
      printf("%f ", matrix[i][j])
    }
    printf("| %f\n", b[i])
  }
  puts
end

puts "Enter the amount of rows and columns: 3 <= Length <= 9"
len = 4

def gaus(len)
  matrix = (0...len).map {Array.new(len)}
  b = Array.new(len)

  #Filling the main matrix
  (0..len - 1).each { |i|
    (0..len - 1).each { |j|
      if i == j
        matrix[i][j] = 2
      else
        matrix[i][j] = len + 2
      end
    }
  }

  #Filling b matrix
  (0..len - 1).each { |i|
    b[i] = i + 1
  }

  print_matrix(matrix, b)

  (0..len-1).each do |i|
    current = matrix[i][i].to_f

    (0..len-1).each do |j|
      matrix[i][j] /= current
    end

    b[i] /= current

    (i+1..len-1).each do |j|
      current_line_element = matrix[j][i].to_f
      (0..len-1).each do |k|
        matrix[j][k] = (matrix[j][k] - matrix[i][k] * current_line_element).to_f
      end
      b[j] = b[j] - b[i] * current_line_element
    end
  end

  print_matrix(matrix, b)

  (len-1).downto(0).each do |i|
    (0..len - 1).each do |j|
      if (i != j)
        b[j] -= b[i] * matrix[j][i]
        matrix[j][i] = 0
      end
    end
  end

  print_matrix(matrix, b)
  b
end

class Test_B < Test::Unit::TestCase
  def test_gaus
    assert_true gaus(4) == [0.5, 0.25, 0.0, -0.25]
  end
end