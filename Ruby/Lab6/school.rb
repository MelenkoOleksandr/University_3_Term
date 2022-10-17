require_relative 'school_class'

class School
  attr_accessor :classes


  def initialize
    @classes = Array.new
    create_class("A")
    create_class("B")
    create_class("C")
  end

  def introduce_classes
    @classes.each do |schoolClass|
      schoolClass.show_students
    end
  end

  def create_class(class_name)
    school_class = SchoolClass.new(class_name)
    amount = (15 + rand(11))
    amount.times do|i|
      surname = "#{class_name}: Student#{i}"
      place = rand(4)

      if place >= 2.5
        place = "university"
      elsif place >= 1.75 && place< 2.5
        place = "college"
      else
        place = "-"
      end

      is_entered = rand < 0.5
      if place == "-"
        is_entered = false
      end

      school_class.add_student( Student.new(surname, place, is_entered))
    end

    @classes.append(school_class)
  end

  def get_total_who_stayed
    total = 0
    classes.each { |school_class| total += school_class.get_amount_who_wanted_to_stay }
    total
  end

  def get_total_who_wanted
    total = 0
    classes.each { |school_class| total += school_class.get_amount_who_wanted_to_enter }
    total
  end

  def get_total_entered
    total = 0
    classes.each { |school_class| total += school_class.get_amount_of_entered }
    total
  end

  def classes_in_future
    amount_stayed = 0
    classes.each { |school_class| amount_stayed +=  (school_class.get_amount - school_class.get_amount_of_entered) }
    puts "Stayed: #{amount_stayed}"
    puts "Future classes amount: "
    if amount_stayed > 50
      puts "3"
    elsif  amount_stayed <= 50 && amount_stayed > 25
      puts "2"
    else
      puts "1"
    end
  end

  def total_amount
    total = 0
    classes.each { |school_class| total += school_class.get_amount }
    total
  end
end
