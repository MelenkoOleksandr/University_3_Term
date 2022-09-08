class Student
  attr_accessor :surname
  attr_accessor :is_enrolled
  attr_accessor :place_to_study

  def initialize(surname, place_to_study = "", is_enrolled = false )
    @surname = surname
    @place_to_study = place_to_study
    @is_enrolled = is_enrolled
  end
end

class SchoolClass
  def initialize(form_teacher, students)
    @form_teacher = form_teacher
    @students = students
  end

  def get_amount_of_students_by_wishes(wish)
    amount = 0
    @students.each {|student| amount += 1 if student.place_to_study == wish}
    return  amount
  end

  def calc_students_who_stayed
    amount = 0
    @students.each {|student| amount += 1 unless student.is_enrolled}
    return  amount
  end
end

class School
  @min_students_in_class = 15
  @max_students_in_class = 25

  def initialize(classes)
    @classes = classes
  end

  def calc_amount_of_10th_classes
    amount_who_left = 0
    @classes.each do |school_class|
      amount_who_left += school_class.calc_students_who_stayed
    end
    if amount_who_left < @min_students_in_class * 3
      return 2
    elsif amount_who_left < @min_students_in_class * 2
      return 1
    else
      return 3
    end
  end
end