# Варіант 7
# https://www.techotopia.com/index.php/Advanced_Ruby_Arrays#Intersection.2C_Union_and_Difference

def printArray(array)
  print "Array: "
  array.each { |value| print "#{value} " }
  puts ""
end

def fillArray(array)
  14.times do |index|
    puts "Input array[#{index}]"
    user_input = gets.chop.to_i
    array[index] = user_input
  end
end

puts "Fill array A:"
A = []

puts "Fill array C:"
C = []

fillArray(A)
fillArray(C)

printArray(A)
printArray(C)

B = A & C
puts "Result:"
printArray(B)


