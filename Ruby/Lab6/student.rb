class Student
  def initialize(surname, entered_place="-", is_entered=false)
    @surname = surname
    @entered_place = entered_place
    @is_entered = is_entered
  end

  def surname
    @surname
    end

  def entered_place
    @entered_place
  end

  def is_entered
    @is_entered
  end

  def print_student
    "#{surname} wanted to enter #{entered_place}, result: #{is_entered}\n"
  end
end
