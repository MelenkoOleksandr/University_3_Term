# 7 Варіант

# dots = [[x1, y1], [x2, y2], [x3, y3]....]
def calcArea(dots)
  sum = 0
  dots.each_with_index do |currentDot, index|
    if (index == dots.length() - 1)
      sum += (currentDot[0] + dots[0][0]) * (currentDot[1] - dots[0][1])
    else
      sum += (currentDot[0] + dots[index + 1][0]) * ( currentDot[1] - dots[index + 1][1])
    end
  end
  return sum.abs.to_f / 2
end


triangleExample = [[1, 1], [4, 2], [5, 3]]
dots = [
  [514, 19], [515, 56], [506, 107], [492, 154], [386, 186],
  [314, 185], [283, 234], [205, 233], [134, 220], [78, 202],
  [68, 179], [67, 148], [82, 118], [90, 94], [92, 59],
  [94, 42], [107, 29], [155, 32], [199, 60], [224, 51],
  [245, 37], [279, 31], [306, 32], [342, 28], [365, 22],
  [397, 16], [443, 26], [468, 39], [488, 32]
]

puts "The area of test triangle in example: #{calcArea(triangleExample)}"
puts "The area of given polygon is #{calcArea(dots)}"