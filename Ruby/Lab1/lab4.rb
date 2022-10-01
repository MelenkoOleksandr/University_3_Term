# Варіант 7
puts "Enter the amount of rows and columns: 3 <= Length <= 9"
len = gets.chop.to_i
puts "Enter k"
k = gets.chop.to_i
matrix = []
b = []

#Filling the main matrix
(0..len - 1).each { |i|
  (0..len - 1).each { |j|
    if i == j
      matrix[i][j] = 2
    else
      matrix[i][j] = k + 2
    end
  }
}

#Filling b matrix
for i in 0..len - 1
  b[i] = i + 1
end

for i in 0..len - 1
  elem = matrix[i][i]
  #divide row by the diagonal element
  for j in 0..len - 1
    elem[i][j] /= elem
  end
  b[i] /= elem

  #subtract from rows
  for j in (i + 1)..len - 1
    for k in 0..len-1
      matrix[j][k] = matrix[i][k]
    end

  end
end