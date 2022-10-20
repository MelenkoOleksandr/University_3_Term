require 'test-unit'

class Phone
  @@id = 1
  attr_accessor :id
  attr_accessor :surname
  attr_accessor :name
  attr_accessor :patronymic
  attr_accessor :address
  attr_accessor :credit_card
  attr_accessor :debit
  attr_accessor :credit
  attr_accessor :city_time
  attr_accessor :international_time

  def initialize(surname, name, patronymic, address, credit_card, debit, credit, city_time, international_time)
    @id = @@id
    @surname = surname
    @name = name
    @patronymic = patronymic
    @address = address
    @credit_card = credit_card
    @debit = debit
    @credit = credit
    @city_time = city_time
    @international_time = international_time

    @@id = @@id + 1
  end

  def print_info
    puts "\nPhone ##{@id}\nOwner: #{@surname} #{@name} #{@patronymic}\nAddress: #{@address}\nCredit Card: #{@credit_card}\nDebit: #{@debit}\nCredit: #{@credit}\nCity Minutes: #{@city_time}\nInternational Minutes: #{@international_time} \n"
  end
end


def phones_city_time_exceed(phones, time)
  phones.select { |phone| phone.city_time > time }
end

def phones_used_international(phones)
  phones.select { |phone| phone.international_time > 0 }
end

def sorted_phones(phones)
  phones.sort { |a, b| a.surname <=> b.surname}
end

phones = []
phones.push(Phone.new("Woodward", "Melissa", "Vitalievich", "Street ...", "213213213123213123", 1000, 0, 120, 10))
phones.push(Phone.new("Crawford", "Fernanda", "Vitalievich", "Street ...", "213213213123213123", 200, 100, 104, 0))
phones.push(Phone.new("Simmons", "Theodore", "Vitalievich", "Street ...", "213213213123213123", 400, 50, 16, 20))
phones.push(Phone.new("Singh", "Arlene", "Vitalievich", "Street ...", "213213213123213123", 200, 0, 153, 0))
phones.push(Phone.new("Farmer", "Maurice", "Vitalievich", "Street ...", "213213213123213123", 100, 3000, 88, 22))
phones.push(Phone.new("Hughes", "Carly", "Vitalievich", "Street ...", "213213213123213123", 0, 0, 139, 18))

puts "\nTask A:\nCity calls time greater than 100\n---------------------------\n"
phones_city_time_exceed(phones, 100).each { |phone| phone.print_info }

puts "\n---------------------------\nTask B:\nUsed international calls\n---------------------------\n"
phones_used_international(phones).each { |phone| phone.print_info }

puts "\n---------------------------\nTask C:\nSorted by surname\n---------------------------\n"
sorted_phones(phones).each { |phone| phone.print_info }

puts "\nTests:\n"

class Test_Phone < Test::Unit::TestCase
  def test_phones_city_time_exceed
    phone1 = Phone.new("Woodward", "Melissa", "F", "Street ...", "213213213123213123", 1000, 0, 120, 10)
    phone2 = Phone.new("Crawford", "Fernanda", "F", "Street ...", "213213213123213123", 200, 100, 104, 0)
    phone3 = Phone.new("Simmons", "Theodore", "F", "Street ...", "213213213123213123", 400, 50, 16, 20)
    phone4 = Phone.new("Singh", "Arlene", "F", "Street ...", "213213213123213123", 200, 0, 153, 0)
    phone5 = Phone.new("Farmer", "Maurice", "F", "Street ...", "213213213123213123", 100, 3000, 88, 22)
    phone6 = Phone.new("Hughes", "Carly", "F", "Street ...", "213213213123213123", 0, 0, 139, 18)

    phones = [phone1, phone2, phone3, phone4, phone5, phone6]
    res1 = [phone4, phone6]
    res3 = [phone1, phone2, phone3, phone4, phone5, phone6]

    assert_equal(res1, phones_city_time_exceed(phones, 130))
    assert_equal([], phones_city_time_exceed(phones, 200))
    assert_equal(res3, phones_city_time_exceed(phones, 0))
  end

  def test_phones_used_international
    phone1 = Phone.new("Woodward", "Melissa", "A", "Street ...", "213213213123213123", 1000, 0, 120, 10)
    phone2 = Phone.new("Crawford", "Fernanda", "C", "Street ...", "213213213123213123", 200, 100, 104, 0)
    phone3 = Phone.new("Simmons", "Theodore", "V", "Street ...", "213213213123213123", 400, 50, 16, 20)
    phone4 = Phone.new("Singh", "Arlene", "S", "Street ...", "213213213123213123", 200, 0, 153, 0)
    phone5 = Phone.new("Farmer", "Maurice", "F", "Street ...", "213213213123213123", 100, 3000, 88, 22)
    phone6 = Phone.new("Hughes", "Carly", "F", "Street ...", "213213213123213123", 0, 0, 139, 18)

    phones1 = [phone1, phone2, phone3, phone4, phone5, phone6]
    res1 = [phone1, phone3, phone5, phone6]

    phones2 = [ phone2, phone4, phone6]
    res2 = [phone6]

    assert_equal(res1, phones_used_international(phones1))
    assert_equal(res2, phones_used_international(phones2))
  end


  def test_sorted_phones
    phone1 = Phone.new("Woodward", "Melissa", "F", "Street ...", "213213213123213123", 1000, 0, 120, 10)
    phone2 = Phone.new("Crawford", "Fernanda", "F", "Street ...", "213213213123213123", 200, 100, 104, 0)
    phone3 = Phone.new("Simmons", "Theodore", "F", "Street ...", "213213213123213123", 400, 50, 16, 20)
    phone4 = Phone.new("Singh", "Arlene", "F", "Street ...", "213213213123213123", 200, 0, 153, 0)
    phone5 = Phone.new("Farmer", "Maurice", "F", "Street ...", "213213213123213123", 100, 3000, 88, 22)
    phone6 = Phone.new("Hughes", "Carly", "F", "Street ...", "213213213123213123", 0, 0, 139, 18)

    phones1 = [phone1, phone2, phone3, phone4, phone5, phone6]
    res1 = [phone2, phone5,phone6, phone3, phone4, phone1 ]

    assert_equal(res1, sorted_phones(phones1), "Sorted")
    assert_equal([], sorted_phones([]))
  end

end