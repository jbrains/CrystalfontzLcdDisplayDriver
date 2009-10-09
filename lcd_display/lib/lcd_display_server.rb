require 'rubygems'
require "socket"
require 'serialport'

SerialPort.open("/dev/tty.usbserial-CF006760", 19200) do |serial_port|
  lcd_display_server = TCPServer.open(20000)
  puts "Started server"
  loop do
    Thread.start(lcd_display_server.accept) do |client|
      puts "Accepted client #{client.inspect}"
      text = client.read
      puts "Received text #{text}"
      puts "Writing text..."
      [17, 0, 0].each { |each| serial_port.putc each }
      serial_port.write " " * 80      
      [17, 0, 0].each { |each| serial_port.putc each }
      serial_port.write(text)
      puts "Wrote text"
      client.close
    end
  end
end
