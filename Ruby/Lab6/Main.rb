require_relative 'school'

school = School.new
school.introduce_classes
puts "Total: #{school.total_amount}"
school.classes_in_future
puts "Wanted to stay:"
puts school.get_total_who_stayed
puts "Wanted to enter:"
puts school.get_total_who_wanted
puts "Has entered"
puts school.get_total_entered