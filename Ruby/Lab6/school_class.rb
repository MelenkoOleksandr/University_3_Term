require_relative 'student'

class SchoolClass
  attr_accessor :students


  def initialize(class_name)
    @class_name = class_name
    @students = Array.new
  end

  def add_student(student)
    @students.push(student)
  end

  def get_amount_who_wanted_to_stay
    total = 0
    @students.each do |student|
      if student.entered_place == "-"
        total += 1
      end
    end
    total
  end

  def get_amount_who_wanted_to_enter
    total = 0
    @students.each do |student|
      if student.entered_place != "-"
        total += 1
      end
    end
    total
  end

  def get_amount_of_entered
    total = 0
    @students.each do |student|
      if student.is_entered
         total += 1
         end
    end
    total
  end

  def get_amount
    students.size
  end

  def show_students
    @students.each do |student|
      puts student.print_student
    end
    puts "\n"
  end
end
