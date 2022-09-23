# Варіант 7
require 'matrix'

LENGTH = 8
def printArray(array)
  print "Array: "
  array.each { |value| print "#{value} " }
  puts ""
end

def fillArray(array)
  LENGTH.times do |index|
    user_input = rand(10)
    array[index] = user_input
  end
end

puts "Fill array A:"
A = []

puts "Fill array C:"
C = []


fillArray(A)
fillArray(C)


A = A + A
C = C + C

printArray(A)
printArray(C)

B = []

for i in 1..LENGTH
  B.push(A[i] * C[i + 1] - A[LENGTH - 2 + i] * C[LENGTH - 3 +i])
end

printArray(B)

puts "Enter the amount of rows and columns: 3 <= Length <= 9"
len = gets.chop.to_i
puts "Enter k"
k = gets.chop.to_i
matrix = []
b = []

#Filling the main matrix
for i in 0..len - 1
  for j in 0..len - 1
    if i == j
      matrix[i][j] = 2
    else
      matrix[i][j] = k + 2
    end
  end
end

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